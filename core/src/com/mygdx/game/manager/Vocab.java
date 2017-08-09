package com.mygdx.game.manager;

/**
 * Created by apple on 16/11/19.
 * 游戏中的用语，用来在游戏中显示。语境
 */
public final class Vocab {
    // 商店画面
    public static final String ShopBuy = "买";
    public static final String ShopSell = "卖";
    public static final String ShopCancel = "撤";
    public static final String Possession = "持有数";
    // 状态画面
    public static final String ExpTotal = "当前历练";
    public static final String ExpNext = "下一%s";
    // 存档／读档画面
    public static final String SaveMessage = "在哪个位置写下经历？";
    public static final String LoadMessage = "从哪个位置继续经历？";
    public static final String File = "经历";
    // 有多个队友时显示
    public static final String PartyName = "%s的队伍";
    // 战斗基本信息
    public static final String Emerge = "%s出现了！";
    public static final String Preemptive = "%s先发制人！";
    public static final String Surprise = "%s被偷袭了！";
    public static final String EscapeStart = "%s准备撤退！";
    public static final String EscapeFailure = "但是被包围了……";
    // 战斗结束信息
    public static final String Victory = "%s胜利了！";
    public static final String Defeat = "%s全灭了……";
    public static final String ObtainExp = "获得了%s点经验值！";
    public static final String ObtainGold = "获得了%s钱！";
    public static final String ObtainItem = "获得了%s！";
    public static final String LevelUp = "%s已经%s%s了！";
    public static final String ObtainSkill = "领悟了%s！";
    // 物品使用
    public static final String UseItem = "%s使用了%s！";
    public static final String EatItem = "%s服用了%s";
    // 关键一击
    public static final String CriticalToEnemy = "关键一击！";
    public static final String CriticalToActor = "痛恨一击！";
    // 角色对象的行动结果
    public static final String ActorAttack = "使出%s";
    public static final String ActorAttackStyle = "使出%s[%s]";
    public static final String ActorAttackMax = "使出%s击中要害";
    public static final String ActorAttackMiss = "使出%s,但对方躲开了";
    //    public static final String Attack = "%s使出%s,打在%s身上，造成%d点伤害";
    //    public static final String Attack = "%s使出%s,打在%s身上，造成%d点伤害";
    public static final String ActorDamage = "%s受到了%s点的伤害！";
    public static final String ActorRecovery = "%s的%s恢复了%s点！";
    public static final String ActorGain = "%s的%s恢复了%s点！";
    public static final String ActorLoss = "%s的%s失去了%s点！";
    public static final String ActorDrain = "%s的%s被夺走了%s点！";
    public static final String ActorNoDamage = "%s没有受到伤害！";
    public static final String ActorNoHit = "失误！%s毫发无伤！";
    // 敌人对象的行动结果
    public static final String EnemyDamage = "%s受到了%s点的伤害！";
    public static final String EnemyRecovery = "%s的%s恢复了%s点！";
    public static final String EnemyGain = "%s的%s恢复了%s点！";
    public static final String EnemyLoss = "%s的%s失去了%s点！";
    public static final String EnemyDrain = "%s的%s被夺走了%s点！";
    public static final String EnemyNoDamage = "%s没有受到伤害！";
    public static final String EnemyNoHit = "失误！%s毫发无伤！";
    // 回避／反射
    public static final String Evasion = "%s躲开了攻击！";
    public static final String MagicEvasion = "%s抵消了魔法效果！";
    public static final String MagicReflection = "%s反射了魔法效果！";
    public static final String CounterAttack = "%s进行反击！";
    public static final String Substitute = "%s代替%s承受了攻击！";
    // 能力强化／弱化
    public static final String BuffAdd = "%s的%s上升了！";
    public static final String DebuffAdd = "%s的%s下降了！";
    public static final String BuffRemove = "%s的%s恢复了！";
    // 技能或物品的使用无效时
    public static final String ActionFailure = "对%s无效！";
    //    用于日历类
    public static final String CALENDAR = "%s年%s季%2d日%s时%s";
    public static final String[] SEASONS = {"春", "夏", "秋", "冬"};
    public static final String[] YEARS = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    public static final String[] HOURS = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    public static final String[] WEEKDAYS = {"日", "金", "木", "水", "火", "土", "月"};
    public static final String[] MOONS = {"....(", "...(.", "..◎..", ".)...", ")...."};
    public static final String[] SUNS = {"O     ", " O    ", "", "", "", "", "", "", "", "", "", ""};
    // 出错时的信息
    public static final String PlayerPosError = "没有设置玩家的初始位置。";
    public static final String EventOverflow = "调用的公共事件超过过上限。";
    // 角色坐标
    public static final String X = "坐标x";
    public static final String Y = "坐标y";
    //    角色属性称呼
    public static final String Str = "臂力";
    public static final String Con = "根骨";
    public static final String Dex = "身法";
    public static final String Int = "悟性";
    public static final String Wis = "五感";
    public static final String Cha = "福缘";
    //    角色信息
    public static final String Name = "姓名";
    public static final String Character = "角色图";
    public static final String Face = "头像";
    public static final String Age = "年龄";
    public static final String H = "身高";
    public static final String LV = "等级";
    public static final String Exp = "历练";
    public static final String Speed = "速度";
    public static final String AP = "动作点数";
    public static final String NickName = "称号";
    public static final String Country = "国家";
    public static final String Family = "家族";
    public static final String Camp = "门派";
    public static final String Faith = "思想";
    public static final String Sex = "性别";
    public static final String Story = "背景故事";
    //  技能信息
    public static final String CLASS = "类别";
    public static final String CH = "属性";
    public static final String DMG = "伤害";
    public static final String DFA = "招式";
    public static final String DAC = "护体";
    public static final String DMP = "耗内";
    public static final String DAP = "动作";

    public String replaceNumber(String s) {
        s = s.replace("-", "负");
        s = s.replace("+", "正");
        s = s.replace("1", "一");
        s = s.replace("2", "二");
        s = s.replace("3", "三");
        s = s.replace("4", "四");
        s = s.replace("5", "五");
        s = s.replace("6", "六");
        s = s.replace("7", "七");
        s = s.replace("8", "八");
        s = s.replace("9", "九");
        s = s.replace("0", "〇");
        return s;
    }
}
