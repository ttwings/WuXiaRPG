package com.mygdx.game.map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.manager.ReadData;

/**
 * Created by Administrator on 2017/3/30.
 */
public class MapRegion {
    public String name;
    //	在区域中的位置，在世界地图中的位置
    public 	Vector2 posWorld;
    //	区域宽，长，高，
    public 	int w,h,l;
    //	区域地形高度
    public int[][] level;
    //	地图图块数据
    public String[][] floorMat;
    public String[][] buildMat;

    public MapRegion(String name,int w,int h){
        this.name = name;
        this.w = w;
        this.h = h;
        this.floorMat = new String[h][w];
        this.buildMat = new String[h][w];
    }
    public MapRegion(){
        this.name = "";
        this.w = 0;
        this.h = 0;
        this.floorMat = new String[h][w];
        this.buildMat = new String[h][w];
    }

    public void readRegionMap(String filePath){
        floorMat = ReadData.readStrMatSwapY(filePath);
        w = floorMat[0].length;
        h = floorMat.length;
    }

}
