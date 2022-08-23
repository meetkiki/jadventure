package com.jadventure.game.constant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Define {
    //指令
    public static String commandStart="开始";
    public static String commandLoad="读档";
    public static String commandDelete="删档";
    public static String commandExit="退出";

    public static String commandRecruit="新兵";
    public static String commandSewerRat="卧底";
    //旁白
    public static String narrator选择角色="选择角色";
}
