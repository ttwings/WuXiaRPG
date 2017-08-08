package com.mygdx.game.action;

/**
 * Created by Administrator on 2017/3/31.
 */
public class Attack {
    public int id;
    public String name;     //    门派
    public String faction;  //    分类
    public String type;     //    攻击ac 伤害dmg
    public int at;
    public String dmgStr;
    public int dmg;
    public String areaStr;  //    范围eg 点*1 米*3 线*4
    public int area;        //    内耗
    public int mp;          //    需求属性
    public String needStr1,needStr2;
    public int need1,need2,need3;           //    基础武学
    public String base1,base2,base3;        //    加成
    public String addStr1,addStr2,addStr3;
    public int add1,add2,add3;              //    效果
    public String[] effect;                 //    招式
    public String[] styles;
    public String attackVoc;                //攻击用语
    public String missVoc;                  //丢失用语
    public String criteVoc;                 //暴击用语
    public String styleVoc;                 //出招用语
    public String animation;                //动画
    public String sound;                    //音效
    public int lv;                          //等级
    public int exp;                         //熟练度0-1000 每100 熟练，等级提升1;
    public String book;                     //秘籍 可以通过阅读秘籍来提高熟练度
    public String describe;                 //描述
//0		1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17
//ID	武功名称	门派	分类	出招	伤害	范围	内耗	需求技能	需求内力	基础武学1	基础武学2	基础武学3	加成属性	加成技能	加成特殊	特效	招式
//1	长拳	无	拳	1	1D4	点D1	1	拳法D1	无	无	无	无	臂力*1	拳法*1	无	无	橫|冲|直|撞
    public Attack(){

    }

    public Attack(String[] datas){
        id = Integer.parseInt(datas[0]);
        name = datas[1];
        faction = datas[2];
        type = datas[3];
        at = Integer.parseInt(datas[4]);
        dmgStr = datas[5];
        dmg = 0;
        areaStr = datas[6].split("D")[0];
        area = Integer.parseInt(datas[6].split("D")[1]);
        mp = Integer.parseInt(datas[7]);
        needStr1 = datas[8].split("D")[0];
        needStr2 = datas[9].split("D")[0];
        //        needStr3 = datas[9].split("D")[0];
        //        if (!datas[8].equals("无"))
        need1 = Integer.parseInt(datas[8].split("D")[1]);
        need2 = Integer.parseInt(datas[9].split("D")[1]);
        //        need3 = Integer.parseInt(datas[9].split("D")[1]);
        base1 = datas[10];
        base2 = datas[11];
        base3 = datas[12];
        addStr1 = datas[13].split("D")[0];
        addStr2 = datas[14].split("D")[0];
        addStr3 = datas[15].split("D")[0];
        add1 = Integer.parseInt(datas[13].split("D")[1]);
        add2 = Integer.parseInt(datas[14].split("D")[1]);
        add3 = Integer.parseInt(datas[15].split("D")[1]);
        effect = datas[16].split(",");
        styles = datas[17].split("、");
        attackVoc = datas[18];
        missVoc = datas[19];
        criteVoc = datas[20];
        styleVoc = datas[21];
        animation = datas[22];
        sound = datas[23];
        book = datas[24];
        describe = datas[25];
    }
}
