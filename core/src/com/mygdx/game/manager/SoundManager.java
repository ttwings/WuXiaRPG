package com.mygdx.game.manager;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
/**
 * Created by Administrator on 2016/12/26.
 * 游戏，背景音乐，各类音效管理类。
 */
public class SoundManager {
    private SoundManager(){}
    private static SoundManager instance = new SoundManager();
    public static SoundManager getInstance(){
        return instance;
    }
    public AssetManager assetManager;
    public void load(String fileName){
        assetManager.load(fileName, Music.class);
        assetManager.finishLoading();
    }
    public Music get(String fileName){
        return assetManager.get(fileName);
    }
    /**
     * #encoding:utf-8
     #==============================================================================
     # ■ Sound
     #------------------------------------------------------------------------------
     #  本模块用于播放声效。会自动获取并播放数据库中 $data_system 设置好的声效。
     #==============================================================================
     module Sound
     # 系统声音
     def self.play_system_sound(n)
     $data_system.sounds[n].play
     end
     # 光标移动
     def self.play_cursor
     play_system_sound(0)
     end
     # 确定
     def self.play_ok
     play_system_sound(1)
     end
     # 取消
     def self.play_cancel
     play_system_sound(2)
     end
     # 无效
     def self.play_buzzer
     play_system_sound(3)
     end
     # 装备
     def self.play_equip
     play_system_sound(4)
     end
     # 存档
     def self.play_save
     play_system_sound(5)
     end
     # 读档
     def self.play_load
     play_system_sound(6)
     end
     # 战斗开始
     def self.play_battle_start
     play_system_sound(7)
     end
     # 撤退
     def self.play_escape
     play_system_sound(8)
     end
     # 敌人普通攻击
     def self.play_enemy_attack
     play_system_sound(9)
     end
     # 敌人受到伤害
     def self.play_enemy_damage
     play_system_sound(10)
     end
     # 敌人被消灭
     def self.play_enemy_collapse
     play_system_sound(11)
     end
     # 首领被消灭 1
     def self.play_boss_collapse1
     play_system_sound(12)
     end
     # 首领被消灭 2
     def self.play_boss_collapse2
     play_system_sound(13)
     end
     # 队友受到伤害
     def self.play_actor_damage
     play_system_sound(14)
     end
     # 队友无法战斗
     def self.play_actor_collapse
     play_system_sound(15)
     end
     # 恢复
     def self.play_recovery
     play_system_sound(16)
     end
     # 落空
     def self.play_miss
     play_system_sound(17)
     end
     # 闪避普通攻击
     def self.play_evasion
     play_system_sound(18)
     end
     # 闪避魔法攻击
     def self.play_magic_evasion
     play_system_sound(19)
     end
     # 反射魔法攻击
     def self.play_reflection
     play_system_sound(20)
     end
     # 商店
     def self.play_shop
     play_system_sound(21)
     end
     # 使用物品
     def self.play_use_item
     play_system_sound(22)
     end
     # 使用技能
     def self.play_use_skill
     play_system_sound(23)
     end
     end
     */
}
