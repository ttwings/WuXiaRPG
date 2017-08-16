package com.mygdx.game.actor;

import com.thoughtworks.qdox.model.util.OrderedMap;
import java.util.Map;

/**
 * Created by ttwings on 2017/8/16.
 */
public class TableData {
    Map<String,Object> propertyMap = new OrderedMap();
//    属性对应的值，例如名称、年龄、坐标
    public String get(String property){
        if (property.contains(property)){
            return (String) propertyMap.get(property);
        }else{
            return "空";
        }
    }
//    更新对应的属性值
    public void put(String property,Object value){
        propertyMap.put(property, value);
    }
}
