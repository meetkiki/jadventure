package com.jadventure.game.navigation;

/**
 * An enumeration with four properties: A description for in-game text and three integers.
 * The integers are added to a coordinate to get the new direction (i.e. to get a north coordinate
 * you add 1 to the y property of a coordinate).
 */

import com.jadventure.game.constant.Define;

/**
 *具有四个属性的枚举：游戏内文本的描述和三个整数。
 *将整数添加到坐标以获得新方向（即获得北坐标将1添加到坐标的y属性）。
 */
public enum Direction {
    NORTH(Define.strToNorth, 0, 1, 0),
    SOUTH(Define.strToSouth, 0, -1, 0),
    EAST(Define.strToEast, 1, 0, 0),
    WEST(Define.strToWest, -1, 0, 0),
    DOWN(Define.strToDown, 0, 0, -1),
    UP(Define.strToUp, 0, 0, 1);

    private final String description;
    private final int dx;
    private final int dy;
    private final int dz;

    private Direction(String description, int dx, int dy, int dz) {
        this.description = description;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public String getDescription() {
        return description;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getDz() {
        return dz;
    }
}
