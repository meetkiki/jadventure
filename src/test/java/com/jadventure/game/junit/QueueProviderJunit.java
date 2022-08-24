package com.jadventure.game.junit;


import com.jadventure.game.QueueProvider;
import org.apache.commons.configuration.Configuration;
import org.junit.Test;

/**
 * @author zgn
 * @Description: simJunit
 * @date 2022/1/13 0013
 */
public class QueueProviderJunit {
    @Test
    public void egMin() {//e.g.
        System.out.println();
    }

    @Test
    public void QueueProvider基础配置_egMin() {//e.g.
        Configuration config = QueueProvider.getConfig();
        System.out.println(config.getString("timestamp"));
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
