package com.jadventure.game.junit;


import com.jadventure.game.QueueProvider;
import com.jadventure.game.constant.Define;
import com.jadventure.game.entities.NPC;
import com.jadventure.game.monsters.Goblin;
import org.junit.Test;

/**
 * @author zgn
 * @Description: simJunit
 * @date 2022/1/13 0013
 */
public class simJunit {
    @Test
    public void egMin() {//e.g.
        System.out.println();
    }

    @Test
    public void 对象equals_egMin() {//e.g.
        int playerLevel=2;
        Goblin test001 = new Goblin(playerLevel);
        test001.setHealth(0);
        Goblin test002 = new Goblin(playerLevel);
        System.out.println(test001.equals(test002));
    }

    @Test
    public void 字串换行输出_egMin() {//e.g.
        QueueProvider.offer(String.format(Define.strLocationsView,"暗廊","1,2,3","很长很暗的走廊"));
    }

    @Test
    public void egMax() {//e.g.
        //region 模拟数据
        System.out.println("--------------------Start--------------------");
        //endregion

        //region 数据处理
        System.out.println("--------------------Deal--------------------");
        //endregion

        //region 数据展示
        System.out.println("--------------------Done--------------------");
        //endregion
    }
}
