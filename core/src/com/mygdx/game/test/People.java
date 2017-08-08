package com.mygdx.game.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.manager.EnumAction;
import com.mygdx.game.rpgdata.RpgActor;

public class People extends RpgActor{
	// Head texture;
	public Texture headTexture;
	// Animation Texture;
	public Texture animationTexture;
	//	State of people
	public EnumAction enumAction;
	//	Current Animation
	public Animation animation;
	//	Animation of move different direction;
	public Animation moveU;
	public Animation moveD;
	public Animation moveL;
	public Animation moveR;
	//	Face to direction
	public int faceDirection;
	//	Currents frames
	public TextureRegion[] frames;
	public TextureRegion[] moveDframes;
	public TextureRegion[] moveUframes;
	public TextureRegion[] moveLframes;
	public TextureRegion[] moveRframes;
	public int frameIndex;

	// Run length;
	public int runLength;
	public int moveLength;
	float stateTime = 0.1f;
	float deltaTime = 0.1f;
	//	TextureRegion currentFrame;
	public TextureRegion currentFrame;
	public String[] skillBattles;
	public String skill;
	public int change =0;
	public People(){
		setName("东方曦");
		setX(100);
		setY(100);
		runLength = 2;
		moveLength = 4;
		enumAction = EnumAction.stop;
		animation = moveU;
		creatMoveCurrentFrames(0,0,0,0);
		frameIndex = 0;
		frames = moveLframes;
		currentFrame = frames[frameIndex];
		skillBattles = new String[]{"少商剑","商阳剑","中冲剑","关冲剑","少冲剑","少泽剑"};
		skill = "";
		change = 0;
	};
	public void update(float deltaTime){

	}
	public void switchMoveAni(EnumAction enumAction){
		switch(enumAction){
			case moveU:frames = moveUframes;
				break;
			case moveD:frames = moveDframes;
				break;
			case moveL:frames = moveLframes;
				break;
			case moveR:frames = moveRframes;
				break;
			case stop:
				break;
			case run:
				break;
			default:
				break;
		}
	}
	public void creatMoveCurrentFrames(int x0,int y0,int x1,int y1){
		TextureRegion[][] actorRegions = TextureRegion.split(animationTexture, 96, 128);
		TextureRegion[][] actorRegion = actorRegions[x0][y0].split(32, 32);
		TextureRegion[] moveTemp;
		moveTemp = new TextureRegion[]{actorRegion[x1][y1],actorRegion[x1][y1+1],actorRegion[x1][y1+2]};
		moveDframes = moveTemp;
		moveTemp = new TextureRegion[]{actorRegion[x1+2][y1],actorRegion[x1+2][y1+1],actorRegion[x1+2][y1+2]};
		moveRframes = moveTemp;
		moveTemp = new TextureRegion[]{actorRegion[x1+1][y1],actorRegion[x1+1][y1+1],actorRegion[x1+1][y1+2]};
		moveLframes = moveTemp;
		moveTemp = new TextureRegion[]{actorRegion[x1+3][y1],actorRegion[x1+3][y1+1],actorRegion[x1+3][y1+2]};
		moveUframes = moveTemp;
	}
	public void move(int x,int y){
		this.setX(this.getX()+x);
		this.setY(this.getY()+y);
	}
}
