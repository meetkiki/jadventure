package com.jadventure.game.prompts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.TreeMap;

import com.jadventure.game.DeathException;
import com.jadventure.game.QueueProvider;
import com.jadventure.game.constant.Define;
import com.jadventure.game.entities.Player;

/**
 * CommandParser parses the game commands
 *
 * It parses all the commands automatically.
 * To add a new command, you just need to make addition in the CommandCollection.
 */
public class CommandParser {
    Player player;
    private TreeMap<String, Method> commandMap;

    public CommandParser(Player player){
        this.player = player;
        commandMap = new TreeMap<String, Method>();
        initCommandMap();
    }

    // adds the command to the commandMap
    private void initCommandMap() {
        Method[] methods = CommandCollection.class.getMethods();

        for(Method method: methods){
            if (!method.isAnnotationPresent(Command.class)) {
                continue;
            }
            Command annotation = method.getAnnotation(Command.class);
            this.commandMap.put(annotation.command(), method);
            for(String alias : annotation.aliases()){
                if (alias.length() == 0) {
                    break;
                }
                this.commandMap.put(alias, method);
            }
        }
    }
    /**
     * 解析玩家指令
     * @author  zgn
     * @date    2022/8/24 0024
     * @param	player 玩家
     * @param	userCommand 指令
     * @return	boolean
     */
    public boolean parse(Player player, String userCommand) throws DeathException {
        CommandCollection com = CommandCollection.getInstance();
        com.initPlayer(player);

        if (userCommand.equals("exit")) {
            return false;
        }

        String command = removeNaturalText(userCommand);

        // descendingKeySet otherwise startsWith will return correspond to longer command
        // e.g. 'de' will match startWith('d')
        for (String key : commandMap.descendingKeySet()) {
            if (command.startsWith(key)) {
                Method method = commandMap.get(key);
                if (method.getParameterTypes().length == 0){
                    if (command.equals(key)) {
                        try {
                            if (method.getAnnotation(Command.class).debug()) {
                                if (Define.strSysRole.equals(player.getName())) {
                                    method.invoke(com);
                                } else {
                                    QueueProvider.offer(Define.strSys001);
                                }
                            } else {
                                method.invoke(com);
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            if (e.getCause() instanceof DeathException) {
                                throw (DeathException) e.getCause();
                            } else {
                                e.getCause().printStackTrace();
                            }
                        }
                    } else {
                        QueueProvider.offer(String.format(Define.commandUnKnow,userCommand));
                        return true;
                    }
                } else if (method.getParameterTypes()[0] == String.class) {
                    String arg = command.substring(key.length()).trim();
                    try {
                        if (method.getAnnotation(Command.class).debug()) {
                            if (Define.strSysRole.equals(player.getName())) {
                                method.invoke(com, arg);
                            } else {
                                QueueProvider.offer(Define.strSys001);
                            }
                        } else {
                            method.invoke(com, arg);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        if (e.getCause() instanceof DeathException) {
                            throw (DeathException) e.getCause();
                        } else {
                            e.getCause().printStackTrace();
                        }
                    }
                }
                return true;
            }
        }
        QueueProvider.offer(String.format(Define.commandUnKnow,userCommand));
        return true;
    }

    /**
     * 去除自然语法,eg 一只(a),到(to)
     * @auther zgn
     * @date  2022/8/29
     **/
    private String removeNaturalText(String command) {
        command = command.replaceAll(" to ", " ");
        command = command.replaceAll(" a ", " ");
        return command;
    }
}
