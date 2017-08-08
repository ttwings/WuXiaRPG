package com.mygdx.game.rpgdata;


/**
 * Created by apple on 16/11/20.
 */
public class RpgSystem {
    public String title;
    public int versionId;       //
    public Terms terms;         //
    public int[] partyMembers;  //队伍
    public String currencyUnit; //货币单位
    public int[] windowTone;    //
    public String[] elements;   //
    public String[] skillTypes; //
    public String[] weaponTypes;//
    public String[] armorTypes; //
    public String[] switches;   //
    public String[] variables;  //
    public String   title1_name;//标题画面图像（背景）的文件名。
    public String   title2_name;//标题画面图像（边框）的文件名。
    public Boolean opt_draw_title;//绘制标题文字的开关。
    public int start_map_id;    //玩家初始位置的地图 ID。
    public int start_x; //玩家初始位置的地图 X 坐标。
    public int start_y; //玩家初始位置的地图 Y 坐标。
    public void init(){

    }

    class Terms{
        String[] basic;
        String[] params;
        String[] etypes;
        String[] commands;
        public void init(){
            basic = new String[8];
            params = new String[8];
            etypes = new String[5];
            commands = new String[23];
        }
    }
}
