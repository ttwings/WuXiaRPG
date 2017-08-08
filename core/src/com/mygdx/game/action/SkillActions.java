package com.mygdx.game.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.mygdx.game.actor.BaseActor;
/**
 * Created by Administrator on 2017/5/26.
 */
public class SkillActions {
	static public MoveByAction chongFeng(BaseActor actor, int length, float dur){
		float x =0,y=0;
		x = x+faceTo(actor).x*length;
		y = y+faceTo(actor).y*length;
		return Actions.moveBy(x,y,dur);

	}
	static public Vector2 faceTo(BaseActor actor){
		Vector2 v;
		switch(actor.faceTo){
			case FACE_E:v=new Vector2(1,0);break;
			case FACE_W:v=new Vector2(-1,0);break;
			case FACE_N:v=new Vector2(0,1);break;
			case FACE_S:v=new Vector2(0,-1);break;
			case FACE_SE:v=new Vector2(1,-1);break;
			case FACE_SW:v=new Vector2(-1,-1);break;
			case FACE_NE:v=new Vector2(1,1);break;
			case FACE_NW:v=new Vector2(-1,1);break;
			default:v=Vector2.Zero;
		}
		return v;
	}
	static public Vector2 faceTo(BaseActor actor,BaseActor target){
		Vector2 v1 = new Vector2(actor.getX(),actor.getY());
		Vector2 v2 = new Vector2(target.getX(),target.getY());
		Vector2 v = v2.sub(v1);
		v.nor();
		return v;
	}
	static public Vector2 handPos(BaseActor actor){
		Vector2 v = new Vector2(actor.getX(),actor.getY());
		v = v.add(faceTo(actor).x*32,faceTo(actor).y*32);
		return v;
	}
	static public Vector2 handPos(BaseActor actor,BaseActor target){
		Vector2 v = new Vector2(actor.getX(),actor.getY());
		v = v.add(faceTo(actor,target).x*32,faceTo(actor,target).y*32);
		return v;
	}
}
