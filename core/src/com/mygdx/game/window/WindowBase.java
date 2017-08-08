package com.mygdx.game.window;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by apple on 16/11/19.
 */
public class WindowBase {
    public int w;
    public int h;
    public int x;
    public int y;
    public Texture windowSkin;
    public TextureRegion backgroud;
    public String message;

    public Color textColor(int index){
        Pixmap p;
        int[] argb;
        int pix;
        pix = windowSkin.getTextureData().consumePixmap().getPixel(0,0);
        int a,r,g,b;
        a = pix&0x000000ff;
        b = pix&0x0000ff00>>2;
        g = pix&0x00ff0000>>4;
        r = pix&0xff000000>>6;
        Color c = new Color(r/255,g/255,b/255,a/255);
        return c;
    }
    /**
     * #encoding:utf-8
     #==============================================================================
     # ■ Window_Base
     #------------------------------------------------------------------------------
     # 　游戏中所有窗口的父类
     #==============================================================================

     class Window_Base < Window
     #--------------------------------------------------------------------------
     # ● 初始化对象
     #--------------------------------------------------------------------------
     def initialize(x, y, width, height)
     super
     self.windowskin = Cache.system("Window")
     update_padding
     update_tone
     create_contents
     @opening = @closing = false
     end
     #--------------------------------------------------------------------------
     # ● 释放
     #--------------------------------------------------------------------------
     def dispose
     contents.dispose unless disposed?
     super
     end
     #--------------------------------------------------------------------------
     # ● 获取行高
     #--------------------------------------------------------------------------
     def line_height
     return 24
     end
     #--------------------------------------------------------------------------
     # ● 获取标准的边距尺寸
     #--------------------------------------------------------------------------
     def standard_padding
     return 12
     end
     #--------------------------------------------------------------------------
     # ● 更新边距
     #--------------------------------------------------------------------------
     def update_padding
     self.padding = standard_padding
     end
     #--------------------------------------------------------------------------
     # ● 计算窗口内容的宽度
     #--------------------------------------------------------------------------
     def contents_width
     width - standard_padding * 2
     end
     #--------------------------------------------------------------------------
     # ● 计算窗口内容的高度
     #--------------------------------------------------------------------------
     def contents_height
     height - standard_padding * 2
     end
     #--------------------------------------------------------------------------
     # ● 计算窗口显示指定行数时的应用高度
     #--------------------------------------------------------------------------
     def fitting_height(line_number)
     line_number * line_height + standard_padding * 2
     end
     #--------------------------------------------------------------------------
     # ● 更新色调
     #--------------------------------------------------------------------------
     def update_tone
     self.tone.set($game_system.window_tone)
     end
     #--------------------------------------------------------------------------
     # ● 生成窗口内容
     #--------------------------------------------------------------------------
     def create_contents
     contents.dispose
     if contents_width > 0 && contents_height > 0
     self.contents = Bitmap.new(contents_width, contents_height)
     else
     self.contents = Bitmap.new(1, 1)
     end
     end
     #--------------------------------------------------------------------------
     # ● 更新画面
     #--------------------------------------------------------------------------
     def update
     super
     update_tone
     update_open if @opening
     update_close if @closing
     end
     #--------------------------------------------------------------------------
     # ● 更新打开处理
     #--------------------------------------------------------------------------
     def update_open
     self.openness += 48
     @opening = false if open?
     end
     #--------------------------------------------------------------------------
     # ● 更新关闭处理
     #--------------------------------------------------------------------------
     def update_close
     self.openness -= 48
     @closing = false if close?
     end
     #--------------------------------------------------------------------------
     # ● 打开窗口
     #--------------------------------------------------------------------------
     def open
     @opening = true unless open?
     @closing = false
     self
     end
     #--------------------------------------------------------------------------
     # ● 关闭窗口
     #--------------------------------------------------------------------------
     def close
     @closing = true unless close?
     @opening = false
     self
     end
     #--------------------------------------------------------------------------
     # ● 显示窗口
     #--------------------------------------------------------------------------
     def show
     self.visible = true
     self
     end
     #--------------------------------------------------------------------------
     # ● 隐藏窗口
     #--------------------------------------------------------------------------
     def hide
     self.visible = false
     self
     end
     #--------------------------------------------------------------------------
     # ● 启用窗口
     #--------------------------------------------------------------------------
     def activate
     self.active = true
     self
     end
     #--------------------------------------------------------------------------
     # ● 冻结窗口
     #--------------------------------------------------------------------------
     def deactivate
     self.active = false
     self
     end
     #--------------------------------------------------------------------------
     # ● 获取文字颜色
     #     n : 文字颜色编号（0..31）
     #--------------------------------------------------------------------------
     def text_color(n)
     windowskin.get_pixel(64 + (n % 8) * 8, 96 + (n / 8) * 8)
     end
     #--------------------------------------------------------------------------
     # ● 获取各种文字颜色
     #--------------------------------------------------------------------------
     def normal_color;      text_color(0);   end;    # 普通
     def system_color;      text_color(16);  end;    # 系统
     def crisis_color;      text_color(17);  end;    # 危险
     def knockout_color;    text_color(18);  end;    # 无法战斗
     def gauge_back_color;  text_color(19);  end;    # 值槽背景
     def hp_gauge_color1;   text_color(20);  end;    # HP 值槽 1
     def hp_gauge_color2;   text_color(21);  end;    # HP 值槽 2
     def mp_gauge_color1;   text_color(22);  end;    # MP 值槽 1
     def mp_gauge_color2;   text_color(23);  end;    # MP 值槽 2
     def mp_cost_color;     text_color(23);  end;    # 消费 TP
     def power_up_color;    text_color(24);  end;    # 能力值提升（更换装备时）
     def power_down_color;  text_color(25);  end;    # 能力值降低（更换装备时）
     def tp_gauge_color1;   text_color(28);  end;    # TP 值槽 1
     def tp_gauge_color2;   text_color(29);  end;    # TP 值槽 2
     def tp_cost_color;     text_color(29);  end;    # 消费 TP
     #--------------------------------------------------------------------------
     # ● 获取保留项目的背景色
     #--------------------------------------------------------------------------
     def pending_color
     windowskin.get_pixel(80, 80)
     end
     #--------------------------------------------------------------------------
     # ● 获取半透明绘制用的透明度
     #--------------------------------------------------------------------------
     def translucent_alpha
     return 160
     end
     #--------------------------------------------------------------------------
     # ● 更改内容绘制颜色
     #     enabled : 有效的标志。false 的时候使用半透明效果绘制
     #--------------------------------------------------------------------------
     def change_color(color, enabled = true)
     contents.font.color.set(color)
     contents.font.color.alpha = translucent_alpha unless enabled
     end
     #--------------------------------------------------------------------------
     # ● 绘制内容
     #     args : 与 Bitmap#draw_text 相同
     #--------------------------------------------------------------------------
     def draw_text(*args)
     contents.draw_text(*args)
     end
     #--------------------------------------------------------------------------
     # ● 获取内容尺寸
     #--------------------------------------------------------------------------
     def text_size(str)
     contents.text_size(str)
     end
     #--------------------------------------------------------------------------
     # ● 绘制带有控制符的文本内容
     #--------------------------------------------------------------------------
     def draw_text_ex(x, y, text)
     reset_font_settings
     text = convert_escape_characters(text)
     pos = {:x => x, :y => y, :new_x => x, :height => calc_line_height(text)}
     process_character(text.slice!(0, 1), text, pos) until text.empty?
     end
     #--------------------------------------------------------------------------
     # ● 重置字体设置
     #--------------------------------------------------------------------------
     def reset_font_settings
     change_color(normal_color)
     contents.font.size = Font.default_size
     contents.font.bold = false
     contents.font.italic = false
     end
     #--------------------------------------------------------------------------
     # ● 进行控制符的事前变换
     #    在实际绘制前、将控制符替换为实际的内容。
     #    为了减少歧异，文字「\」会被首先替换为转义符（\e）。
     #--------------------------------------------------------------------------
     def convert_escape_characters(text)
     result = text.to_s.clone
     result.gsub!(/\\/)            { "\e" }
     result.gsub!(/\e\e/)          { "\\" }
     result.gsub!(/\eV\[(\d+)\]/i) { $game_variables[$1.to_i] }
     result.gsub!(/\eV\[(\d+)\]/i) { $game_variables[$1.to_i] }
     result.gsub!(/\eN\[(\d+)\]/i) { actor_name($1.to_i) }
     result.gsub!(/\eP\[(\d+)\]/i) { party_member_name($1.to_i) }
     result.gsub!(/\eG/i)          { Vocab::currency_unit }
     result
     end
     #--------------------------------------------------------------------------
     # ● 获取第 n 号角色的名字
     #--------------------------------------------------------------------------
     def actor_name(n)
     actor = n >= 1 ? $game_actors[n] : nil
     actor ? actor.name : ""
     end
     #--------------------------------------------------------------------------
     # ● 获取第 n 号队伍成员的名字
     #--------------------------------------------------------------------------
     def party_member_name(n)
     actor = n >= 1 ? $game_party.members[n - 1] : nil
     actor ? actor.name : ""
     end
     #--------------------------------------------------------------------------
     # ● 文字的处理
     #     c    : 文字
     #     text : 绘制处理中的字符串缓存（字符串可能会被修改）
     #     pos  : 绘制位置 {:x, :y, :new_x, :height}
     #--------------------------------------------------------------------------
     def process_character(c, text, pos)
     case c
     when "\r"   # 回车
     return
     when "\n"   # 换行
     process_new_line(text, pos)
     when "\f"   # 翻页
     process_new_page(text, pos)
     when "\e"   # 控制符
     process_escape_character(obtain_escape_code(text), text, pos)
     else        # 普通文字
     process_normal_character(c, pos)
     end
     end
     #--------------------------------------------------------------------------
     # ● 处理普通文字
     #--------------------------------------------------------------------------
     def process_normal_character(c, pos)
     text_width = text_size(c).width
     draw_text(pos[:x], pos[:y], text_width * 2, pos[:height], c)
     pos[:x] += text_width
     end
     #--------------------------------------------------------------------------
     # ● 处理换行文字
     #--------------------------------------------------------------------------
     def process_new_line(text, pos)
     pos[:x] = pos[:new_x]
     pos[:y] += pos[:height]
     pos[:height] = calc_line_height(text)
     end
     #--------------------------------------------------------------------------
     # ● 处理翻页文字
     #--------------------------------------------------------------------------
     def process_new_page(text, pos)
     end
     #--------------------------------------------------------------------------
     # ● 获取控制符的实际形式（这个方法会破坏原始数据）
     #--------------------------------------------------------------------------
     def obtain_escape_code(text)
     text.slice!(/^[\$\.\|\^!><\{\}\\]|^[A-Z]+/i)
     end
     #--------------------------------------------------------------------------
     # ● 获取控制符的参数（这个方法会破坏原始数据）
     #--------------------------------------------------------------------------
     def obtain_escape_param(text)
     text.slice!(/^\[\d+\]/)[/\d+/].to_i rescue 0
     end
     #--------------------------------------------------------------------------
     # ● 控制符的处理
     #     code : 控制符的实际形式（比如“\C[1]”是“C”）
     #     text : 绘制处理中的字符串缓存（字符串可能会被修改）
     #     pos  : 绘制位置 {:x, :y, :new_x, :height}
     #--------------------------------------------------------------------------
     def process_escape_character(code, text, pos)
     case code.upcase
     when 'C'
     change_color(text_color(obtain_escape_param(text)))
     when 'I'
     process_draw_icon(obtain_escape_param(text), pos)
     when '{'
     make_font_bigger
     when '}'
     make_font_smaller
     end
     end
     #--------------------------------------------------------------------------
     # ● 处理控制符指定的图标绘制
     #--------------------------------------------------------------------------
     def process_draw_icon(icon_index, pos)
     draw_icon(icon_index, pos[:x], pos[:y])
     pos[:x] += 24
     end
     #--------------------------------------------------------------------------
     # ● 放大字体尺寸
     #--------------------------------------------------------------------------
     def make_font_bigger
     contents.font.size += 8 if contents.font.size <= 64
     end
     #--------------------------------------------------------------------------
     # ● 缩小字体尺寸
     #--------------------------------------------------------------------------
     def make_font_smaller
     contents.font.size -= 8 if contents.font.size >= 16
     end
     #--------------------------------------------------------------------------
     # ● 计算行高
     #     restore_font_size : 计算完成后是否恢复原本的字体尺寸？
     #--------------------------------------------------------------------------
     def calc_line_height(text, restore_font_size = true)
     result = [line_height, contents.font.size].max
     last_font_size = contents.font.size
     text.slice(/^.*$/).scan(/\e[\{\}]/).each do |esc|
     make_font_bigger  if esc == "\e{"
     make_font_smaller if esc == "\e}"
     result = [result, contents.font.size].max
     end
     contents.font.size = last_font_size if restore_font_size
     result
     end
     #--------------------------------------------------------------------------
     # ● 绘制值槽
     #     rate   : 比率（1.0 为满值）
     #     color1 : 渐变色的左端
     #     color2 : 渐变色的右端
     #--------------------------------------------------------------------------
     def draw_gauge(x, y, width, rate, color1, color2)
     fill_w = (width * rate).to_i
     gauge_y = y + line_height - 8
     contents.fill_rect(x, gauge_y, width, 6, gauge_back_color)
     contents.gradient_fill_rect(x, gauge_y, fill_w, 6, color1, color2)
     end
     #--------------------------------------------------------------------------
     # ● 绘制图标
     #     enabled : 有效的标志。false 的时候使用半透明效果绘制
     #--------------------------------------------------------------------------
     def draw_icon(icon_index, x, y, enabled = true)
     bitmap = Cache.system("Iconset")
     rect = Rect.new(icon_index % 16 * 24, icon_index / 16 * 24, 24, 24)
     contents.blt(x, y, bitmap, rect, enabled ? 255 : translucent_alpha)
     end
     #--------------------------------------------------------------------------
     # ● 绘制角色肖像图
     #     enabled : 有效的标志。false 的时候使用半透明效果绘制
     #--------------------------------------------------------------------------
     def draw_face(face_name, face_index, x, y, enabled = true)
     bitmap = Cache.face(face_name)
     rect = Rect.new(face_index % 4 * 96, face_index / 4 * 96, 96, 96)
     contents.blt(x, y, bitmap, rect, enabled ? 255 : translucent_alpha)
     bitmap.dispose
     end
     #--------------------------------------------------------------------------
     # ● 绘制人物行走图
     #--------------------------------------------------------------------------
     def draw_character(character_name, character_index, x, y)
     return unless character_name
     bitmap = Cache.character(character_name)
     sign = character_name[/^[\!\$]./]
     if sign && sign.include?('$')
     cw = bitmap.width / 3
     ch = bitmap.height / 4
     else
     cw = bitmap.width / 12
     ch = bitmap.height / 8
     end
     n = character_index
     src_rect = Rect.new((n%4*3+1)*cw, (n/4*4)*ch, cw, ch)
     contents.blt(x - cw / 2, y - ch, bitmap, src_rect)
     end
     #--------------------------------------------------------------------------
     # ● 获取 HP 的文字颜色
     #--------------------------------------------------------------------------
     def hp_color(actor)
     return knockout_color if actor.hp == 0
     return crisis_color if actor.hp < actor.mhp / 4
     return normal_color
     end
     #--------------------------------------------------------------------------
     # ● 获取 MP 的文字颜色
     #--------------------------------------------------------------------------
     def mp_color(actor)
     return crisis_color if actor.mp < actor.mmp / 4
     return normal_color
     end
     #--------------------------------------------------------------------------
     # ● 获取 TP 的文字颜色
     #--------------------------------------------------------------------------
     def tp_color(actor)
     return normal_color
     end
     #--------------------------------------------------------------------------
     # ● 绘制角色行走图
     #--------------------------------------------------------------------------
     def draw_actor_graphic(actor, x, y)
     draw_character(actor.character_name, actor.character_index, x, y)
     end
     #--------------------------------------------------------------------------
     # ● 绘制角色肖像图
     #--------------------------------------------------------------------------
     def draw_actor_face(actor, x, y, enabled = true)
     draw_face(actor.face_name, actor.face_index, x, y, enabled)
     end
     #--------------------------------------------------------------------------
     # ● 绘制名字
     #--------------------------------------------------------------------------
     def draw_actor_name(actor, x, y, width = 112)
     change_color(hp_color(actor))
     draw_text(x, y, width, line_height, actor.name)
     end
     #--------------------------------------------------------------------------
     # ● 绘制职业
     #--------------------------------------------------------------------------
     def draw_actor_class(actor, x, y, width = 112)
     change_color(normal_color)
     draw_text(x, y, width, line_height, actor.class.name)
     end
     #--------------------------------------------------------------------------
     # ● 绘制称号
     #--------------------------------------------------------------------------
     def draw_actor_nickname(actor, x, y, width = 180)
     change_color(normal_color)
     draw_text(x, y, width, line_height, actor.nickname)
     end
     #--------------------------------------------------------------------------
     # ● 绘制等级
     #--------------------------------------------------------------------------
     def draw_actor_level(actor, x, y)
     change_color(system_color)
     draw_text(x, y, 32, line_height, Vocab::level_a)
     change_color(normal_color)
     draw_text(x + 32, y, 24, line_height, actor.level, 2)
     end
     #--------------------------------------------------------------------------
     # ● 绘制强化／弱化状态的图标
     #--------------------------------------------------------------------------
     def draw_actor_icons(actor, x, y, width = 96)
     icons = (actor.state_icons + actor.buff_icons)[0, width / 24]
     icons.each_with_index {|n, i| draw_icon(n, x + 24 * i, y) }
     end
     #--------------------------------------------------------------------------
     # ● 以 当前值／最大值 这样的分数形式绘制当前值和最大值
     #     current : 当前值
     #     max     : 最大值
     #     color1  : 当前值的颜色
     #     color2  : 最大值的颜色
     #--------------------------------------------------------------------------
     def draw_current_and_max_values(x, y, width, current, max, color1, color2)
     change_color(color1)
     xr = x + width
     if width < 96
     draw_text(xr - 40, y, 42, line_height, current, 2)
     else
     draw_text(xr - 92, y, 42, line_height, current, 2)
     change_color(color2)
     draw_text(xr - 52, y, 12, line_height, "/", 2)
     draw_text(xr - 42, y, 42, line_height, max, 2)
     end
     end
     #--------------------------------------------------------------------------
     # ● 绘制 HP
     #--------------------------------------------------------------------------
     def draw_actor_hp(actor, x, y, width = 124)
     draw_gauge(x, y, width, actor.hp_rate, hp_gauge_color1, hp_gauge_color2)
     change_color(system_color)
     draw_text(x, y, 30, line_height, Vocab::hp_a)
     draw_current_and_max_values(x, y, width, actor.hp, actor.mhp,
     hp_color(actor), normal_color)
     end
     #--------------------------------------------------------------------------
     # ● 绘制 MP
     #--------------------------------------------------------------------------
     def draw_actor_mp(actor, x, y, width = 124)
     draw_gauge(x, y, width, actor.mp_rate, mp_gauge_color1, mp_gauge_color2)
     change_color(system_color)
     draw_text(x, y, 30, line_height, Vocab::mp_a)
     draw_current_and_max_values(x, y, width, actor.mp, actor.mmp,
     mp_color(actor), normal_color)
     end
     #--------------------------------------------------------------------------
     # ● 绘制 TP
     #--------------------------------------------------------------------------
     def draw_actor_tp(actor, x, y, width = 124)
     draw_gauge(x, y, width, actor.tp_rate, tp_gauge_color1, tp_gauge_color2)
     change_color(system_color)
     draw_text(x, y, 30, line_height, Vocab::tp_a)
     change_color(tp_color(actor))
     draw_text(x + width - 42, y, 42, line_height, actor.tp.to_i, 2)
     end
     #--------------------------------------------------------------------------
     # ● 绘制简单的状态
     #--------------------------------------------------------------------------
     def draw_actor_simple_status(actor, x, y)
     draw_actor_name(actor, x, y)
     draw_actor_level(actor, x, y + line_height * 1)
     draw_actor_icons(actor, x, y + line_height * 2)
     draw_actor_class(actor, x + 120, y)
     draw_actor_hp(actor, x + 120, y + line_height * 1)
     draw_actor_mp(actor, x + 120, y + line_height * 2)
     end
     #--------------------------------------------------------------------------
     # ● 绘制能力值
     #--------------------------------------------------------------------------
     def draw_actor_param(actor, x, y, param_id)
     change_color(system_color)
     draw_text(x, y, 120, line_height, Vocab::param(param_id))
     change_color(normal_color)
     draw_text(x + 120, y, 36, line_height, actor.param(param_id), 2)
     end
     #--------------------------------------------------------------------------
     # ● 绘制物品名称
     #     enabled : 有效的标志。false 的时候使用半透明效果绘制
     #--------------------------------------------------------------------------
     def draw_item_name(item, x, y, enabled = true, width = 172)
     return unless item
     draw_icon(item.icon_index, x, y, enabled)
     change_color(normal_color, enabled)
     draw_text(x + 24, y, width, line_height, item.name)
     end
     #--------------------------------------------------------------------------
     # ● 绘制货币数值（持有金钱之类的）
     #--------------------------------------------------------------------------
     def draw_currency_value(value, unit, x, y, width)
     cx = text_size(unit).width
     change_color(normal_color)
     draw_text(x, y, width - cx - 2, line_height, value, 2)
     change_color(system_color)
     draw_text(x, y, width, line_height, unit, 2)
     end
     #--------------------------------------------------------------------------
     # ● 获取能力值变化的绘制色
     #--------------------------------------------------------------------------
     def param_change_color(change)
     return power_up_color   if change > 0
     return power_down_color if change < 0
     return normal_color
     end
     end

     */
}
