package com.jadventure.game.conversation;


import com.jadventure.game.entities.NPC;
import com.jadventure.game.entities.Player;
import com.jadventure.game.menus.Menus;
import com.jadventure.game.menus.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private int id;
    private String playerPrompt;//玩家说的话
    private String text;//回复
    private ConditionType condition;//交流情况角色限制
    private String conditionParameter;
    private List<Integer> responses;//回复Line序号
    private ActionType action;//动作

    public Line(int id, String playerPrompt, String text, ConditionType condition,
            String conditionParameter, List<Integer> responses, ActionType action) {
        this.id = id;
        this.playerPrompt = playerPrompt;
        this.text = text;
        this.condition = condition;
        this.conditionParameter = conditionParameter;
        this.responses = responses;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getPlayerPrompt() {
        return playerPrompt;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public String getConditionParameter() {
        return conditionParameter;
    }

    public ActionType getAction() {
        return action;
    }
    /**
     *
     * @author  zgn
     * @date    2022/8/26 0026
     * @param	npc
     * @param	player
     * @param	lines
     * @return	com.jadventure.game.conversation.Line
     */
    public Line display(NPC npc, Player player, List<Line> lines) {
        if (responses.size() == 0) {
            return null;
        }
        List<MenuItem> responseList = new ArrayList<>();
        //加载对话,如果触发条件,载入界面对象
        for (Integer responseNum : responses) {
            Line response = lines.get(responseNum);
            if (ConversationManager.matchesConditions(npc, player, response)) {
                responseList.add(new MenuItem(response.getPlayerPrompt(), null));
            }
        }
        Menus responseMenu = new Menus();
        //进入界面对象,返回符合指令的对话
        MenuItem response = responseMenu.displayMenu(responseList);
        for (int responseNum : responses) {
            Line possibleResponse = lines.get(responseNum);
            if (possibleResponse.getPlayerPrompt().equals(response.getCommand())) {
                return possibleResponse;
            }
        }
        return null;
    }
}
