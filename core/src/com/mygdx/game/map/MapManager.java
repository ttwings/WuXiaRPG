package com.mygdx.game.map;

import com.mygdx.game.manager.ReadData;

/**
 * Created by Administrator on 2017/3/10.
 * 地图管理类，主要功能   1、生成世界地图 2、生成局部地图 3、地图信息与tile图片的链接
 * ps：地图类作为一个主类，所有的游戏运算显示，都应该在该类里面显示，即 基本的物品，人物，人物背包等
 * ps：需要对应的 stage 类，显示地图数据。将地图信息的转化，放入到stage中。基本的运算也在该类中进行
 *
 */
public class MapManager {
    private MapManager(){init();}
    private static MapManager instance = new MapManager();
    public static MapManager getInstance(){
        return instance;
    }
    public MapLocal mapLocal;
    public MapRegion mapRegion;

    public void init(){
    }

    public void initRegionMap(){
    }

    void readMapMat(String name){

    }

    public MapLocal getMapLocal(){
        return mapLocal;
    }


}
