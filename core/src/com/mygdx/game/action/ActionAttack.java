package com.mygdx.game.action;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.manager.AnimationManager;
import com.mygdx.game.manager.ReadData;
import com.mygdx.game.map.MapLocal;
import com.mygdx.game.stage.StageMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
public class ActionAttack implements GamepadKey {
	public String[] commands = {"切磋","挑衅","观察","运气","祛毒"};
	public int index = 0;
	public String currCmd = commands[index];
	Map<String,Attack> attackMap = new HashMap<>();
	public ActionAttack(){
		attackMap = ReadData.attackMap("Data/Skills.txt");
	}
	@Override
	public void passU(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passD(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passL(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passR(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passA(MapLocal mapLocal, BaseActor actor) {
		attack(mapLocal, actor);
		actor.addAction(SkillActions.chongFeng(actor,320,0.5f));
	}
	@Override
	public void passB(MapLocal mapLocal, BaseActor actor) {
		attack(mapLocal, actor);
		actor.addAction(SkillActions.chongFeng(actor,-320,0.5f));
	}
	@Override
	public void passX(MapLocal mapLocal, BaseActor actor) {
		recover(actor);
		StageMap.getInstance().addBattleMsg(actor.name+"运气疗伤");
	}
	@Override
	public void passY(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passL1(MapLocal mapLocal, BaseActor actor) {
		actor.actionIndex++;
	}
	@Override
	public void passL2(MapLocal mapLocal, BaseActor actor) {
		actor.actionIndex--;
	}
	@Override
	public void passR1(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passR2(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passStart(MapLocal mapLocal, BaseActor actor) {
	}
	@Override
	public void passBack(MapLocal mapLocal, BaseActor actor) {
	}
//	用内功回复自己
	void recover(BaseActor actor){
		if(actor.HP >= actor.maxHP){
			actor.call = "你没有受伤！";
			return;
		}
		if(actor.MP<= 0){
			actor.call = "你内力不足";
			return;
		}
		actor.MP = actor.MP - 1;
		actor.HP += 10;
		actor.call = "运气疗伤";
	}

	// 1、锁定目标 2、决定出招时，判断先攻 3、计算攻击与防御 4、判断是否失误 5、是则返回第二步 6、否 计算伤害 7、计算特效
	// 计算与目标的距离
	void attack(MapLocal local,BaseActor actor){
		if(isOver(actor)){

		}else {
			attackTurn(local,actor);
		}
	}

	void attackTurn(MapLocal local,BaseActor actor){
		StageMap stageMap = StageMap.getInstance();
//		stageMap.battleMsg.clear();
		BaseActor actor1,actor2;
		String attackName1,attackName2;
		Attack attack1,attack2;
		actor1 = actor;
		actor2 = stageMap.actors.get(actor1.target);
		attackName1 = actor1.skills[0];
		attackName2 = actor2.skills[0];
		attack1 = AttackManager.getInstance().getAttack(attackName1);
		attack2 = AttackManager.getInstance().getAttack(attackName2);
		AnimationManager animationManager = AnimationManager.getInstance();
//		判断出手速度
		if(faster(actor1,actor2) && actor1.busy==0){
			stageMap.addBattleMsg(actor1.name+"抢先出手");
//			stageMap.animation = animationManager.getAttackAni(attack1.animation);
			stageMap.animation = animationManager.getAttackAni("Attack2.png");
			attackD20(actor1,actor2);
			if (actor2.HP>0){
				stageMap.addBattleMsg(actor2.name+"反击");
				stageMap.animation = animationManager.getAttackAni("lion.png");
				attackD20(actor2,actor1);
			}

//			actor1.ap = 0;
		}else{
			stageMap.addBattleMsg(actor2.name+"先出手");
//			stageMap.animation = animationManager.getAttackAni(attack2.animation);
			stageMap.animation = animationManager.getAttackAni("Attack3.png");
			attackD20(actor2,actor1);
			if (actor1.HP>0){
				stageMap.addBattleMsg(actor1.name+"还击");
				stageMap.animation = animationManager.getAttackAni("Attack5.png");
				attackD20(actor1,actor2);
			}

//			actor2.ap = 0;
		}

	}

	boolean isOver(BaseActor actor){
		//		先判断战斗是否结束
		BaseActor actor1,actor2;
		StageMap stageMap = StageMap.getInstance();
		actor1 = actor;
//				String targetName = actor1.target;
		actor2 = stageMap.actors.get(actor1.target);
		boolean over = false;
		if(actor1.HP<=0){
			over = true;
			stageMap.addBattleMsg(actor1.name+"大侠请重新来过。");
		}else if(actor2.HP<=0){
			over = true;
			stageMap.addBattleMsg(actor2.name+"惨败");
		}
		return over;
	}

	// 1,判断出手速度
	boolean faster(BaseActor actor1,BaseActor actor2){
		AttackManager attackManager = AttackManager.getInstance();
		Attack attack1,attack2;
		attack1 = attackManager.getAttack(actor1.actionStr);
		attack2 = attackManager.getAttack(actor2.actionStr);
		int d1,d2,f1,f2;
		d1 = MathUtils.random(1,20);
		d2 = MathUtils.random(1,20);
		f1 = (d1+actor1.LV/2+actor1.exDex);
		f2 = (d2+actor2.LV/2+actor2.exDex);
		String s = actor1.name + ":"+f1 + " VS "+actor2.name+":"+f2;
		StageMap.getInstance().addBattleMsg(s);
		return f1>=f2;
	}
	// 判断距离
	void skillDistance(BaseActor actor1,BaseActor actor2){
		AttackManager attackManager = AttackManager.getInstance();
		Attack attack1,attack2;
		attack1 = attackManager.getAttack(actor1.skill);
		attack2 = attackManager.getAttack(actor2.skill);
		int d1,d2;
		d1 = attack1.area;
		d2 = attack2.area;


	}

	void attackD20(BaseActor actor1,BaseActor actor2){
		AttackManager attackManager = AttackManager.getInstance();
		StageMap stageMap = StageMap.getInstance();
		Attack attack1,attack2;
		attack1 = attackManager.getAttack(actor1.skills[0]);
		attack2 = attackManager.getAttack(actor2.skills[0]);
		stageMap.attack1 = attack1;
		stageMap.ax = actor2.lx;
		stageMap.ay = actor2.ly;
		String attackVocab="",s,dmgStr="";
		int dmg = 0;
		int d1 = MathUtils.random(1,20);
		int d2 = MathUtils.random(1,20);
		if(d1==20){
			attackVocab = attack1.criteVoc;
			dmg = diceMax(attack1.dmgStr,3);
			s = String.format(attackVocab,attack1.name);
			dmgStr = "[RED]暴击!"+dmg;
			s += dmgStr;
			stageMap.attackMessage = dmgStr;
			stageMap.addBattleMsg(s);
		}else if(d1==1){
			attackVocab = attack1.missVoc;
			s = String.format(attackVocab,attack1.name);
			dmgStr = "[GRAY]失手~"+0;
			stageMap.attackMessage = dmgStr;
			stageMap.addBattleMsg(s);
		} else if(d1>17 && d1 <20){
			attackVocab = attack1.styleVoc;
			int size = attack1.styles.length;
			s = String.format(attackVocab,attack1.name,attack1.styles[MathUtils.random(0,size-1)]);
			dmg = dice(attack1.dmgStr)*2;
			s += "[YELLOW]发动招式:"+dmg;
			stageMap.attackMessage = dmgStr;
			stageMap.addBattleMsg(s);
		}   else {
			if(isAttack(actor1,actor2)){
				attackVocab = attack1.attackVoc;
				s = String.format(attackVocab,attack1.name);
				dmg = dice(attack1.dmgStr);
				dmgStr = "呃"+dmg;
				stageMap.attackMessage = dmgStr;
				s = s+dmgStr;
				stageMap.addBattleMsg(s);
			} else {
				s = "$N双腿微曲，双掌平平提到胸前，右臂划个圆圈，呼的一声，用降龙十八掌的第一招「亢龙有悔」拍向$n";
				s = attackStyle(s,actor1,actor2);
				stageMap.attackMessage = s;
				stageMap.addBattleMsg(s);
			}
		}
		actor2.HP = actor2.HP - dmg;
		actor1.MP = actor1.MP - attack1.mp;
//		制作字符字典 bitmapfont
//		FontManager.getInstance().addCharacters(dmgStr);
//		FontManager.getInstance().addCharacters(s);
		actor1.busy+=5;
		if(actor2.HP*10/actor2.maxHP==8){
			stageMap.addBattleMsg(actor2.name + "身体晃动了一下。");
		}else if(actor2.HP*10/actor2.maxHP == 6){
			stageMap.addBattleMsg(actor2.name+"面部有些扭曲。");
		}else if(actor2.HP*10/actor2.maxHP == 4){
			stageMap.addBattleMsg(actor2.name+"嘴角流出血。");
		}else if(actor2.HP*10/actor2.maxHP == 2){
			stageMap.addBattleMsg(actor2.name+"已经站不稳了。");
		}else if(actor2.HP<= 0){
			stageMap.addBattleMsg(actor2.name+"倒下了");
		}
	}
//	计算AC，防御，包括基础防御和内功防御，物品防御
	boolean isAttack(BaseActor actor1,BaseActor actor2){
		boolean isAttack =false;
		int at,ac,dc,d1,d2;
		Attack attack1,attack2;
		String attSkill1,attSkill2;
		attSkill1 = actor1.skills[0];
		attSkill2 = actor2.skills[0];
		attack1 = AttackManager.getInstance().getAttack(attSkill1);
		attack1 = AttackManager.getInstance().getAttack(attSkill2);
		d1 = MathUtils.random(1,20);
		d2 = MathUtils.random(1,20);
		at = actor1.exStr + d1 + attack1.at;
		ac = actor2.exDex + actor2.MP/10;
//		StageMap.getInstance().addBattleMsg(at+"VS"+ac);
		if(at>ac){
			isAttack = true;
		} else {
			isAttack = false;
			StageMap.getInstance().addBattleMsg(actor2.name + "[BLUE]躲闪");
		}
		return isAttack;
	}
//	色子 nDn
	int dice(String diceStr){
		int num,dice,dmg = 0;
		String[] dices = diceStr.split("D");
		num = Integer.parseInt(dices[0]);
		dice = Integer.parseInt(dices[1]);
		for (int i=0;i<num;i++){
			MathUtils.random.setSeed(System.currentTimeMillis());
			dmg = dmg + MathUtils.random(1,dice);
		}
		return dmg;
	}
//	暴击计算
	int diceMax(String diceStr,int multi){
		int num,dice,dmg = 0;
		String[] dices = diceStr.split("D");
		num = Integer.parseInt(dices[0]);
		dice = Integer.parseInt(dices[1]);
		dmg = num*dice*multi;
		return dmg;
	}
//  武功招式的描述
	String attackStyle(String style, BaseActor actor1,BaseActor actor2){
		String newStyle;
		String[] bodys = new String[]{"左手腕","左胳膊","脖子","头部","腰部","左腿","小腿","左脚脚腕","右手腕","右胳膊","右腿","右脚脚腕",};
		newStyle = style.replace("$N",actor1.name);
		newStyle = newStyle.replace("$n",actor2.name);
		newStyle = newStyle.replace("$w",actor1.equips[0]);
		newStyle = newStyle.replace("$l",bodys[MathUtils.random(bodys.length-1)]);
		newStyle = newStyle.replace("「","[RED]「");
		newStyle = newStyle.replace("」","」[]");
		return  newStyle;
	}
//	如何控制战斗流程，变成即时制。或者让npc自动战斗
//	1、进入战斗模式
//	2、目标处于活动状态
//	3、自己处于活动状态
//	4、目标在范围内。
//	5、战斗，随机使用技能。
//	简单回合制turn%2 == 0 ，自己行动，==1，对方行动
	void attackTurn(int turn){
		BaseActor me;
		BaseActor target;
		Action action;

	}

	public void updata(){

	}

}
