package com.mygdx.game.test;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.action.Attack;
import com.mygdx.game.action.AttackManager;
/**
 * Created by Administrator on 2016/11/10.
 */
public class Test {
    static Attack attack;
    static AttackManager attackManager =  AttackManager.getInstance();

    public static void main(String[] args){

        String atcFormat = "%s使出%s,打在%s身上，造成%d点伤害";
        attack = attackManager.getAttack("长拳");
        String atcStr;
        atcStr = String.format(atcFormat,"乔峰",attack.name,"铁头",attack.dmg);
        System.out.println(atcStr);
    }


    static int dice(String diceStr){
        int num,dice,dmg=0;
        String[] dices = diceStr.split("D");
        num = Integer.parseInt(dices[0]);
        dice = Integer.parseInt(dices[1]);
        for (int i=0;i<num;i++){
            dmg = dmg + MathUtils.random(1,dice);
//            Gdx.app.debug("dmg",dmg+"");
            System.out.println(dmg);
        }
        return num;
    }




    public static String speak(String sbeak ,String name,int count){
        String s;
        s= String.valueOf(System.out.printf(sbeak,name,count));
        return s;
    }

    static String[][] building(){
        String[][] building;
        String[] temp;
        int w,h;
        String build;
        build = "墙体 墙体 墙体 墙体 墙体\n" +
                "墙体 空气 空气 空气 墙体\n" +
                "墙体 空气 空气 空气 墙体\n" +
                "墙体 空气 空气 空气 窗户\n" +
                "墙体 空气 空气 空气 墙体\n" +
                "墙体 空气 空气 空气 墙体\n" +
                "墙体 空气 空气 空气 墙体\n" +
                "墙体 墙体 空气 墙体 墙体\n";

        temp = build.split("\n");
        h = temp.length;
        w = temp[0].split(" ").length;
        building = new String[h][w];
        System.out.println("h:"+h+"  w:"+w);

        for (int i=0;i<h;i++){
            building[i] = temp[i].split(" ");
        }

        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                System.out.print(building[i][j] + " ");
            }   System.out.println();
        }
        return building;
    }
}
