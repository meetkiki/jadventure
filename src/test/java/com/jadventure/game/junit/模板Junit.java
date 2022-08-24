package com.jadventure.game.junit;


import org.junit.Test;

/**
 * @author zgn
 * @Description: simJunit
 * @date 2022/1/13 0013
 */
public class 模板Junit {
    @Test
    public void egMin() {//e.g.
        System.out.println();
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
