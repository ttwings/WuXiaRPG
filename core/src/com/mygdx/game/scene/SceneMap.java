package com.mygdx.game.scene;

/**
 * Created by Administrator on 2016/12/26.
 */
public class SceneMap {
    /**
     *#encoding:utf-8
     #==============================================================================
     # ■ Scene_Map
     #------------------------------------------------------------------------------
     # 　地图画面
     #==============================================================================

     class Scene_Map < Scene_Base
     #--------------------------------------------------------------------------
     # ● 开始处理
     #--------------------------------------------------------------------------
     def start
     super
     SceneManager.clear
     $game_player.straighten
     $game_map.refresh
     $game_message.visible = false
     create_spriteset
     create_all_windows
     @menu_calling = false
     end
     #--------------------------------------------------------------------------
     # ● 执行进入场景时的渐变
     #--------------------------------------------------------------------------
     def perform_transition
     if Graphics.brightness == 0
     Graphics.transition(0)
     fadein(fadein_speed)
     else
     super
     end
     end
     #--------------------------------------------------------------------------
     # ● 获取渐变速度
     #--------------------------------------------------------------------------
     def transition_speed
     return 15
     end
     #--------------------------------------------------------------------------
     # ● 结束前处理
     #--------------------------------------------------------------------------
     def pre_terminate
     super
     pre_battle_scene if SceneManager.scene_is?(Scene_Battle)
     pre_title_scene  if SceneManager.scene_is?(Scene_Title)
     end
     #--------------------------------------------------------------------------
     # ● 结束处理
     #--------------------------------------------------------------------------
     def terminate
     super
     SceneManager.snapshot_for_background
     dispose_spriteset
     perform_battle_transition if SceneManager.scene_is?(Scene_Battle)
     end
     #--------------------------------------------------------------------------
     # ● 更新画面
     #--------------------------------------------------------------------------
     def update
     super
     $game_map.update(true)
     $game_player.update
     $game_timer.update
     @spriteset.update
     update_scene if scene_change_ok?
     end
     #--------------------------------------------------------------------------
     # ● 判定是否可以切换场景
     #--------------------------------------------------------------------------
     def scene_change_ok?
     !$game_message.busy? && !$game_message.visible
     end
     #--------------------------------------------------------------------------
     # ● 更新场景消退时的过渡
     #--------------------------------------------------------------------------
     def update_scene
     check_gameover
     update_transfer_player unless scene_changing?
     update_encounter unless scene_changing?
     update_call_menu unless scene_changing?
     update_call_debug unless scene_changing?
     end
     #--------------------------------------------------------------------------
     # ● 更新画面（消退用）
     #--------------------------------------------------------------------------
     def update_for_fade
     update_basic
     $game_map.update(false)
     @spriteset.update
     end
     #--------------------------------------------------------------------------
     # ● 通用的消退处理
     #--------------------------------------------------------------------------
     def fade_loop(duration)
     duration.times do |i|
     yield 255 * (i + 1) / duration
     update_for_fade
     end
     end
     #--------------------------------------------------------------------------
     # ● 淡入画面
     #--------------------------------------------------------------------------
     def fadein(duration)
     fade_loop(duration) {|v| Graphics.brightness = v }
     end
     #--------------------------------------------------------------------------
     # ● 淡出画面
     #--------------------------------------------------------------------------
     def fadeout(duration)
     fade_loop(duration) {|v| Graphics.brightness = 255 - v }
     end
     #--------------------------------------------------------------------------
     # ● 淡入画面（白）
     #--------------------------------------------------------------------------
     def white_fadein(duration)
     fade_loop(duration) {|v| @viewport.color.set(255, 255, 255, 255 - v) }
     end
     #--------------------------------------------------------------------------
     # ● 淡出画面（白）
     #--------------------------------------------------------------------------
     def white_fadeout(duration)
     fade_loop(duration) {|v| @viewport.color.set(255, 255, 255, v) }
     end
     #--------------------------------------------------------------------------
     # ● 生成精灵组
     #--------------------------------------------------------------------------
     def create_spriteset
     @spriteset = Spriteset_Map.new
     end
     #--------------------------------------------------------------------------
     # ● 释放精灵组
     #--------------------------------------------------------------------------
     def dispose_spriteset
     @spriteset.dispose
     end
     #--------------------------------------------------------------------------
     # ● 生成所有窗口
     #--------------------------------------------------------------------------
     def create_all_windows
     create_message_window
     create_scroll_text_window
     create_location_window
     end
     #--------------------------------------------------------------------------
     # ● 生成信息窗口
     #--------------------------------------------------------------------------
     def create_message_window
     @message_window = Window_Message.new
     end
     #--------------------------------------------------------------------------
     # ● 生成滚动文字窗口
     #--------------------------------------------------------------------------
     def create_scroll_text_window
     @scroll_text_window = Window_ScrollText.new
     end
     #--------------------------------------------------------------------------
     # ● 生成地图名称窗口
     #--------------------------------------------------------------------------
     def create_location_window
     @map_name_window = Window_MapName.new
     end
     #--------------------------------------------------------------------------
     # ● 监听场所移动指令
     #--------------------------------------------------------------------------
     def update_transfer_player
     perform_transfer if $game_player.transfer?
     end
     #--------------------------------------------------------------------------
     # ● 监听遇敌事件
     #--------------------------------------------------------------------------
     def update_encounter
     SceneManager.call(Scene_Battle) if $game_player.encounter
     end
     #--------------------------------------------------------------------------
     # ● 监听取消键的按下。如果菜单可用且地图上没有事件在运行，则打开菜单界面。
     #--------------------------------------------------------------------------
     def update_call_menu
     if $game_system.menu_disabled || $game_map.interpreter.running?
     @menu_calling = false
     else
     @menu_calling ||= Input.trigger?(:B)
     call_menu if @menu_calling && !$game_player.moving?
     end
     end
     #--------------------------------------------------------------------------
     # ● 打开菜单画面
     #--------------------------------------------------------------------------
     def call_menu
     Sound.play_ok
     SceneManager.call(Scene_Menu)
     Window_MenuCommand::init_command_position
     end
     #--------------------------------------------------------------------------
     # ● 监听 F9 的按下。如果是游戏测试的情况下，则打开调试界面
     #--------------------------------------------------------------------------
     def update_call_debug
     SceneManager.call(Scene_Debug) if $TEST && Input.press?(:F9)
     end
     #--------------------------------------------------------------------------
     # ● 处理场所移动
     #--------------------------------------------------------------------------
     def perform_transfer
     pre_transfer
     $game_player.perform_transfer
     post_transfer
     end
     #--------------------------------------------------------------------------
     # ● 场所移动前的处理
     #--------------------------------------------------------------------------
     def pre_transfer
     @map_name_window.close
     case $game_temp.fade_type
     when 0
     fadeout(fadeout_speed)
     when 1
     white_fadeout(fadeout_speed)
     end
     end
     #--------------------------------------------------------------------------
     # ● 场所移动后的处理
     #--------------------------------------------------------------------------
     def post_transfer
     case $game_temp.fade_type
     when 0
     Graphics.wait(fadein_speed / 2)
     fadein(fadein_speed)
     when 1
     Graphics.wait(fadein_speed / 2)
     white_fadein(fadein_speed)
     end
     @map_name_window.open
     end
     #--------------------------------------------------------------------------
     # ● 切换战斗画面前的处理
     #--------------------------------------------------------------------------
     def pre_battle_scene
     Graphics.update
     Graphics.freeze
     @spriteset.dispose_characters
     BattleManager.save_bgm_and_bgs
     BattleManager.play_battle_bgm
     Sound.play_battle_start
     end
     #--------------------------------------------------------------------------
     # ● 切换到标题画面前的处理
     #--------------------------------------------------------------------------
     def pre_title_scene
     fadeout(fadeout_speed_to_title)
     end
     #--------------------------------------------------------------------------
     # ● 执行战斗前的渐变
     #--------------------------------------------------------------------------
     def perform_battle_transition
     Graphics.transition(60, "Graphics/System/BattleStart", 100)
     Graphics.freeze
     end
     #--------------------------------------------------------------------------
     # ● 获取淡出速度
     #--------------------------------------------------------------------------
     def fadeout_speed
     return 30
     end
     #--------------------------------------------------------------------------
     # ● 获取淡入速度
     #--------------------------------------------------------------------------
     def fadein_speed
     return 30
     end
     #--------------------------------------------------------------------------
     # ● 获取切换到标题画面时的淡出速度
     #--------------------------------------------------------------------------
     def fadeout_speed_to_title
     return 60
     end
     end

     */
}
