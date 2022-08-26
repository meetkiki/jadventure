package com.jadventure.game.menus;

import java.io.File;
import java.net.Socket;

import com.jadventure.game.DeathException;
import com.jadventure.game.Game;
import com.jadventure.game.GameModeType;
import com.jadventure.game.JAdventure;
import com.jadventure.game.QueueProvider;
import com.jadventure.game.constant.Define;
import com.jadventure.game.entities.Player;

/**
 * The first menu displayed on user screen
 * @see JAdventure
 * This menu lets the player choose whether to load an exiting game,
 * start a new one, or exit to the terminal.
 */
/**
 * 主菜单
 * @EngDesc Called when creating a new Player
 * @author  zgn
 * @date    2022/8/24 0024
 */
public class MainMenu extends Menus implements Runnable {

    public MainMenu(Socket server, GameModeType mode){
        QueueProvider.startMessenger(mode, server);
    }

    public MainMenu() {
        start();
    }

    public void run() {
        start();
    }

    public void start() {
        menuItems.add(new MenuItem(Define.commandStart, Define.strRecordStart, "new"));
        menuItems.add(new MenuItem(Define.commandLoad, Define.strRecordLoad));
        menuItems.add(new MenuItem(Define.commandDelete, Define.strRecordDelete));
        menuItems.add(new MenuItem(Define.commandExit, null, "quit"));

        boolean continuing = true;
        do {
            MenuItem selectedItem = displayMenu(menuItems);
            try {
                continuing = testOption(selectedItem);
            } catch (DeathException e) {
                if (e.getLocalisedMessage().equals("close")) {
                    continuing = false;
                }
            }
        } while(continuing);
        QueueProvider.offer(Define.strSysExit);
    }

    private static boolean testOption(MenuItem m) throws DeathException {
        String key = m.getKey();
        if(key.equals(Define.commandStart)){
            new ChooseClassMenu();
        }
        if(key.equals(Define.commandLoad)){
            loadProfileFromMenu();
        }
        if(key.equals(Define.commandDelete)){
            deleteProfileFromMenu();
        }
        if(key.equals(Define.commandExit)){
            QueueProvider.offer(Define.strSysGoodbye);
            return false;
        }
        return true;
    }

    private static void loadProfileFromMenu() throws DeathException {
        String key;
        if (isProfileDirEmpty()) {
            QueueProvider.offer(Define.strRecordNull);
            return;
        }
        Player player = null;
        do {
            listProfiles();
            QueueProvider.offer(Define.strRecordChioce);
            key = QueueProvider.take();
            if (key.equals("exit") || key.equals("back")) {
                return;
            } else if (Player.profileExists(key)) {
                player = Player.load(key);
            } else {
                QueueProvider.offer(Define.strRecordFindNull001);
            }
        } while (player == null);
        new Game(player, "old");
    }

    private static void deleteProfileFromMenu() {
        String key;
        while (true) {
            if (isProfileDirEmpty()) {
                QueueProvider.offer(Define.strRecordNull001);
                return;
            }
            listProfiles();
            QueueProvider.offer(Define.strRecordChioce001);
            key = QueueProvider.take();
            if ((key.equals("exit") || key.equals("back"))) {
                return;
            }
            if (Player.profileExists(key)) {
                String profileName = key;
                QueueProvider.offer(String.format(Define.strRecordDelete001,profileName));
                key = QueueProvider.take();
                if ((key.equals("exit") || key.equals("back"))) {
                    return;
                } else if (key.equals("y")) {
                    File profile = new File("json/profiles/" + profileName);
                    deleteDirectory(profile);
                    QueueProvider.offer(String.format(Define.strRecordDelete002,profileName));
                    return;
                } else {
                    QueueProvider.offer(String.format(Define.strRecordDelete003,profileName));
                }
            } else {
                QueueProvider.offer(Define.strRecordNull001);
            }
        }
    }

    private static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            for (File file : files) {
                if(file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return directory.delete();
    }

    private static boolean isProfileDirEmpty() {
        int numProfiles = new File("json/profiles").list().length;
        return (numProfiles == 0);
    }

    private static void listProfiles() {
        if (isProfileDirEmpty()) {
            QueueProvider.offer(Define.strRecordNull001);
            return;
        }
        File file = new File("json/profiles");
        String[] profiles = file.list();
        QueueProvider.offer(Define.strRecord);
        for (String name : profiles) {
            if (new File("json/profiles/" + name).isDirectory()) {
                QueueProvider.offer("  " + name);
            }
        }
    }
}
