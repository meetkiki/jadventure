package com.jadventure.game.menus;

import com.jadventure.game.QueueProvider;
import com.jadventure.game.constant.Define;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All menus in JAdventure extend this class
 * Add MenuItems to menuItems, call displayMenu and you're happy
 */
/**
 *JAdventure中的所有菜单都扩展了这个类
 *将菜单项添加到菜单项中，调用displayMenu，您会很高兴
 */
public class Menus {
    protected List<MenuItem> menuItems = new ArrayList<>();
    protected Map<String, MenuItem> commandMap = new HashMap<String, MenuItem>();

    /**
     * 将(序号/菜单指令/小写的终级指令)转化为并添加指令
     * @auther zgn
     * @date  2022/8/29
     * @param m
     * @return com.jadventure.game.menus.MenuItem
     **/
    public MenuItem displayMenu(List<MenuItem> m) {
        int i = 1;
        for (MenuItem menuItem: m) {
            commandMap.put(String.valueOf(i), menuItem);
            commandMap.put(menuItem.getKey(), menuItem);
            for (String command: menuItem.getAltCommands()) {
                commandMap.put(command.toLowerCase(), menuItem);
            }
            i ++;
        }
        MenuItem selectedItem = selectMenu(m);
        return selectedItem;
    }

    // calls for user input from command line
    protected MenuItem selectMenu(List<MenuItem> m) {
        this.printMenuItems(m);
        String command = QueueProvider.take();//获取输入
        if (commandMap.containsKey(command.toLowerCase())) {
            return commandMap.get(command.toLowerCase());
        } else {
            QueueProvider.offer(String.format(Define.commandUnKnow,command));
            return this.displayMenu(m);
        }
    }
    /**
     * 按序号输出指令和菜单描述
     * @auther zgn
     * @date  2022/8/29
     * @param m
     **/
    private void printMenuItems(List<MenuItem> m) {
        int i = 1;
        for (MenuItem menuItem: m) {
            if (menuItem.getDescription() != null) {
                QueueProvider.offer("[" + i + "] " + menuItem.getCommand() + " - " + menuItem.getDescription());
            } else {
                QueueProvider.offer("[" + i + "] " + menuItem.getCommand());
            }
            i++;
        }
    }
}

