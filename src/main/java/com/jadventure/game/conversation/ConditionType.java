package com.jadventure.game.conversation;
/**
 * 交流条件类型
 * @author  zgn
 * @date    2022/8/26 0026
*/
public enum ConditionType {
    NONE,//所有
    ALLY,//盟友
    ENEMY,//敌人
    LEVEL,//等级>=conditionParameter
    ITEM,//拥有conditionParameter 物品
    CHAR_TYPE//play.type
}
