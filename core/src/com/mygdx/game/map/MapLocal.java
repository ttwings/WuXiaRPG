package com.mygdx.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.manager.ReadData;

/**
 * 本地地图
 * @author ttwings
 * @version 0.1
 * @since 2013-12-30 下午3:46:30
 */
public class MapLocal {
	//	在区域中的位置，在世界地图中的位置
	public Vector2 posRegion;
	public Vector2 posWorld;
	//	区域宽，长，高，
	public int w, h, l;
	//	区域所处的气候带
	public String bolilmStr;
	//	区域现在的天气
	public String weatherStr;
	//	区域现在的气温
	public int temperature;
	//	区域现在的风力,风向
	public Vector2 wind2D;
	//	区域地形高度
	public int[][] level;
	//	地图图块数据
	//
	public String[][] datas;
	//	地板纹理
	//	public EnumFloor[][] floor;
	//	植被
	//	覆盖层
	//	物品层
	//	public String[][] objStr;
	//	人物层
	public TiledMap tiledMap;
	public String[][] actorMat;

	public MapLocal(Vector2 posRegion, Vector2 posWorld, int w, int h, int l, String bolilmStr, String weatherStr, int temperature, Vector2 wind2D, int[][] level, String[][] datas) {
		this.posRegion = posRegion;
		this.posWorld = posWorld;
		this.w = w;
		this.h = h;
		this.l = l;
		this.bolilmStr = bolilmStr;
		this.weatherStr = weatherStr;
		this.temperature = temperature;
		this.wind2D = wind2D;
		this.level = level;
		this.datas = datas;
	}

	public MapLocal() {
		this.posRegion = new Vector2(0, 0);
		this.posWorld = new Vector2(0, 0);
		this.w = 0;
		this.h = 0;
		this.l = 0;
		this.bolilmStr = null;
		this.weatherStr = null;
		this.temperature = 0;
		this.wind2D = new Vector2(0, 0);
		this.level = null;
		this.datas = null;
		this.actorMat = null;
	}


	public MapLocal(int w, int h) {
		int rand;
		this.w = w;
		this.h = h;
		l = 0;
		datas = new String[h][w];
		for(int i = 0; i < h; ++i) {
			for(int j = 0; j < w; ++j) {
				datas[i][j] = "土1,空气,空气,空气";
				if(MathUtils.random(10) % 2 == 0) {
					datas[i][j] = "草1,空气,空气,空气";
				}
				if(MathUtils.random(10) == 5) {
					rand = MathUtils.random(10) + 1;
					datas[i][j] = "土1,空气,野生植物" + rand + ",空气";
				}
				if(MathUtils.random(10) == 9) {
					rand = MathUtils.random(25) + 1;
					datas[i][j] = "草1,空气,空气,树木" + rand;
				}
			}
		}
	}
	public void readMatData(String file,int x,int y,int w,int h){
		String[][] matDatas = ReadData.readStrMat(file);
		String[][] tempMat = new String[w][h];
		for (int i=0;i<h;i++){
			for (int j=0;j<w;j++){
				datas[i][j] = tempMat[i][j] = matDatas[y+i][x+j];
			}
		}
		this.w = datas[0].length;
		this.h = datas.length;
	}

	public void readMatDataSwapY(String file,int x,int y,int w,int h){
		String[][] matDatas = ReadData.readStrMatSwapY(file);
		String[][] tempMat = new String[w][h];
		for (int i=0;i<w;i++){
			for (int j=0;j<w;j++){
				datas[i][j] = tempMat[i][j] = matDatas[y+i][x+j];
			}
		}
		this.w = datas[0].length;
		this.h = datas.length;
	}

	void changeTile(MapLocal mapLocal,int x, int y, int index, String tile){
		String[] tempStr = mapLocal.datas[y][x].split(",");
		tempStr[index] = tile;
		String dataStr = "";
		for (int i=0;i<tempStr.length;i++){
			dataStr =dataStr + tempStr[i]+",";
		}
		mapLocal.datas[y][x] = dataStr;
		Gdx.app.debug("",dataStr);
	}
	String  getTile(MapLocal mapLocal,int x,int y,int index){
		String str1,str2;
		str1 = mapLocal.datas[x][y].split(",")[index];
		if(str1!="空气"){
			str2 = str1;
			changeTile(mapLocal,x,y,index,"空气");
		}else {
			str2="";
		}
		return str2;
	}


}