package com.mygdx.game.rpgdata;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.HashMap;

/**
 * Created by apple on 16/11/20.
 */
public class RpgMap {
    public String displayName; //地图显示名。
    public int tilesetId;   //地图使用的图块组 ID。
    public int width;       //地图宽度。
    public int height;      //地图高度。
    public int scrollType;  //循环方式（0：不循环、1：纵向循环、2：横向循环、3：纵横循环）。
    public boolean specifyBattleback;  //是否使用战斗背景图像。
    public String battleback1Name;     //使用战斗背景图像时地板图像的文件名。
    public String battleback2Name;     //使用战斗背景图像时墙壁图像的文件名。
    public boolean autoplayBgm;    //是否自动切换背景音乐。
    public String bgm;              //自动切换背景音乐时对应的背景音乐（RPG::BGM）。
    public boolean autoplayBgs;    //是否自动切换背景声音。
    public String bgs;              //自动切换背景声音时对应的背景声音（RPG::BGS）。
    public boolean disableDashing; //是否禁止跑步。
    public int[] encounterList;    //遇敌列表。RPG::Map::Encounter 的数组。
    public int encounterStep;      //平均遇敌步数。
    public String parallaxName;    //远景图像文件名。
    public boolean parallaxLoopX; //远景是否启用了[横向循环]选项。
    public boolean parallaxLoopY; //远景是否启用了[纵向循环]选项。
    public int parallaxSx;         //远景横向自动卷动的速度。
    public int parallaxSy;         //远景纵向自动卷动的速度。
    public boolean parallaxShow;   //远景是否启用了[在地图编辑器中显示]选项。
    public String note;             //备注。
    public TiledMap data;           //地图数据。图块 ID 及其附属数据的三维数组。（Table）。
    public HashMap<String, String> events;  //地图事件。以事件 ID 为主键，RPG::Event 的实例为值的哈希表。


}
