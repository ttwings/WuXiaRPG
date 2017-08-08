package com.mygdx.game.rpgdata;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.manager.Constants;

/**
 * Created by Administrator on 2017/3/22.
 */
public class RpgBuilding {
    public int x0,y0,x1,y1,x2,y2,x3,y3,x4,y4;
    public int w,h;
    public String[][] buildMat;
    public int dirDoor,dirWindow;

    public RpgBuilding(int x0, int y0,int w, int h) {
        this.x0 = x0;
        this.y0 = y0;
        this.w = w;
        this.h = h;
        this.x1 = x1-w/2;
        this.y1 = y1+h/2;
        this.x2 = x2+w/2;
        this.y2 = y2+h/2;
        this.x3 = x3+w/2;
        this.y3 = y3-h/2;
        this.x4 = x4-w/2;
        this.y4 = y4-h/2;
        this.buildMat = new String[h][w];
    }

    public RpgBuilding(int x0,int y0,String[][] buildMat){
        this.x0 = x0;
        this.y0 = y0;
        this.buildMat = buildMat;
        this.h = buildMat.length;
        this.w = buildMat[0].length;
        this.x1 = x1-w/2;
        this.y1 = y1+h/2;
        this.x2 = x2+w/2;
        this.y2 = y2+h/2;
        this.x3 = x3+w/2;
        this.y3 = y3-h/2;
        this.x4 = x4-w/2;
        this.y4 = y4-h/2;
    }

    public void createFloor(String floor){
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                changeData(j,i,0,floor);
            }
        }
    }

    public void createSpace(){
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                buildMat[i][j] = "空气,空气,空气,空气";
            }
        }
    }

    public void createWall(String wall){
        for (int i=0;i<h;i++){
//            buildMat[i][0] +=","+ wall;
//            buildMat[i][w-1] +=","+ wall;
            changeData(0,i,1,wall);
            changeData(w-1,i,1,wall);
        }
        for (int j=0;j<w;j++){
//            buildMat[0][j] +=","+ wall;
//            buildMat[h-1][j] +=","+ wall;
            changeData(j,0,1,wall);
            changeData(j,h-1,1,wall);
        }
    }


    public  void createDoor(String door,int direction){
        if (direction == Constants.DIR_N){
            changeData(w/2,h-1,1,door);
        }
        if (direction == Constants.DIR_S){
            changeData(w/2,0,1,door);
        }
        if (direction == Constants.DIR_W){
            changeData(0,h/2,1,door);
        }
        if (direction == Constants.DIR_E){
            changeData(w-1,h/2,1,door);
        }

    }

    public void createWindow(String window,int direction){
        if (direction == Constants.DIR_S){
            changeData(w/2,h-1,1,window);
        }
        if (direction == Constants.DIR_N){
            changeData(w/2,0,1,window);
        }
        if (direction == Constants.DIR_W){
            changeData(0,h/2,1,window);
        }
        if (direction == Constants.DIR_E){
            changeData(w-1,h/2,1,window);
        }
    }

    public void creatObj(String obj,int x,int y){
        changeData(x,y,2,obj);
    }

    void changeData(int w,int h,int index,String tile){
        String[] tempStr = buildMat[h][w].split(",");

        tempStr[index] = tile;
        String dataStr = "";
        for (int i=0;i<tempStr.length;i++){
            dataStr =dataStr + tempStr[i]+",";
        }
        buildMat[h][w] = dataStr;
//        Gdx.app.debug("",dataStr);
    }
}
