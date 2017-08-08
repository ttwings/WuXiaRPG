package com.mygdx.game.rpgdata;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by apple on 16/11/19.
 */
public class RpgBaseItem extends Actor{
    public int id;
    public String name;
    public int iconIndex;
    public String description; //说明
    public int[] features; //特征
    public String note;    //备注
    public void init(){
        id = 0;
        name = "";
        iconIndex = 0;
        description = "";
        features = new int[0];
        note = "";
    }
}
