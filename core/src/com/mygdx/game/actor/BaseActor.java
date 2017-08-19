package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.manager.Cache;
import com.mygdx.game.manager.EnumAction;
import com.mygdx.game.map.MapLocal;

/**
 * Created by ttwings on 2016/11/18.
 */
public class BaseActor extends Image{
	/**
	 * ID	姓名	称号	世家	国家	信仰	性别	门派	等级	历练	声望	行动点数	臂力	根骨	身法	悟性	五感	福缘	综合	气血	真气	角色图	行走图索引	头像	肖像索引	坐标x	坐标y	故事
	 1	段誉	傻小子	大理皇子	大理	佛	男	大理皇族	1	0	0		10	14	14	16	12	16	82	100	100	角色1		Face00000.png		20	20	逃出家门的傻小子
	 */
	public int ID;
	public String name; // 名字
	public String nickname; // 称号
	public String characterName; // 行走图文件名
	public int characterIndex; // 行走图索引
	public String faceName; // 肖像文件名
	public int faceIndex; // 肖像索引
	public String region; // 区域名
	public int rx, ry; // 区域坐标
	public int lx,ly; // 本地图坐标 0-128
	public String family; // 世家
	public String country; // 国家
	public String camp; // 阵营
	public String faith; // 信仰
	public String sex; // 性别
	public String className; // 师从 门派
	// public int age; // 年龄
	// public int h; // 身高
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
	public int maxMP;
	public int DHP; // 生命骰
	public int maxHP; // 最大生命
	public int LV; // 等级
	public int actionInputIndex; // 输入中的战斗行动编号
	public String lastSkill; // 光标记忆用 : 技能
	public String story;
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
	 *  31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47	48	49	50	51	52	53	54	55	56	57	58	59	60	61	62	63	64	65	66	67	68	69	70	71	72	73	74	75	76	77	78
	 actionStr	target	thirsty	hunger	tired	pain	credit	money	debuff1	debuff2	debuff3	debuff4	debuff5	debuff6	state1	state2	state3	state4	equip1	equip2	equip3	equip4	equip5	equip6	skill1	skill2	skill3	skill4	skill5	skill6	skill7	skill8	skill9	skill10	skill11	skill12	item1	item2	item3	item4	item5	item6	item7	item8	item9	item10	item11	item12
	 行动	目标	口渴	饥饿	疲劳	疼痛	存款	金钱	冰封	灼烧	流血	中毒	封穴	内伤	状态1	状态2	状态3	状态4	头戴	背披	身穿	手握	腰挂	足踩	技能1	技能2	技能3	技能4	技能5	技能6	技能7	技能8	技能9	技能10	技能11	技能12	物品1	物品2	物品3	物品4	物品5	物品6	物品7	物品8	物品9	物品10	物品11	物品12
	 */
	public int thirsty;
	public int hunger;
	public int tired;
	public int pain;
	public int credit;
	public int money;
	public String[] debuffs;
	public String[]	states;
	public String[] equips;
	public String[] skills;
	public String[] items;
	public TableData tableDate;
	public TextureRegion textureRegion;
	public String objName;

	public BaseActor() {
		name = ""; // 名字
		nickname = ""; // 称号
		characterName = ""; // 行走图文件名
		characterIndex = 0; // 行走图索引
		faceName = ""; // 肖像文件名
		faceIndex = 0; // 肖像索引
		family = ""; // 世家
		camp = ""; // 阵营
		country = "";
		faith = ""; // 信仰
		sex = ""; // 性别
		className = ""; // 职业 ID
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
		baseHP = 0; // 基本生命
		HP = 0; // 生命值
		MP = 0; // 内力值
		DHP = 0; // 生命骰
		maxHP = 0; // 最大生命
		LV = 0; // 等级
		actionInputIndex = 0; // 输入中的战斗行动编号
		lastSkill = ""; // 光标记忆用 : 技能
		actionStr = "";
		skill = "";
		target = "";
		call = "";
		isCall = false;
		busy = 0;
		objName = "";
	}

	public BaseActor(String[] datas){
			ID = Integer.parseInt(datas[0]);
			name =datas[1];
			nickname = datas[2];
			family = datas[3];
			country = datas[4];
			faith = datas[5];
			sex = datas[6];
			className = datas[7];
			LV = Integer.parseInt(datas[8]);
			exp = Integer.parseInt(datas[9]);
			reputation = Integer.parseInt(datas[10]);
			ap = Integer.parseInt(datas[11]);
			Str = Integer.parseInt(datas[12]);
			exStr = (Str - 8)/2;
			Con = Integer.parseInt(datas[13]);
			exCon = (Con - 8)/2;
			Dex = Integer.parseInt(datas[14]);
			exDex = (Dex - 8)/2;
			Int = Integer.parseInt(datas[15]);
			exInt = (Int - 8)/2;
			Cha = Integer.parseInt(datas[16]);
			exCha = (Cha - 8)/2;
			Wis = Integer.parseInt(datas[17]);
			exWis = (Wis - 8)/2;
			// 综合	气血	真气	角色图	行走图索引	头像	肖像索引	坐标x	坐标y	故事
			HP = Integer.parseInt(datas[19]);
			maxHP = HP;
			MP = Integer.parseInt(datas[20]);
			maxMP = MP;
			characterName = datas[21];
			faceName = datas[23];
			lx = Integer.parseInt(datas[25]);
			ly = Integer.parseInt(datas[26]);
			setX(lx);
			setY(ly);
			/**
			 *  31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47	48	49	50	51	52	53	54	55	56	57	58	59	60	61	62	63	64	65	66	67	68	69	70	71	72	73	74	75	76	77	78
			 actionStr	target	thirsty	hunger	tired	pain	credit	money	debuff1	debuff2	debuff3	debuff4	debuff5	debuff6	state1	state2	state3	state4	equip1	equip2	equip3	equip4	equip5	equip6	skill1	skill2	skill3	skill4	skill5	skill6	skill7	skill8	skill9	skill10	skill11	skill12	item1	item2	item3	item4	item5	item6	item7	item8	item9	item10	item11	item12
			 行动	目标	口渴	饥饿	疲劳	疼痛	存款	金钱	冰封	灼烧	流血	中毒	封穴	内伤	状态1	状态2	状态3	状态4	头戴	背披	身穿	手握	腰挂	足踩	技能1	技能2	技能3	技能4	技能5	技能6	技能7	技能8	技能9	技能10	技能11	技能12	物品1	物品2	物品3	物品4	物品5	物品6	物品7	物品8	物品9	物品10	物品11	物品12
			 */
			story = datas[27];
			region = datas[28];
			rx = Integer.parseInt(datas[29]);
			ry = Integer.parseInt(datas[30]);
			actionStr = datas[31];
			target = datas[32];
			thirsty = Integer.parseInt(datas[33]);
			hunger = Integer.parseInt(datas[34]);
			tired = Integer.parseInt(datas[35]);
			pain = Integer.parseInt(datas[36]);
			credit = Integer.parseInt(datas[37]);
			money = Integer.parseInt(datas[38]);
			debuffs = new String[6];
			debuffs[0] = datas[39];
			debuffs[1] = datas[40];
			debuffs[2] = datas[41];
			debuffs[3] = datas[42];
			debuffs[4] = datas[43];
			debuffs[5] = datas[44];
			states = new String[4];
			states[0] = datas[45];
			states[1] = datas[46];
			states[2] = datas[47];
			states[3] = datas[48];
			equips = new String[6];
			equips[0] = datas[49];
			equips[1] = datas[50];
			equips[2] = datas[51];
			equips[3] = datas[52];
			equips[4] = datas[53];
			equips[5] = datas[54];
			skills = new String[12];
			skills[0] = datas[55];
			//初始化行动
			actionStr = skills[0];
			skills[1] = datas[56];
			skills[2] = datas[57];
			skills[3] = datas[58];
			skills[4] = datas[59];
			skills[5] = datas[60];
			skills[6] = datas[61];
			skills[7] = datas[62];
			skills[8] = datas[63];
			skills[9] = datas[64];
			skills[10] = datas[65];
			skills[11] = datas[66];
			items = new String[12];
			items[0] = datas[67];
			items[1] = datas[68];
			items[2] = datas[69];
			items[3] = datas[70];
			items[4] = datas[71];
			items[5] = datas[72];
			items[6] = datas[73];
			items[7] = datas[74];
			items[8] = datas[75];
			items[9] = datas[76];
			items[10] = datas[77];
			items[11] = datas[78];
//			动作测试
		actions = new String[]{"闲逛","战斗","整理","专长"};
		actionIndex = 0;
		actionStr = actions[actionIndex];
		enumAction = EnumAction.MOVE_E;
		faceTo = EnumAction.FACE_E;
		call = "";
		busy = 0;
		turn = 0;
		speed = 4;
	}

	public void move(int x,int y){
		setX(getX()+x*speed);
		setY(getY()+y*speed);
		lx = (int)getX()/16;
		ly = (int)getY()/16;
		turn++;
//		setX(lx);
//		setY(ly);
	}
	public void indexAction(int d){
		int length = actions.length;
		if ((actionIndex + d)<0){
			actionIndex = -(actionInputIndex+d);
		}else {
			actionIndex = actionInputIndex+d;
		}

	}

	public boolean isBusy(){
		return busy>0;
	}

	public void updataBusy(){
		if(busy>0){
			busy--;
		}
	}

	public void updata(){
		if (actionIndex<0){
			actionIndex=0;
		}else if (actionIndex>actions.length-1){
			actionIndex = actions.length-1;
		}
		actionStr = actions[actionIndex];
		updataBusy();
	}
//	public void updata(MapLocal mapLocal, SpriteBatch batch){
//		batch.draw(this,getX(),getY());
//	}
}
