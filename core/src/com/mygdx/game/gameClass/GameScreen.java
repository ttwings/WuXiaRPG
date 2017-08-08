package com.mygdx.game.gameClass;

/**
 * Created by Administrator on 2016/12/9.
 */
public class GameScreen {
    /**
     * #encoding:utf-8
     #==============================================================================
     # ■ Game_Screen
     #------------------------------------------------------------------------------
     # 　更改色调以及画面闪烁、保存画面全体关系处理数据的类。
     #   使用于 Game_Map 和 Game_Troop 类。
     #==============================================================================

     class Game_Screen
     #--------------------------------------------------------------------------
     # ● 定义实例变量
     #--------------------------------------------------------------------------
     attr_reader   :brightness               # 明亮度
     attr_reader   :tone                     # 色调
     attr_reader   :flash_color              # 闪烁颜色
     attr_reader   :pictures                 # 图片
     attr_reader   :shake                    # 震动位置
     attr_reader   :weather_type             # 天气 类型
     attr_reader   :weather_power            # 天气 强度 (Float)
     #--------------------------------------------------------------------------
     # ● 初始化对象
     #--------------------------------------------------------------------------
     def initialize
     @pictures = Game_Pictures.new
     clear
     end
     #--------------------------------------------------------------------------
     # ● 清除
     #--------------------------------------------------------------------------
     def clear
     clear_fade
     clear_tone
     clear_flash
     clear_shake
     clear_weather
     clear_pictures
     end
     #--------------------------------------------------------------------------
     # ● 清除淡入・淡出效果
     #--------------------------------------------------------------------------
     def clear_fade
     @brightness = 255
     @fadeout_duration = 0
     @fadein_duration = 0
     end
     #--------------------------------------------------------------------------
     # ● 清除色调
     #--------------------------------------------------------------------------
     def clear_tone
     @tone = Tone.new
     @tone_target = Tone.new
     @tone_duration = 0
     end
     #--------------------------------------------------------------------------
     # ● 清除闪烁效果
     #--------------------------------------------------------------------------
     def clear_flash
     @flash_color = Color.new
     @flash_duration = 0
     end
     #--------------------------------------------------------------------------
     # ● 清除震动效果
     #--------------------------------------------------------------------------
     def clear_shake
     @shake_power = 0
     @shake_speed = 0
     @shake_duration = 0
     @shake_direction = 1
     @shake = 0
     end
     #--------------------------------------------------------------------------
     # ● 清除天气
     #--------------------------------------------------------------------------
     def clear_weather
     @weather_type = :none
     @weather_power = 0
     @weather_power_target = 0
     @weather_duration = 0
     end
     #--------------------------------------------------------------------------
     # ● 清除图片
     #--------------------------------------------------------------------------
     def clear_pictures
     @pictures.each {|picture| picture.erase }
     end
     #--------------------------------------------------------------------------
     # ● 开始淡出
     #--------------------------------------------------------------------------
     def start_fadeout(duration)
     @fadeout_duration = duration
     @fadein_duration = 0
     end
     #--------------------------------------------------------------------------
     # ● 开始淡入
     #--------------------------------------------------------------------------
     def start_fadein(duration)
     @fadein_duration = duration
     @fadeout_duration = 0
     end
     #--------------------------------------------------------------------------
     # ● 开始更改色调
     #--------------------------------------------------------------------------
     def start_tone_change(tone, duration)
     @tone_target = tone.clone
     @tone_duration = duration
     @tone = @tone_target.clone if @tone_duration == 0
     end
     #--------------------------------------------------------------------------
     # ● 开始闪烁
     #--------------------------------------------------------------------------
     def start_flash(color, duration)
     @flash_color = color.clone
     @flash_duration = duration
     end
     #--------------------------------------------------------------------------
     # ● 开始震动
     #     power : 强度
     #     speed : 速度
     #--------------------------------------------------------------------------
     def start_shake(power, speed, duration)
     @shake_power = power
     @shake_speed = speed
     @shake_duration = duration
     end
     #--------------------------------------------------------------------------
     # ● 更改天气
     #     type  : 类型 (:none, :rain, :storm, :snow)
     #     power : 强度
     #    如果是下雨则显示一阵一阵的雨点、没有天气类型 (:none) 的时候
     #    把 @weather_power_target (强度的目标值) 设置为 0 。
     #--------------------------------------------------------------------------
     def change_weather(type, power, duration)
     @weather_type = type if type != :none || duration == 0
     @weather_power_target = type == :none ? 0.0 : power.to_f
     @weather_duration = duration
     @weather_power = @weather_power_target if duration == 0
     end
     #--------------------------------------------------------------------------
     # ● 更新画面
     #--------------------------------------------------------------------------
     def update
     update_fadeout
     update_fadein
     update_tone
     update_flash
     update_shake
     update_weather
     update_pictures
     end
     #--------------------------------------------------------------------------
     # ● 更新淡出
     #--------------------------------------------------------------------------
     def update_fadeout
     if @fadeout_duration > 0
     d = @fadeout_duration
     @brightness = (@brightness * (d - 1)) / d
     @fadeout_duration -= 1
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新淡入
     #--------------------------------------------------------------------------
     def update_fadein
     if @fadein_duration > 0
     d = @fadein_duration
     @brightness = (@brightness * (d - 1) + 255) / d
     @fadein_duration -= 1
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新色调
     #--------------------------------------------------------------------------
     def update_tone
     if @tone_duration > 0
     d = @tone_duration
     @tone.red = (@tone.red * (d - 1) + @tone_target.red) / d
     @tone.green = (@tone.green * (d - 1) + @tone_target.green) / d
     @tone.blue = (@tone.blue * (d - 1) + @tone_target.blue) / d
     @tone.gray = (@tone.gray * (d - 1) + @tone_target.gray) / d
     @tone_duration -= 1
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新闪烁
     #--------------------------------------------------------------------------
     def update_flash
     if @flash_duration > 0
     d = @flash_duration
     @flash_color.alpha = @flash_color.alpha * (d - 1) / d
     @flash_duration -= 1
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新震动
     #--------------------------------------------------------------------------
     def update_shake
     if @shake_duration > 0 || @shake != 0
     delta = (@shake_power * @shake_speed * @shake_direction) / 10.0
     if @shake_duration <= 1 && @shake * (@shake + delta) < 0
     @shake = 0
     else
     @shake += delta
     end
     @shake_direction = -1 if @shake > @shake_power * 2
     @shake_direction = 1 if @shake < - @shake_power * 2
     @shake_duration -= 1
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新天气
     #--------------------------------------------------------------------------
     def update_weather
     if @weather_duration > 0
     d = @weather_duration
     @weather_power = (@weather_power * (d - 1) + @weather_power_target) / d
     @weather_duration -= 1
     if @weather_duration == 0 && @weather_power_target == 0
     @weather_type = :none
     end
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新图片
     #--------------------------------------------------------------------------
     def update_pictures
     @pictures.each {|picture| picture.update }
     end
     #--------------------------------------------------------------------------
     # ● 开始闪烁（慢性伤害和有害地形用）
     #--------------------------------------------------------------------------
     def start_flash_for_damage
     start_flash(Color.new(255,0,0,128), 8)
     end
     end

     */

}
