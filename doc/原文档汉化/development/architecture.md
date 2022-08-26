#建筑

##层

**警告**[2014.12.16]目前不存在，仍需实施。

有三层(http://en.wikipedia.org/wiki/Multitier_architecture):

-用户界面层（表示）
-游戏引擎层（逻辑层）
-持久层（数据层）

还有一个图[层架构](https://gitee.com/zgn_13200126222/jadventure/raw/master/doc/%E7%BB%93%E6%9E%84%E5%9B%BE/java%E7%B1%BB%E7%BB%93%E6%9E%84%E5%9B%BE.png)

这里最重要的类是_GameEngine_，因为它是[facade](http://en.wikipedia.org/wiki/Facade_pattern)为了比赛。
最重要的方法是_GameEngine_ is_execute（String commandText）_，它返回命令/操作的结果。

```
    GameEngine gameEngine = new GameEngine();
    String result = gameEngine.execute("get key");
    System.out.println(result);
```
