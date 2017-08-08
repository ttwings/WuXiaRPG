package com.mygdx.game.rpgdata;

/**
 * Created by apple on 16/11/19.
 */
public class RpgActor extends RpgBaseItem {
    public String nickname;
    public int classID;
    public int initLevel;
    public int maxLevel;
    public String characterName;
    public int characterIndex;
    public String faceName;
    public int faceIndex;
    public int[] equips;
    public void init(){
        nickname = "";
        classID = 0;
        initLevel = 0;
        maxLevel = 99;
        characterName = "";
        characterIndex = 0;
        faceName = "";
        faceIndex = 0;
        equips = new int[4];
    }
}
