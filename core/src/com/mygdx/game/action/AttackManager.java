package com.mygdx.game.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.game.manager.ReadData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
public class AttackManager {
    private AttackManager(){init();}
    private static AttackManager instance = new AttackManager();
    public static AttackManager getInstance(){
        return instance;
    }
    Map<String,Attack> attackMap = new HashMap<>();
    void init(){
        loadAttack("Data/Skills.txt");
    }

    void loadAttack(String file){
        attackMap = ReadData.attackMap(file);
    }
    public Attack getAttack(String name){
        Attack attack;
        if (attackMap.containsKey(name)){
            attack = attackMap.get(name);
        }else{
            attack = null;
        }
        return attack;
    }
}
