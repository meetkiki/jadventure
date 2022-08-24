![](https://gitee.com/zgn_13200126222/jadventure/raw/master/doc/%E6%B8%B8%E6%88%8F%E6%88%AA%E5%9B%BE/20220824_001.png "test")
==============================================




描述
-------------
JAdventure是基于Java（文本）的
[Role Playing Game](http://en.wikipedia.org/wiki/Role-playing_game) (RPG) -
[Single Player](http://en.wikipedia.org/wiki/Role-playing_game#Single-player).




项目背景
-------------
它最初是作为的项目创建的
[progether subreddit](http://www.reddit.com/r/progether)
并由Applzor, add7, geniuus, Malfunction, bdong\_, Qasaur, tamul。它被Hawk554重新激活。



项目状态
--------------
**在建**本项目处于alpha状态。玩家可以走路
通过游戏，找到物品并与一些 _有趣_ 人物战斗！



欢迎开发人员
----------------------
欢迎大家帮助创建JAdventure！

了解更多关于游戏和其中的想法的最佳方法是
来看看[Reddit - JAdventure](https://www.reddit.com/search?q=jadventure).

或者访问我们的[IRC channel reddit-progether](http://webchat.freenode.net/?channels=reddit-progether&uio=d4)

所有文档均可从[JAdventure wiki](https://github.com/progether/JAdventure/wiki)
以及从[JAdventure website](https://progether.github.io/JAdventure) 获得. 这些
构成项目文件和开发指南的大部分。

###贡献者###
 1. [Hawk554](https://github.com/hawk554)
 1. [projectdelphai](https://github.com/projectdelphai)
 1. [CageHN](https://github.com/CageHN)
 1. [kzisme](https://github.com/kzisme)
 1. [blackwolf12333](https://github.com/blackwolf12333)
 1. [MikesNorth](https://github.com/mikesnorth)
 1. [pthayer3](https://github.com/pthayer3)
 1. [Reinecker](https://github.com/reinecker)
 1. [tamul](https://github.com/tamul)
 1. [shkesar](https://github.com/shkesar)
 1. [paddatrapper](https://github.com/paddatrapper)
 1. [Dev-Osmium](https://github.com/Dev-Osmium)


开发或游戏设置游戏
-----------------------------------------------

###游戏安装

1.从下载游戏[此处](https://github.com/Progether/JAdventure/releases)
2.提取游戏文件
3.进入游戏目录并运行`java-jar jadventure XX。jar`（其中XX是版本）

###与Maven-开发人员一起运行
1.安装[Maven](http://maven.apache.com)
1.进入游戏目录并运行

    ```
    $mvn测试
    $mvn exec:java
    ```

玩游戏
----------------
要开始新游戏：

	start
要保存游戏：

	s
使用以下命令获取命令列表：

	h
要获取您周围的怪物列表：

	m
要查看有关玩家的详细信息：

	v s - 查看状态
    v e - 查看装备
    v b - 查看背包
要退出游戏：

	exit
移动：

	g n -向北
	g s - 向南
	g e - 向东
	g w - 向西
要拾取项目：

	p <itemName>
要删除项目：

	d <itemName>
装备/取消装备项目：

	e <itemName>
    ue <itemName>
攻击：

	a <monster>
环顾四周：

	la
要与非玩家角色交谈：

	t <npc>



编码标准/惯例/样式
--------------------------------
为了使代码更加可读、易懂和一致，每个
贡献者应遵循以下规定的指导原则。如果你不同意
对于某件事或遇到尚未确定的风格，做出
发布或拉取请求，分别讨论最佳样式。这个
标准将根据多数规则或官方文件确定
（例如oracle编码标准）。

 1. eg1
 1. eg2 

待优化
--------------------------------
    1.国际化:将中间字段统一存储替换_可参考codeGen,先使用Define.java
