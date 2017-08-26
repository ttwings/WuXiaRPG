package com.mygdx.game.map;
/**
 * Created by Administrator on 2017/4/20.
 * 房间信息，地图划分为多个房间。不同的房间，放置不同的东西，比如厨房，有米饭可以吃，有茶水可以喝。
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
	public Room(String[] datas){
		ID = datas[0];
		name = datas[1];
		rx = Integer.parseInt(datas[2]);
		ry = Integer.parseInt(datas[3]);
		inside = datas[4];
		upRoom = datas[5];
		downRoom = datas[6];
		noFight = datas[7];
//		房间承载的功能，比如酒馆，可以买卖酒水。厨房可以做饭，吃饭。
		actions = datas[8].split("、");
		names = datas[9].split("、");
//		房间中的物品，近期测试房间的物品放到tiledmap中去。
		objs = datas[10].split("、");
		describe = datas[11];
	}
}
