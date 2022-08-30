#改进思路

项目合作在[Reddit-Progether]进行(https://www.reddit.com/r/progether/)
在那里搜索[JAdventure](https://www.reddit.com/search?q=jadventure&sort=new).

或者访问我们的[IRC频道reddit progether](http://webchat.freenode.net/?channels=reddit-编程和uio=d4）

##建议

* 将LocationRepository重命名为WorldRepositoy，因为它包含游戏世界.
* 添加对多个游戏的支持
    这应该相对容易，因为游戏已经存储在磁盘上（json格式）.
    具体的解决方案可以是将每个游戏存储在单独的文件“world xxx.json”中.
    或者每个游戏都在它自己的目录中，比如game data/${world name}/world.json；项目.json；等
