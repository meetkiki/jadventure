package com.jadventure.game.constant;

import com.jadventure.game.QueueProvider;
import lombok.Data;

/**
 * 命名规则 物+动作
 * @author  zgn
 * @date
 */
@Data
public class Define {

    ////zgnHelp 配置文件路径json/original_data/
    public static String configPath="json/cn_data/";//original_data;cn_data
    //指令
    public static String commandStart="开始";
    public static String commandLoad="读档";
    public static String commandDelete="删档";
    public static String commandExit="退出";

    public static String commandAttack="进攻";
    public static String commandDefend="防御";
    public static String commandEscape="逃跑";
    public static String commandEquip="调整装备";
    public static String commandUnequip="卸下装备";
    public static String commandView="视察";

    public static String commandRecruit="新兵";
    public static String commandSewerRat="盗贼";

    public static String strAttack="攻击[%s].";
    public static String strAttack001="造成[%s]伤害.";//22 damage dealt!
    public static String strAttack002="[%s]的血量是:%s.";//The Goblin's health is 39
    public static String strAttack003="致命一击!伤害加倍!";//The Goblin's health is 39
    public static String strAttacking="一只[%s]正在攻击你!";//A %s is attacking you!

    public static String strBackpack="背包";//Backpack:
    public static String strBackpackEdit="编辑"+strBackpack;//Edit backpack:

    public static String strBuy="从[%s]购买.";//"Buy from " + npc.getName();
    public static String strBuy001="%s物品:\t%s金币:%s";//Guide's items:	Guide's gold:100
    public static String strBuy002="售出给[%s].";//Sell to
    public static String strBuy003="你拥有[%s]金币.\n你想买点啥?";//You have " + player.getGold() + " gold coins.\nWhat do you want to buy?
    //zgnTodo 暂不清楚_test001
    public static String strBuy004="购买了一份[%s]消费了[%s].";//QueueProvider.offer("You have bought a " + item.getName() + " for " + item.getProperties().get("value") + " gold coins.");
    public static String strBuy005="还剩[%s]金币.";//QueueProvider.offer("You now have " + player.getGold() + " gold coins remaining.");
    public static String strBuy006="穷鬼走开!";//You do not have enough money!
    public static String strBuy007="[%s]不存在，或者此角色不拥有该物品.";//Either this item doesn't exist or this character does not own that item

    public static String strChioceRecord="选择要加载的配置文件。输入“后退”返回。";//\nSelect a profile to load. Type 'back' to go back.
    public static String strChioceRole="选择角色";

    public static String strDefend="抵御[%s]的进攻";
    public static String strDefend001="已经准备好抵抗[%s]了.";//You get ready to defend against the Goblin.
    public static String strDied="角色阵亡...重新开始?(y/n)";//You died... Start again? (y/n)
    public static String strDrop="对手丢了一个[%s].";//Your opponent dropped a

    public static String strEmpty="--空--";//--Empty--
    public static String strEquip="装备物品";
    public static String strEquip001="想要穿上什么装备?";//What item do you want to unequip?
    public static String strEquipped="你装备了[%s].";//equipped
    public static String strEquippedItems="装备:";//Equipped Items:
    public static String strEscape="试图从[%s]身后逃跑";//You have managed to escape the:
    public static String strEscape001="试图逃跑太多次了!";//You have tried to escape too many times!
    public static String strEscape002="[%s]:软弱!(逃跑失败)";//You failed to escape the:
    public static String strEXIT="退出";//EXIT!

    public static String strGoodbye="再见,冒险家!";//Goodbye!

    public static String strItemLevel="尚未到达使用物品的等级!";//You do not have the required level to use this item
    public static String strItems="发现物品:";//Items:
    public static String strItemsUse="想要是用什么装备?";//What item do you want to use?

    public static String strKilled="通过击杀[%s]\n你获得了[%s]经验以及[%s]金币";//You killed a \nYou have gained XP and gold

    public static String strLevelNow="当下人物等级:%s!";//You've are now level " + newLevel + "!

    public static String strNo="n";
    public static String strNPCs="NPCs:";//NPCs:
    public static String strNullItem="尚未拥有该物品!";//You do not have that item

    public static String strPickUp="你捡起了[%s].";//picked up
    public static String strPickUpNull="[%s]不存在.";//picked up
    public static String strPrompt="指令:";//\nPrompt:

    public static String strRecord="记录:";//Profiles:
    public static String strRecordStart="开始新的旅程";//Starts a new Game
    public static String strRecordLoad="加载存档";//Loads an existing Game
    public static String strRecordDelete="删除存档";//Deletes an existing Game

    public static String strSaveData="您的游戏数据已保存。";//\nYour game data was saved.
    public static String strSaveLocations="游戏位置已保存。";//The game locations were saved.
    public static String strSell="售出给[%s].";//Sell to

    public static String strToNorth="前往北方(n)";//To the North
    public static String strToSouth="前往南方(s)";//To the South
    public static String strToEast="前往东方(e)";//To the East
    public static String strToWest="前往西方(w)";//To the West
    public static String strToDown="向下";//Heading down
    public static String strToUp="向上";//Heading up

    public static String strUnequip="卸下物品";
    public static String strUnEquipped="你卸下了[%s].";//unequipped
    public static String strUnknowPrompt="不能识别的指令:%s!";//I don't know what %s means.

    public static String strView="视察自身人物的状态";
    public static String strView001="你的指令是?例如：查看状态（vs）、查看背包（vb）、查看装备（ve）";//\nWhat is your command? ex. View stats(vs), View Backpack(vb), View Equipment(ve)

    public static String strWelcome="欢迎回来,%s!";//Welcome back,%s!
    public static String strWelcome001="欢迎来到西里亚,%s.";//Welcome to Silliya,%s.

    public static String strYes="y";
    public static String strYourChioce="你的选择是?";//\nWhat is your choice?
}
