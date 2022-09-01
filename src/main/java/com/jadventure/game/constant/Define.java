package com.jadventure.game.constant;

import lombok.Data;

/**
 * 命名规则 物+动作
 * @author  zgn
 * @date
 */
@Data
public class Define {

    //zgnHelp 配置文件路径json/original_data/
    public static String configPath="json/cn_data/";//original_data;cn_data

    public static String command="指令:";//\nPrompt:
    public static String commandStart="开始";
    public static String commandLoad="读档";
    public static String commandDelete="删档";
    public static String commandExit="退出";
    public static String commandAttack="进攻";
    public static String commandDefend="防御";
    public static String commandEscape="逃跑";
    public static String commandEquip="调整装备";
    public static String commandUnEquip="卸下装备";
    public static String commandUnKnow="不能识别的指令:%s!";//I don't know what %s means.
    public static String commandView="视察";
    public static String commandRecruit="新兵";
    public static String commandSewerRat="盗贼";
    public static String commandSyndicate="辛迪加成员";
    public static String commandBrotherhood="兄弟会成员";

    public static String strBackpack="背包";//Backpack:
    public static String strBackpackEdit="编辑"+strBackpack;//Edit backpack:

    public static String strBattle="攻击[%s].";
    public static String strBattle001="造成[%s]伤害.";//22 damage dealt!
    public static String strBattle002="[%s]的血量是:%s.";//The Goblin's health is 39
    public static String strBattle003="致命一击!伤害加倍!";//The Goblin's health is 39
    public static String strBattle004="你附近的怪物:";//Monsters around you:
    public static String strBattle005="附近没有怪物了";//There are no monsters around you'n
    public static String strBattleing="[%s]正在攻击你!";//A %s is attacking you!

    public static String strDataSave="您的游戏数据已保存.";//\nYour game data was saved

    public static String strEntityHead="头";
    public static String strEntityChest="胸部";
    public static String strEntityLeftArm="左臂";
    public static String strEntityLeftHand="左手";
    public static String strEntityRightArm="右臂";
    public static String strEntityRightHand="右手";
    public static String strEntityBothHands="两手";
    public static String strEntityBothArms="双臂";
    public static String strEntityLegs="腿";
    public static String strEntityFeet="脚";

    public static String strFoeDrop="掉落了一个[%s].";//Your opponent dropped a// .
    public static String strFoeNull="未发现敌人.";//Opponent not found

    public static String strItemsDrop="丢弃了[%s].";//item.getName() + " dropped
    public static String strItemsEmpty="--空--";//--Empty--
    public static String strItemsEquip="装备物品";
    public static String strItemsEquip001="想要穿上什么装备?";//What item do you want to unequip?
    public static String strItemsEquip002="装备:";//Equipped Items:
    public static String strItemsEquipped="你装备了[%s].";//equipped
    public static String strItemsFind="发现物品:";//Items:
    public static String strItemsFindNull="尚未拥有该物品!";//You do not have that item
    public static String strItemsLevel="尚未到达使用物品的等级!";//You do not have the required level to use this item
    public static String strItemsNull="Invalid item name";
    public static String strItemsNull001="参数'id'的值为'%s',未在存储库中找到.";//Argument 'id' with value '生肉' not found in repository.
    public static String strItemsUnEquip="卸下物品";
    public static String strItemsUnEquip001="你卸下了[%s].";//unequipped
    public static String strItemsUse="想要是用什么装备?";//What item do you want to use?
    public static String strItemsView="名称: %s\n" +
                                        "描述: %s\n" +
                                        "要求等级: %s.";//Item doesn't exist within your view
    public static String strItemsViewNull="未发现物品.";//Item doesn't exist within your view

    public static String strLocationsSave="游戏位置已保存.";//The game locations were saved.
    public static String strLocationsView="%s{%s}:\n    %s";//The game locations were saved.

    public static String strNPCs="NPC:";//NPCs:
    public static String strNPCsTalkError="不能与[%s]对话.";//Unable to talk to " + arg

    public static String strRecord="记录:";//Profiles:
    public static String strRecordChioce="选择要加载的配置文件.输入'后退'返回.";//\nSelect a profile to load. Type 'back' to go back.
    public static String strRecordChioce001="选择要删除的配置文件.输入'后退'返回.";
    public static String strRecordDelete="删除存档";//Deletes an existing Game
    public static String strRecordDelete001="确定要删除[%s]?(y/n)";
    public static String strRecordDelete002="存档[%s]已经删除.";
    public static String strRecordDelete003="取消删除存档[%s]";
    public static String strRecordFindNull="找不到[%s]记录.";//Unable to open file '" + fileName
    public static String strRecordFindNull001="找到不到存档,请重试.";
    public static String strRecordLoad="加载存档";//Loads an existing Game
    public static String strRecordNull="没有存档,请开始新的游戏.";
    public static String strRecordNull001="没有存档!";
    public static String strRecordSaveError="无法保存[%s]记录.";//Unable to save to file '" + fileName
    public static String strRecordStart="开始新的旅程";//Starts a new Game

    public static String strRoleChioce="选择角色";
    public static String strRoleChioce001="你的选择是?";//\nWhat is your choice?
    public static String strRoleChioce002="无效角色";//Not a valid class
    public static String strRoleDefend="抵御[%s]的进攻";
    public static String strRoleDefend001="已经准备好抵抗[%s]了.";//You get ready to defend against the Goblin.
    public static String strRoleDied="角色阵亡...重新开始?(y/n)";//You died... Start again? (y/n)
    public static String strRoleEdit="您的主职业现在已更改!你现在是[%s]!";//You're character type is now changed! You are now a 兄弟会成员!
    public static String strRoleEscape="试图从[%s]身后逃跑";//You have managed to escape the:
    public static String strRoleEscape001="试图逃跑太多次了!";//You have tried to escape too many times!
    public static String strRoleEscape002="[%s]:软弱!(逃跑失败)";//You failed to escape the:
    public static String strRoleHealth="血量已达最大值";//Maximum health must be possitive
    public static String strRoleHealth001="血量健康";//Health must be possitive
    public static String strRoleLevel="当下人物等级:%s!";//You've are now level " + newLevel + "!
    public static String strRolePick="你捡起了[%s].";//picked up
    public static String strRolePickNull="[%s]不存在.";//picked up
    public static String strRoleView="视察自身人物的状态";
    public static String strRoleView001="玩家昵称: %s\n" +
            "职业: %s\n" +
            "当前武器: %s\n" +
            "金币: %s\n" +
            "当前生命值/最大值: %s/%s\n" +
            "伤害/护甲: %s/%s\n" +
            "力量: %s\n" +
            "智力: %s\n" +
            "敏捷: %s\n" +
            "幸运: %s\n" +
            "精神: %s\n" +
            "经验: %s\n" +
            "等级: %s";
    public static String strRoleWin="通过击杀[%s]\n你获得了[%s]经验以及[%s]金币";//You killed a \nYou have gained XP and gold

    public static String strSys001="必须使用测试配置文件进行调试";//Must be using test profile to debug
    public static String strSysExit="退出";//EXIT!
    public static String strSysGoodbye="再见,冒险家!";//Goodbye!
    public static String strSysHelp="\nlist:列出玩家当前拥有的物品\n" +
            "add：添加物品\n"+
            "remove：删除物品\n"+
            "help：打印'帮助'\n"+
            "exit：退出背包调试菜单\n";
    public static String strSysLine="----------------------------";
    public static String strSysNo="n";
    public static String strSysRole="测试盗贼_xdh";//测试玩家账号;可以调用debug的指令 ;测试新兵;测试盗贼_xdj;测试盗贼_xdh;test
    public static String strSysView="你的指令是?例如：查看状态（vs）、查看背包（vb）、查看装备（ve）";//\nWhat is your command? ex. View stats(vs), View Backpack(vb), View Equipment(ve)
    public static String strSysWelcome="欢迎回来,%s!";//Welcome back,%s!
    public static String strSysWelcome001="欢迎来到西里亚,%s.";//Welcome to Silliya,%s.
    public static String strSysYes="y";

    public static String strToNorth="前往北方(n)";//To the North
    public static String strToSouth="前往南方(s)";//To the South
    public static String strToEast="前往东方(e)";//To the East
    public static String strToWest="前往西方(w)";//To the West
    public static String strToDown="向下(d)";//Heading down
    public static String strToUp="向上(u)";//Heading up
    public static String strToError="不能穿墙.";//You cannot walk through walls.
    public static String strToError001="走到尽头了.";//The is no exit that way.
    public static String strToError002="错误的方向指令.";//That direction doesn't exist
    public static String strToError003="错误的地图位置.";//Heading up

    public static String strTradeBuy="从[%s]购买.";//"Buy from " + npc.getName();
    public static String strTradeBuy001="%s物品:\t%s金币:%s";//Guide's items:    Guide's gold:100
    public static String strTradeBuy002="售出给[%s].";//Sell to
    public static String strTradeBuy003="你拥有[%s]金币.\n你想买点啥?";//You have " + player.getGold() + " gold coins.\nWhat do you want to buy?
    public static String strTradeBuy004="购买了一份[%s]消费了[%s].";//QueueProvider.offer("You have bought a " + item.getName() + " for " + item.getProperties().get("value") + " gold coins.");
    public static String strTradeBuy005="还剩[%s]金币.";//QueueProvider.offer("You now have " + player.getGold() + " gold coins remaining.");
    public static String strTradeBuy006="[%s]金币不够!";//You do not have enough money!
    public static String strTradeBuy007="[%s]不存在,或者此角色不拥有该物品.";//Either this item doesn't exist or this character does not own that item
    public static String strTradeBuy008="- %s: 每%s个%s金币\n";//- 生命药剂 : 1 at 50 gold coins each
    public static String strTradeBuy009="- %s: 每%s个%s金币\n";//- 生命药剂 : 1 at 50 gold coins each
    public static String strTradeCurrency="[%s]的金币:%s,[%s]拥有的物品:\n";
    public static String strTradeCurrency001="[%s]还剩%s金币.";
    public static String strTradeCurrencySell="[%s]的金币:%s,\n你想卖什么?";
    public static String strTradeSell="售出给[%s].";//Sell to
    public static String strTradeSelling="[%s]以%s金币售出了[%s].";

}
