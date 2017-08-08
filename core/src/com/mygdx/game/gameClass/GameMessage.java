package com.mygdx.game.gameClass;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/9.
 */
public class GameMessage {
    /**
     * #encoding:utf-8
     #==============================================================================
     # ■ Game_Message
     #------------------------------------------------------------------------------
     # 　处理信息窗口状态、文字显示、选项等的类。本类的实例请参考 $game_message 。
     #==============================================================================

     class Game_Message
     #--------------------------------------------------------------------------
     # ● 定义实例变量
     #--------------------------------------------------------------------------
     attr_reader   :texts                    # 文字数组（行单位）
     attr_reader   :choices                  # 选项数组
     attr_accessor :face_name                # 肖像文件名
     attr_accessor :face_index               # 肖像索引
     attr_accessor :background               # 背景类型
     attr_accessor :position                 # 显示位置
     attr_accessor :choice_proc              # 选项 回调（Proc）
     attr_accessor :choice_cancel_type       # 选项 取消的场合
     attr_accessor :num_input_variable_id    # 数值输入 变量ID
     attr_accessor :num_input_digits_max     # 数值输入 列数
     attr_accessor :item_choice_variable_id  # 物品选择 变量ID
     attr_accessor :scroll_mode              # 滚动文字的标志
     attr_accessor :scroll_speed             # 滚动文字：滚动速度
     attr_accessor :scroll_no_fast           # 滚动文字：禁止快进
     attr_accessor :visible                  # 信息显示中
     */
     ArrayList<String> texts;
     ArrayList<String> choices;
     String faceName;
     int faceIndex;
     int background;
     Vector2 pos;
     String choiceProc;
     String choiceCancelType;
     int numInputVariableId;
     int inputDigitsMax;
     int itemChoiceVariableId;
     int scrollMode;
     int scrollSpeed;
     boolean scrollNoFast;
     boolean visible;
    /**
     #--------------------------------------------------------------------------
     # ● 初始化对象
     #--------------------------------------------------------------------------
     def initialize
     clear
     @visible = false
     end
     */
    public  void init(){
         clear();
         visible = false;
    }
    /**
     #--------------------------------------------------------------------------
     # ● 清除
     #--------------------------------------------------------------------------
    */
    public void clear(){
     texts = new ArrayList<String>();
     choices = new ArrayList<String>();
     faceName = "";
     faceIndex = 0;
     background = 0;

    }
    /**
     def clear
     @texts = []
     @choices = []
     @face_name = ""
     @face_index = 0
     @background = 0
     @position = 2
     @choice_cancel_type = 0
     @choice_proc = nil
     @num_input_variable_id = 0
     @num_input_digits_max = 0
     @item_choice_variable_id = 0
     @scroll_mode = false
     @scroll_speed = 2
     @scroll_no_fast = false
     end
     #--------------------------------------------------------------------------
     # ● 添加内容
     #--------------------------------------------------------------------------
     def add(text)
     @texts.push(text)
     end
     #--------------------------------------------------------------------------
     # ● 判定是否存在显示内容
     #--------------------------------------------------------------------------
     def has_text?
     @texts.size > 0
     end
     #--------------------------------------------------------------------------
     # ● 判定是否为选择模式
     #--------------------------------------------------------------------------
     def choice?
     @choices.size > 0
     end
     #--------------------------------------------------------------------------
     # ● 判定是否为数值输入模式
     #--------------------------------------------------------------------------
     def num_input?
     @num_input_variable_id > 0
     end
     #--------------------------------------------------------------------------
     # ● 判定是否为物品选择模式
     #--------------------------------------------------------------------------
     def item_choice?
     @item_choice_variable_id > 0
     end
     #--------------------------------------------------------------------------
     # ● 判定是否为繁忙状态
     #--------------------------------------------------------------------------
     def busy?
     has_text? || choice? || num_input? || item_choice?
     end
     #--------------------------------------------------------------------------
     # ● 翻页
     #--------------------------------------------------------------------------
     def new_page
     @texts[-1] += "\f" if @texts.size > 0
     end
     #--------------------------------------------------------------------------
     # ● 获取包括换行符的所有内容
     #--------------------------------------------------------------------------
     def all_text
     @texts.inject("") {|r, text| r += text + "\n" }
     end
     end

     */
}
