package com.jadventure.game.menus;

import com.jadventure.game.constant.Define;
import com.jadventure.game.entities.Player;
import com.jadventure.game.DeathException;
import com.jadventure.game.Game;
import com.jadventure.game.QueueProvider;

/**
 * 角色选择
 * @EngDesc Called when creating a new Player
 * @author  zgn
 * @date    2022/8/24 0024
*/
public class ChooseClassMenu extends Menus {

    public ChooseClassMenu() throws DeathException {
        this.menuItems.add(new MenuItem(Define.commandRecruit, "一名新入伍守卫西里亚城的士兵"));
        this.menuItems.add(new MenuItem(Define.commandSewerRat, "西里亚地下组织的成员"));

        while(true) {
            QueueProvider.offer(Define.strRoleChioce);
            MenuItem selectedItem = displayMenu(this.menuItems);
            if(testOption(selectedItem)) {
            	break;
            }
        }
    }
    /**
     * 依据菜单做出执行判断
     * @auther zgn
     * @date  2022/8/29
     * @param m 菜单
     * @return boolean
     **/
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
