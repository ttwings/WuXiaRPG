package com.mygdx.game.action;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.actor.TableData;

/**
 * Created by ttwings on 2017/8/27.
 */
public class Skill extends Image{
    TableData tableDate = new TableData();
    String areaType;
    int w,h,r;
    public Skill(TableData tableData){
        this.tableDate = tableData;
    }
    public void put(String property, Object value) {
        tableDate.put(property, value);
    }
    public String get(String property) {
        return tableDate.get(property);
    }
}
