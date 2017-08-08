package com.mygdx.game.gameClass;

/**
 * Created by Administrator on 2016/12/26.
 */
public class GameAction {
    /**
     * #encoding:utf-8
     #==============================================================================
     # ■ Game_Action
     #------------------------------------------------------------------------------
     # 　处理战斗中的行动的类。本类在 Game_Battler 类的内部使用。
     #==============================================================================

     class Game_Action
     #--------------------------------------------------------------------------
     # ● 定义实例变量
     #--------------------------------------------------------------------------
     attr_reader   :subject                  # 行动主体
     attr_reader   :forcing                  # 强制行动的标志
     attr_reader   :item                     # 技能 / 物品
     attr_accessor :target_index             # 目标索引
     attr_reader   :value                    # 评价值（自动战斗用）
     #--------------------------------------------------------------------------
     # ● 初始化对象
     #--------------------------------------------------------------------------
     def initialize(subject, forcing = false)
     @subject = subject
     @forcing = forcing
     clear
     end
     #--------------------------------------------------------------------------
     # ● 清除
     #--------------------------------------------------------------------------
     def clear
     @item = Game_BaseItem.new
     @target_index = -1
     @value = 0
     end
     #--------------------------------------------------------------------------
     # ● 获取队友单位
     #--------------------------------------------------------------------------
     def friends_unit
     subject.friends_unit
     end
     #--------------------------------------------------------------------------
     # ● 获取敌人单位
     #--------------------------------------------------------------------------
     def opponents_unit
     subject.opponents_unit
     end
     #--------------------------------------------------------------------------
     # ● 设置敌人的战斗行动
     #     action : RPG::Enemy::Action
     #--------------------------------------------------------------------------
     def set_enemy_action(action)
     if action
     set_skill(action.skill_id)
     else
     clear
     end
     end
     #--------------------------------------------------------------------------
     # ● 设置普通攻击
     #--------------------------------------------------------------------------
     def set_attack
     set_skill(subject.attack_skill_id)
     self
     end
     #--------------------------------------------------------------------------
     # ● 设置防御
     #--------------------------------------------------------------------------
     def set_guard
     set_skill(subject.guard_skill_id)
     self
     end
     #--------------------------------------------------------------------------
     # ● 设置技能
     #--------------------------------------------------------------------------
     def set_skill(skill_id)
     @item.object = $data_skills[skill_id]
     self
     end
     #--------------------------------------------------------------------------
     # ● 设置物品
     #--------------------------------------------------------------------------
     def set_item(item_id)
     @item.object = $data_items[item_id]
     self
     end
     #--------------------------------------------------------------------------
     # ● 获取物品实例
     #--------------------------------------------------------------------------
     def item
     @item.object
     end
     #--------------------------------------------------------------------------
     # ● 判定是否为普通攻击
     #--------------------------------------------------------------------------
     def attack?
     item == $data_skills[subject.attack_skill_id]
     end
     #--------------------------------------------------------------------------
     # ● 随机目标
     #--------------------------------------------------------------------------
     def decide_random_target
     if item.for_dead_friend?
     target = friends_unit.random_dead_target
     elsif item.for_friend?
     target = friends_unit.random_target
     else
     target = opponents_unit.random_target
     end
     if target
     @target_index = target.index
     else
     clear
     end
     end
     #--------------------------------------------------------------------------
     # ● 设置混乱行动
     #--------------------------------------------------------------------------
     def set_confusion
     set_attack
     end
     #--------------------------------------------------------------------------
     # ● 行动准备
     #--------------------------------------------------------------------------
     def prepare
     set_confusion if subject.confusion? && !forcing
     end
     #--------------------------------------------------------------------------
     # ● 判定行动是否有效
     #    假设在事件指令中没有选择强制行动时，
     #    受到状态的限制，也没有可用的物品，或者没有预定的行动，则返回 false。
     #--------------------------------------------------------------------------
     def valid?
     (forcing && item) || subject.usable?(item)
     end
     #--------------------------------------------------------------------------
     # ● 计算行动速度
     #--------------------------------------------------------------------------
     def speed
     speed = subject.agi + rand(5 + subject.agi / 4)
     speed += item.speed if item
     speed += subject.atk_speed if attack?
     speed
     end
     #--------------------------------------------------------------------------
     # ● 生成目标数组
     #--------------------------------------------------------------------------
     def make_targets
     if !forcing && subject.confusion?
     [confusion_target]
     elsif item.for_opponent?
     targets_for_opponents
     elsif item.for_friend?
     targets_for_friends
     else
     []
     end
     end
     #--------------------------------------------------------------------------
     # ● 混乱时的目标
     #--------------------------------------------------------------------------
     def confusion_target
     case subject.confusion_level
     when 1
     opponents_unit.random_target
     when 2
     if rand(2) == 0
     opponents_unit.random_target
     else
     friends_unit.random_target
     end
     else
     friends_unit.random_target
     end
     end
     #--------------------------------------------------------------------------
     # ● 目标为敌人
     #--------------------------------------------------------------------------
     def targets_for_opponents
     if item.for_random?
     Array.new(item.number_of_targets) { opponents_unit.random_target }
     elsif item.for_one?
     num = 1 + (attack? ? subject.atk_times_add.to_i : 0)
     if @target_index < 0
     [opponents_unit.random_target] * num
     else
     [opponents_unit.smooth_target(@target_index)] * num
     end
     else
     opponents_unit.alive_members
     end
     end
     #--------------------------------------------------------------------------
     # ● 目标为队友
     #--------------------------------------------------------------------------
     def targets_for_friends
     if item.for_user?
     [subject]
     elsif item.for_dead_friend?
     if item.for_one?
     [friends_unit.smooth_dead_target(@target_index)]
     else
     friends_unit.dead_members
     end
     elsif item.for_friend?
     if item.for_one?
     [friends_unit.smooth_target(@target_index)]
     else
     friends_unit.alive_members
     end
     end
     end
     #--------------------------------------------------------------------------
     # ● 行动的价值评价（自动战斗用）
     #    自动设置 @value 和 @target_index 。
     #--------------------------------------------------------------------------
     def evaluate
     @value = 0
     evaluate_item if valid?
     @value += rand if @value > 0
     self
     end
     #--------------------------------------------------------------------------
     # ● 技能／物品的评价
     #--------------------------------------------------------------------------
     def evaluate_item
     item_target_candidates.each do |target|
     value = evaluate_item_with_target(target)
     if item.for_all?
     @value += value
     elsif value > @value
     @value = value
     @target_index = target.index
     end
     end
     end
     #--------------------------------------------------------------------------
     # ● 获取技能／使用物品的候选目标
     #--------------------------------------------------------------------------
     def item_target_candidates
     if item.for_opponent?
     opponents_unit.alive_members
     elsif item.for_user?
     [subject]
     elsif item.for_dead_friend?
     friends_unit.dead_members
     else
     friends_unit.alive_members
     end
     end
     #--------------------------------------------------------------------------
     # ● 技能／物品的评价（对指定目标）
     #--------------------------------------------------------------------------
     def evaluate_item_with_target(target)
     target.result.clear
     target.make_damage_value(subject, item)
     if item.for_opponent?
     return target.result.hp_damage.to_f / [target.hp, 1].max
     else
     recovery = [-target.result.hp_damage, target.mhp - target.hp].min
     return recovery.to_f / target.mhp
     end
     end
     end

     */
}
