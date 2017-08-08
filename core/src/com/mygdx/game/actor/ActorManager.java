package com.mygdx.game.actor;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.manager.ReadData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 16/12/31.
 */
public class ActorManager {
    private ActorManager(){init();}
    private static ActorManager instance = new ActorManager();
    public  static ActorManager getInstance(){
        return instance;
    }
    ArrayList<String> nameList;
    public String[] firstName,fSecondName,mSecondName;
    public Map<String,BaseActor> actorMap = new HashMap<>();
    void init(){
        //    readActor("慕容复");
        //    readActor("虚竹");
    }
    //read names
    public void readNames(String file){
        nameList = ReadData.readArrayFromFile(file);
        firstName = nameList.get(0).split("\t");
        mSecondName = nameList.get(1).split("\t");
        fSecondName = nameList.get(2).split("\t");
    }
    public boolean isHave(String name){
        return actorMap.containsKey(name);
    }

    public String getNames(Boolean isFemale){
        int l1,l2,l3;
        l1 = firstName.length;
        l2 = mSecondName.length;
        l3 = fSecondName.length;
        String name,first,fSecond,mSecond;
        first =  firstName[MathUtils.random(l1)];
        mSecond = mSecondName[MathUtils.random(l2)];
        fSecond = fSecondName[MathUtils.random(l3)];
        if (isFemale){
            name = first+fSecond;
        }else {
            name = first+mSecond;
        }
        return name;
    }
}
