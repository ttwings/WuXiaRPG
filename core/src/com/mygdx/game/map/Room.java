package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
/**
 * Created by Administrator on 2017/4/20.
 */
public class Room {
   	public String ID;
	public String name;
	public int rx;
	public int ry;
	public String inside;
	public String upRoom;
	public String downRoom;
	public String noFight;
	public String[] actions;
	public String[] names;
	public String[] objs;
	public String describe;
	public TiledMap tiledMap;
//	public String[][] mapDatas;
	public Room(String[] datas){
		ID = datas[0];
		name = datas[1];
		rx = Integer.parseInt(datas[2]);
		ry = Integer.parseInt(datas[3]);
		inside = datas[4];
		upRoom = datas[5];
		downRoom = datas[6];
		noFight = datas[7];
		actions = datas[8].split("、");
		names = datas[9].split("、");
		objs = datas[10].split("、");
		describe = datas[11];
	}
}
