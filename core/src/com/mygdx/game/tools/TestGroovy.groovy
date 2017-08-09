package com.mygdx.game.tools

import com.mygdx.game.actor.BaseActor
import com.mygdx.game.manager.ReadData

/**
 * Created by ttwings on 2017/8/7.
 */
class TestGroovy{
//    BaseActor actor = ReadData.actorMap("Data/Actors.txt").get("段誉")
    BaseActor actor
    void printActorName(String s){
        println(actor.name+s)
    }
    void printName(String name){
        println("my name is "+name)
    }
}
