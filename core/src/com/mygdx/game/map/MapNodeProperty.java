package com.mygdx.game.map;

public class MapNodeProperty {
	byte hot2cold;
	byte dry2wet;
	byte lite2dark;
	byte open2close;
	public MapNodeProperty(byte hot2cold, byte dry2wet, byte lite2dark, byte open2close) {
		super();
		this.hot2cold=hot2cold;
		this.dry2wet=dry2wet;
		this.lite2dark=lite2dark;
		this.open2close=open2close;
	}
	/**
	 * @param hot2cold 		热到冷
	 * @param dry2wet 		干燥到湿润
	 * @param lite2dark 	光亮到阴暗
	 * @param open2close 	开放到封闭
	 */
	public MapNodeProperty(int hot2cold, int dry2wet, int lite2dark, int open2close) {
		super();
		this.hot2cold=(byte)hot2cold;
		this.dry2wet=(byte)dry2wet;
		this.lite2dark=(byte)lite2dark;
		this.open2close=(byte)open2close;
	}
	public MapNodeProperty(){
		this.hot2cold=0;
		this.dry2wet=0;
		this.lite2dark=0;
		this.open2close=0;
	}
}
