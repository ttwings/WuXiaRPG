package com.mygdx.game.rpgdata;

/**
 * Created by apple on 16/11/20.
 */
public class RpgEventCommand {
    String code;//事件指令代码。
    int indent;//指令缩排深度。一般为0，每次使用「条件分歧」之类的指令就会增加 1。
    int[] parameters;//包含事件指令各项参数的数组，不同事件指令有不同内容。
}
