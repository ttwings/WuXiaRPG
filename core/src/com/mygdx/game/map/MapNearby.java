package com.mygdx.game.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.manager.Cache;

/**
 * Created by Administrator on 2017/3/17.
 */
public class MapNearby {

    Cache cache = Cache.instance();
    String[][] mapData;
    TextureRegion map,mapN,mapS,mapE,mapW;

    void createNearbyMap(int x0, int y0, int x1, int y1, int index){
        String tileStr;
        int w,h;
        w = x1-x0+1;
        h = y1-y0+1;
        TextureRegion[][] mapN,mapS,mapE,mapW,map;
        map = new TextureRegion[h][w];

        for(int i=0;i<h;++i){
            for(int j=0;j<w;++j){
                tileStr = mapData[y0+i][x0+j].split(",")[index];
                map[i][j] = cache.tileMap.get(tileStr).textureRegion;
            }
        }
    }

}
