package com.mygdx.game.gameClass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.manager.Cache;
/**
 * Created by Administrator on 2017/4/12.
 */
public class Tile{
	public int ID;
	public String name;
	public String tileSet;
	public int w,h;
	public int x,y;
	public int offx,offy;
	public TextureRegion textureRegion;
	public Tile(String[] datas){
		Texture texture;
		ID = Integer.parseInt(datas[0]);
		name = datas[1];
		tileSet = datas[2];
		w = Integer.parseInt(datas[3]);
		h = Integer.parseInt(datas[4]);
		x = Integer.parseInt(datas[5]);
		y = Integer.parseInt(datas[6]);
		offx = Integer.parseInt(datas[7]);
		offy = Integer.parseInt(datas[8]);
		texture =  Cache.instance().tileset(tileSet);
		textureRegion = new TextureRegion(texture,x,y,w,h);
	}
}
