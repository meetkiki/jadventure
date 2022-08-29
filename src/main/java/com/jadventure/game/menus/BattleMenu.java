package com.jadventure.game.menus;

import com.jadventure.game.DeathException;
import com.jadventure.game.constant.Define;
import com.jadventure.game.entities.Entity;
import com.jadventure.game.entities.Player;
import com.jadventure.game.entities.NPC;
import com.jadventure.game.QueueProvider;
import com.jadventure.game.CharacterChange;
import com.jadventure.game.items.ItemStack;
import com.jadventure.game.items.Item;
import com.jadventure.game.GameBeans;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * 战斗系统
 * @auther zgn
 * @date  2022/8/29
 **/
public class BattleMenu extends Menus {

    private NPC opponent;
    private Player player;
    private Random random;
    private int armour;
    private double damage;
    private int escapeSuccessfulAttempts = 0;//逃跑成功次数,成功一次+1,失败-1

    public BattleMenu(NPC opponent, Player player) throws DeathException {
        this.random = new Random();
        this.opponent = opponent;
        this.player = player;
        this.armour = player.getArmour();
        this.damage = player.getDamage();
        buildMenu();
        //战斗流程
        while (opponent.getHealth() > 0 &&
                player.getHealth() > 0 &&
                (escapeSuccessfulAttempts <= 0)) {
            QueueProvider.offer(Define.strRoleChioce001);
            MenuItem selectedItem = displayMenu(this.menuItems);
            testSelected(selectedItem);
        }
        if (player.getHealth() == 0) {//死亡
            QueueProvider.offer(Define.strRoleDied);
            String reply = QueueProvider.take().toLowerCase();
            while (!reply.startsWith(Define.strSysYes) && !reply.startsWith(Define.strSysNo)) {
                QueueProvider.offer(Define.strRoleDied);
                reply = QueueProvider.take().toLowerCase();
            }
            if (reply.startsWith(Define.strSysYes)) {
                throw new DeathException("restart");
            } else if (reply.startsWith(Define.strSysNo)) {
                throw new DeathException("close");
            }
        }  else if (opponent.getHealth() == 0) {//获胜
            int xp = opponent.getXPGain();
            this.player.setXP(this.player.getXP() + xp);
            int oldLevel = this.player.getLevel();
            int newLevel = (int) (0.075 * Math.sqrt(this.player.getXP()) + 1);
            this.player.setLevel(newLevel);

            // Iterates over the opponent's items and if there are any, drops them.
            // There are two loops due to a ConcurrentModification Exception that occurs
            // if you try to remove the item while looping through the npc's items.
            List<ItemStack> itemStacks = opponent.getStorage().getItemStack();
            List<String> itemIds = new ArrayList<>();
            for (ItemStack itemStack : itemStacks) {
                String itemId = itemStack.getItem().getId();
                itemIds.add(itemId);
            }
            for (String itemId : itemIds) {
                Item item = GameBeans.getItemRepository().getItem(itemId);
                opponent.removeItemFromStorage(item);
                this.player.getLocation().addItem(item);
                QueueProvider.offer(String.format(Define.strFoeDrop,item.getName()));
            }

            this.player.getLocation().remove(opponent);
            this.player.setGold(this.player.getGold() + opponent.getGold());
            QueueProvider.offer(String.format(Define.strRoleWin,opponent.getName(),xp,opponent.getGold()));
            if (oldLevel < newLevel) {
                QueueProvider.offer(String.format(Define.strRoleLevel,newLevel));
            }
            CharacterChange cc = new CharacterChange();
            cc.trigger(this.player, "kill", opponent.getName());
        }
    }

    /**
     * 初始化战斗指令
     * @auther zgn
     * @date  2022/8/29
     **/
    private void buildMenu() {
        this.menuItems.add(new MenuItem(Define.commandAttack,
                String.format(Define.strBattle, opponent.getName())));
        this.menuItems.add(new MenuItem(Define.commandDefend,
                String.format(Define.strRoleDefend, opponent.getName())));
        this.menuItems.add(new MenuItem(Define.commandEscape,
                String.format(Define.strRoleEscape, opponent.getName())));
        this.menuItems.add(new MenuItem(Define.commandEquip, Define.strItemsEquip));
        this.menuItems.add(new MenuItem(Define.commandUnEquip, Define.strItemsUnEquip));
        this.menuItems.add(new MenuItem(Define.commandView,Define.strRoleView));

    }

    private void testSelected(MenuItem m) {
        if(m.getKey().equals(Define.commandAttack)){
            //进攻伤害完整,护甲削弱
            mutateStats(1, 0.5);
            attack(player, opponent);
            attack(opponent, player);
            resetStats();
        }
        if(m.getKey().equals(Define.commandDefend)){
            mutateStats(0.5, 1);
            QueueProvider.offer(String.format(Define.strRoleDefend001,opponent.getName()));
            attack(player, opponent);
            attack(opponent, player);
            resetStats();
        }
        //逃跑
        if(m.getKey().equals(Define.commandEscape)){
            escapeSuccessfulAttempts = escapeAttempt(player,
                    opponent, escapeSuccessfulAttempts);
        }
        if(m.getKey().equals(Define.commandEquip)){
            equip();
        }
        if(m.getKey().equals(Define.commandUnEquip)){
            unequip();
        }
        if(m.getKey().equals(Define.commandView)){
            viewStats();
        }
    }

    private int escapeAttempt(Player player, NPC attacker,
            int escapeAttempts) {
        if (escapeAttempts == -10) {
            escapeAttempts = 0;
        }
        //逃跑等级= 智力+精神+敏捷
        double playerEscapeLevel = player.getIntelligence() +
            player.getStealth() + player.getDexterity();
        //进攻等级=逃跑等级+(进攻方伤害/逃跑等级)
        double attackerEscapeLevel = playerEscapeLevel +
            (attacker.getDamage() / playerEscapeLevel);
        //逃跑系数=逃跑等级/进攻等级
        //假设伤害系数为a a^2/(a^2+伤害)
        double escapeLevel = playerEscapeLevel / attackerEscapeLevel;

        Random rand = new Random();
        //zgnTodo 逃跑和幸运也有关系,有误的计算方式
        int rawLuck = rand.nextInt(player.getLuck()*2) + 1;
        int lowerBound = 60 - rawLuck;
        int upperBound = 80 - rawLuck;
        double minEscapeLevel = (rand.nextInt((upperBound - lowerBound) + 1) +
                lowerBound) / 100.0;//最小逃跑系数[0.22-0.3]
        //逃跑系数达标且未曾逃跑过,可以逃跑
        if (escapeLevel > minEscapeLevel && (escapeAttempts == 0)) {
            QueueProvider.offer(String.format(Define.strRoleEscape,attacker.getName()));
            return 1;
        } else if (escapeAttempts < 0) {//不能在逃了
            QueueProvider.offer(Define.strRoleEscape001);
            return escapeAttempts - 1;
        } else {//逃跑失败
            QueueProvider.offer(String.format(Define.strRoleEscape002,attacker.getName()));
            return escapeAttempts-1;
        }
    }
    /**
     * zgnTodo 伤害计算
     * @auther zgn
     * @date  2022/8/29
     * @param attacker 进攻方
     * @param defender 防守方
     **/
    private void attack(Entity attacker, Entity defender) {

        if (attacker.getHealth() == 0) {
            return;
        }
        double damage = attacker.getDamage();
        double critCalc = random.nextDouble();
        if (critCalc < attacker.getCritChance()) {
            damage += damage;
            QueueProvider.offer(Define.strBattle003);
        }
        int healthReduction = (int) ((((3 * attacker.getLevel() / 50 + 2) *
                damage * damage / (defender.getArmour() + 1)/ 100) + 2) *
                (random.nextDouble() + 1));
        defender.setHealth((defender.getHealth() - healthReduction));
        if (defender.getHealth() < 0) {
            defender.setHealth(0);
        }
        QueueProvider.offer(String.format(Define.strBattle001,healthReduction));
        QueueProvider.offer(String.format(Define.strBattle002,defender.getName(),defender.getHealth()));
        /*if (attacker instanceof Player) {
            QueueProvider.offer(String.format(Define.strBattle002,defender.getName(),defender.getHealth()));
        } else {
            QueueProvider.offer(String.format(Define.strBattle002,defender.getName(),defender.getHealth()));
        }*/
    }

    /**
     * 百分比调整攻击和护甲值
     * @auther zgn
     * @date  2022/8/29
     * @param damageMult
     * @param armourMult
     *
     **/
    private void mutateStats(double damageMult, double armourMult) {
        armour = player.getArmour();
        damage = player.getDamage();
        player.setArmour((int) (armour * armourMult));
        player.setDamage(damage * damageMult);
    }

    private void resetStats() {
        player.setArmour(armour);
        player.setDamage(damage);
    }

    private void equip() {
        player.printStorage();
        QueueProvider.offer(Define.strItemsUse);
        String itemName = QueueProvider.take();
        if (!itemName.equalsIgnoreCase("back")) {
            player.equipItem(itemName);
        }
    }

    private void unequip() {
        player.printEquipment();
        QueueProvider.offer(Define.strItemsEquip001);
        String itemName = QueueProvider.take();
        if (!itemName.equalsIgnoreCase("back")) {
            player.dequipItem(itemName);
        }
    }

    private void viewStats() {
        QueueProvider.offer(Define.strSysView);
        String input = QueueProvider.take();
        switch (input) {
            case "vs":
            case "viewstats":
                player.getStats();
                break;
            case "ve":
            case "viewequipped":
                player.printEquipment();
                break;
            case "vb":
            case "viewbackpack":
                player.printStorage();
                break;
            case "back":
            case "exit":
                break;
            default:
                viewStats();
                break;
        }
    }
}
