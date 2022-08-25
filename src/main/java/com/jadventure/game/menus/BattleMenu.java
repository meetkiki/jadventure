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

public class BattleMenu extends Menus {

    private NPC opponent;
    private Player player;
    private Random random;
    private int armour;
    private double damage;
    private int escapeSuccessfulAttempts = 0;

    public BattleMenu(NPC opponent, Player player) throws DeathException {
        this.random = new Random();
        this.opponent = opponent;
        this.player = player;
        this.armour = player.getArmour();
        this.damage = player.getDamage();
        buildMenu();
        while (opponent.getHealth() > 0 &&
                player.getHealth() > 0 &&
                (escapeSuccessfulAttempts <= 0)) {
            QueueProvider.offer(Define.strYourChioce);
            MenuItem selectedItem = displayMenu(this.menuItems);
            testSelected(selectedItem);
        }
        if (player.getHealth() == 0) {
            QueueProvider.offer(Define.strDied);
            String reply = QueueProvider.take().toLowerCase();
            while (!reply.startsWith(Define.strYes) && !reply.startsWith(Define.strNo)) {
                QueueProvider.offer(Define.strDied);
                reply = QueueProvider.take().toLowerCase();
            }
            if (reply.startsWith(Define.strYes)) {
                throw new DeathException("restart");
            } else if (reply.startsWith(Define.strNo)) {
                throw new DeathException("close");
            }
        }  else if (opponent.getHealth() == 0) {
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
                QueueProvider.offer(String.format(Define.strDrop,item.getName()));
            }

            this.player.getLocation().remove(opponent);
            this.player.setGold(this.player.getGold() + opponent.getGold());
            QueueProvider.offer(String.format(Define.strKilled,opponent.getName(),xp,opponent.getGold()));
            if (oldLevel < newLevel) {
                QueueProvider.offer(String.format(Define.strLevelNow,newLevel));
            }
            CharacterChange cc = new CharacterChange();
            cc.trigger(this.player, "kill", opponent.getName());
        }
    }

    private void buildMenu() {
        this.menuItems.add(new MenuItem(Define.commandAttack,
                String.format(Define.strAttack, opponent.getName())));
        this.menuItems.add(new MenuItem(Define.commandDefend,
                String.format(Define.strDefend, opponent.getName())));
        this.menuItems.add(new MenuItem(Define.commandEscape,
                String.format(Define.strEscape, opponent.getName())));
        this.menuItems.add(new MenuItem(Define.commandEquip, Define.strEquip));
        this.menuItems.add(new MenuItem(Define.commandUnequip, Define.strUnequip));
        this.menuItems.add(new MenuItem(Define.commandView,Define.strView));

    }

    private void testSelected(MenuItem m) {
        if(m.getKey().equals(Define.commandAttack)){
            mutateStats(1, 0.5);
            attack(player, opponent);
            attack(opponent, player);
            resetStats();
        }
        if(m.getKey().equals(Define.commandDefend)){
            mutateStats(0.5, 1);
            QueueProvider.offer(String.format(Define.strDefend001,opponent.getName()));
            attack(player, opponent);
            attack(opponent, player);
            resetStats();
        }
        if(m.getKey().equals(Define.commandEscape)){
            escapeSuccessfulAttempts = escapeAttempt(player,
                    opponent, escapeSuccessfulAttempts);
        }
        if(m.getKey().equals(Define.commandEquip)){
            equip();
        }
        if(m.getKey().equals(Define.commandUnequip)){
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
        double playerEscapeLevel = player.getIntelligence() +
            player.getStealth() + player.getDexterity();
        double attackerEscapeLevel = attacker.getIntelligence() +
            attacker.getStealth() + attacker.getDexterity() +
            (attacker.getDamage() / playerEscapeLevel);
        double escapeLevel = playerEscapeLevel / attackerEscapeLevel;

        Random rand = new Random();
        int rawLuck = rand.nextInt(player.getLuck()*2) + 1;
        int lowerBound = 60 - rawLuck;
        int upperBound = 80 - rawLuck;
        double minEscapeLevel = (rand.nextInt((upperBound - lowerBound) + 1) +
                lowerBound) / 100.0;
        if (escapeLevel > minEscapeLevel && (escapeAttempts == 0)) {
            QueueProvider.offer(String.format(Define.strEscape,attacker.getName()));
            return 1;
        } else if (escapeAttempts < 0) {
            QueueProvider.offer(Define.strEscape001);
            return escapeAttempts - 1;
        } else {
            QueueProvider.offer(String.format(Define.strEscape002,attacker.getName()));
            return escapeAttempts-1;
        }
    }

    private void attack(Entity attacker, Entity defender) {
        if (attacker.getHealth() == 0) {
            return;
        }
        double damage = attacker.getDamage();
        double critCalc = random.nextDouble();
        if (critCalc < attacker.getCritChance()) {
            damage += damage;
            QueueProvider.offer(Define.strAttack003);
        }
        int healthReduction = (int) ((((3 * attacker.getLevel() / 50 + 2) *
                damage * damage / (defender.getArmour() + 1)/ 100) + 2) *
                (random.nextDouble() + 1));
        defender.setHealth((defender.getHealth() - healthReduction));
        if (defender.getHealth() < 0) {
            defender.setHealth(0);
        }
        QueueProvider.offer(String.format(Define.strAttack001,healthReduction));
        QueueProvider.offer(String.format(Define.strAttack002,defender.getName(),defender.getHealth()));
        /*if (attacker instanceof Player) {
            QueueProvider.offer(String.format(Define.strAttack002,defender.getName(),defender.getHealth()));
        } else {
            QueueProvider.offer(String.format(Define.strAttack002,defender.getName(),defender.getHealth()));
        }*/
    }

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
        QueueProvider.offer(Define.strEquip001);
        String itemName = QueueProvider.take();
        if (!itemName.equalsIgnoreCase("back")) {
            player.dequipItem(itemName);
        }
    }

    private void viewStats() {
        QueueProvider.offer(Define.strView001);
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
