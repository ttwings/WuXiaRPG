package com.mygdx.game.rpgdata;

/**
 * Created by apple on 16/11/19.
 */
public class RpgClass {
    public int[] expParams;
    public int[] params;
    public int[] learnings;
    public int expForLevel(int level){
        int exp = 0;
        return exp;
    };
    public void init(){
        expParams = new int[4];
        params = new int[10];
        learnings = new int[10];
    }
}
