package com.jadventure.game.menus;

import com.jadventure.game.constant.Define;
import com.jadventure.game.entities.Player;
import com.jadventure.game.DeathException;
import com.jadventure.game.Game;
import com.jadventure.game.QueueProvider;

/**
 * Called when creating a new Player
 */
public class ChooseClassMenu extends Menus {

    public ChooseClassMenu() throws DeathException {
        this.menuItems.add(new MenuItem(Define.commandRecruit, "一名新入伍守卫西里亚城的士兵"));
        this.menuItems.add(new MenuItem(Define.commandSewerRat, "西里亚地下组织的成员"));

        while(true) {
            QueueProvider.offer(Define.narrator选择角色);
            MenuItem selectedItem = displayMenu(this.menuItems);
            if(testOption(selectedItem)) {
            	break;
            }
        }
    }

    private static boolean testOption(MenuItem m) throws DeathException {
        String key = m.getKey();
        if(key.equals(Define.commandRecruit)) {
            Player player = Player.getInstance("recruit");
            new Game(player, "new");
            return true;
        }
        if(key.equals(Define.commandSewerRat)) {
            Player player = Player.getInstance("sewerrat");
            new Game(player, "new");
            return true;
        }

        return false;

    }
}
