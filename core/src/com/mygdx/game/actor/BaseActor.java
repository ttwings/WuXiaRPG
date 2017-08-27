package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.action.ActorTextureComponent;
import com.mygdx.game.manager.EnumAction;
/**
 * Created by ttwings on 2016/11/18.
 */
public class BaseActor extends Actor{
    /**
     * ID	姓名	称号	世家	国家	信仰	性别	门派	等级	历练	声望	行动点数	臂力	根骨	身法	悟性	五感	福缘	综合	气血	真气	角色图	行走图索引	头像	肖像索引	坐标x	坐标y	故事
     * 1	段誉	傻小子	大理皇子	大理	佛	男	大理皇族	1	0	0		10	14	14	16	12	16	82	100	100	角色1		Face00000.png		20	20	逃出家门的傻小子
     */
    public int rx, ry; // 区域坐标
    public int lx, ly; // 本地图坐标 0-128
    public int exp; // 江湖历练
    public int reputation; // 声望
    public int speed; // 移动速度
    public int jump; // 跳跃高度
    public int ap; // 行动点数
    public int Str; // 臂力
    public int Con; // 根骨
    public int Dex; // 身法
    public int Int; // 悟性
    public int Wis; // 五感
    public int Cha; // 福缘
    public int exStr, exCon, exDex, exInt, exWis, exCha; // 属性调整值
    public int AC; // 防御等级
    public int FA; // 招式，同理 dnd 中的先攻
    public int exDamage; // 伤害
    public int baseHP; // 基本生命
    public int HP; // 气血
    public int MP; // 真气
    public int AP;
    public int maxAP;
    public int maxMP;
    public int DHP; // 生命骰
    public int maxHP; // 最大生命
    public int LV; // 等级
    public int actionInputIndex; // 输入中的战斗行动编号
    public String actionStr;
    public EnumAction enumAction;
    public EnumAction faceTo;
    public int turn;
    //	执行动作测试
    public String[] actions;
    public int actionIndex;
    public String skill; // 装备技能
    public String target; // 锁定目标
    public boolean isCall;
    public String call;
    public int busy;
    /**
     * 31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47	48	49	50	51	52	53	54	55	56	57	58	59	60	61	62	63	64	65	66	67	68	69	70	71	72	73	74	75	76	77	78
     * actionStr	target	thirsty	hunger	tired	pain	credit	money	debuff1	debuff2	debuff3	debuff4	debuff5	debuff6	state1	state2	state3	state4	equip1	equip2	equip3	equip4	equip5	equip6	skill1	skill2	skill3	skill4	skill5	skill6	skill7	skill8	skill9	skill10	skill11	skill12	item1	item2	item3	item4	item5	item6	item7	item8	item9	item10	item11	item12
     * 行动	目标	口渴	饥饿	疲劳	疼痛	存款	金钱	冰封	灼烧	流血	中毒	封穴	内伤	状态1	状态2	状态3	状态4	头戴	背披	身穿	手握	腰挂	足踩	技能1	技能2	技能3	技能4	技能5	技能6	技能7	技能8	技能9	技能10	技能11	技能12	物品1	物品2	物品3	物品4	物品5	物品6	物品7	物品8	物品9	物品10	物品11	物品12
     */
    public int thirsty;
    public int hunger;
    public int tired;
    public int pain;
    public int credit;
    public int money;
    public String[] debuffs;
    public String[] states;
    public String[] equips;
    public String[] skills;
    public String[] items;
    public TableData tableDate;
    public TextureRegion textureRegion;
    public String objName;
    public ActorTextureComponent actorTextureComponent;
    public BaseActor(TableData tableDate) {


        this.tableDate = tableDate;
        actorTextureComponent = new ActorTextureComponent(this);
        actorTextureComponent.init();
        // age= 0; // 年龄
        // h= 0; // 身高
        exp = 0; // 经验
        reputation = 0; // 声望
        speed = 0; // 移动速度
        jump = 0; // 跳跃高度
        ap = 0; // 行动点数

        Str = 0; // 臂力
        Con = 0; // 体质
        Dex = 0; // 身法
        Int = 0; // 悟性
        Wis = 0; // 五感
        Cha = 0; // 福缘
        exStr = 0;
        exCon = 0;
        exDex = 0;
        exInt = 0;
        exWis = 0;
        exCha = 0; // 属性调整值
        AC = 0; // 防御等级
        FA = 0; // 招式，同理 dnd 中的先攻
        exDamage = 0; // 伤害
        baseHP = 10; // 基本生命
        maxHP = Integer.parseInt(tableDate.get("气血")); // 最大生命
        maxMP = Integer.parseInt(tableDate.get("真气"));
        maxAP = Integer.parseInt(tableDate.get("精力"));
        HP = maxHP; // 生命值
        MP = maxMP;// 内力值
        AP = maxAP;
        DHP = 10; // 生命骰

        LV = 0; // 等级
        actionInputIndex = 0; // 输入中的战斗行动编号
//        lastSkill = ""; // 光标记忆用 : 技能
//        actionStr = "";
        skill = "";
        target = "";
        call = "";
        isCall = false;
//        busy = 0;
        objName = "";
//        actions = new String[]{"闲逛", "战斗", "整理", "专长"};
        //			动作测试
        actions = new String[]{"闲逛", "战斗", "整理", "专长"};
        actionIndex = 0;
        actionStr = actions[actionIndex];
        enumAction = EnumAction.MOVE_E;
        faceTo = EnumAction.FACE_E;
        call = "";
        busy = 0;
        turn = 0;
        speed = 4;
        items = new String[12];
        items[0] = tableDate.get("物品1");
        items[1] = tableDate.get("物品2");
        items[2] = tableDate.get("物品3");
        items[3] = tableDate.get("物品4");
        items[4] = tableDate.get("物品5");
        items[5] = tableDate.get("物品6");
        items[6] = tableDate.get("物品7");
        items[7] = tableDate.get("物品8");
        items[8] = tableDate.get("物品9");
        items[9] = tableDate.get("物品10");
        items[10] = tableDate.get("物品11");
        items[11] = tableDate.get("物品12");
//        手握	头戴	背披	身穿	腰挂	足踩
        equips = new String[6];
        equips[0] = tableDate.get("手握");
        equips[1] = tableDate.get("头戴");
        equips[2] = tableDate.get("背披");
        equips[3] = tableDate.get("身穿");
        equips[4] = tableDate.get("腰挂");
        equips[5] = tableDate.get("足踩");
        skills = new String[12];
        skills[0] = tableDate.get("技能1");
        skills[1] = tableDate.get("技能2");
        skills[2] = tableDate.get("技能3");
        skills[3] = tableDate.get("技能4");
        skills[4] = tableDate.get("技能5");
        skills[5] = tableDate.get("技能6");
        skills[6] = tableDate.get("技能7");
        skills[7] = tableDate.get("技能8");
        skills[8] = tableDate.get("技能9");
        skills[9] = tableDate.get("技能10");
        skills[10] = tableDate.get("技能11");
        skills[11] = tableDate.get("技能12");
        actionStr = skills[0];
    }
    public void move(int x, int y) {
        setX(getX() + x * speed);
        setY(getY() + y * speed);
        lx = (int) getX() / 16;
        ly = (int) getY() / 16;
        turn++;
//		setX(lx);
//		setY(ly);
    }

    public void indexAction(int d) {
        int length = actions.length;
        if ((actionIndex + d) < 0) {
            actionIndex = -(actionInputIndex + d);
        } else {
            actionIndex = actionInputIndex + d;
        }

    }

    public boolean isBusy() {
        return busy > 0;
    }

    public void updataBusy() {
        if (busy > 0) {
            busy--;
        }
    }

    public void updata() {
        if (actionIndex < 0) {
            actionIndex = 0;
        } else if (actionIndex > actions.length - 1) {
            actionIndex = actions.length - 1;
        }
        actionStr = actions[actionIndex];
        updataBusy();
        actorTextureComponent.updata();
    }

    //	public void updata(MapLocal mapLocal, SpriteBatch batch){
//		batch.draw(this,getX(),getY());
//	}
    public void put(String property, Object value) {
        tableDate.put(property, value);
    }

    public String get(String property) {
        return tableDate.get(property);
    }
}
