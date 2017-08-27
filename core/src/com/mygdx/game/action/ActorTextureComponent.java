package com.mygdx.game.action;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.manager.Cache;
import com.mygdx.game.manager.EnumAction;
import com.mygdx.game.manager.FontManager;
import com.mygdx.game.tools.LazyBitmapFont;

/**
 * Created by ttwings on 2017/8/28.
 */
public class ActorTextureComponent implements TextureComponent{
    Texture texture;
    TextureRegion[][] textureRegions;
    TextureRegion nowTexture;
    int w,h,tileW,tileH;
    BaseActor actor;
    LazyBitmapFont font;
    public ActorTextureComponent(BaseActor actor){
        this.actor = actor;
    }
    @Override
    public void init() {
        texture = Cache.instance().character(actor.get("行走图"));
        w = texture.getWidth();
        h = texture.getHeight();
        tileW = w/4;
        tileH = h/4;
        textureRegions = TextureRegion.split(texture,tileW,tileH);
        nowTexture = textureRegions[0][0];
        font = FontManager.getInstance().fontL;
//        action1 = Actions.moveBy(100,100);
//        action2 = Actions.moveBy(-100,0);
//        action3 = Actions.moveBy(0,100);
    }
    Action action1,action2,action3;
    @Override
    public void updata() {
        nowTexture = moveFrame(textureRegions, actor.enumAction, actor.turn);
//        actor.addAction(Actions.sequence(action1,action2,action3));
//        actor.addAction(Actions.moveTo(100,0,2));
//        actor.addAction(Actions.moveBy(0,100,4));
    }

    @Override
    public void draw(Batch batch) {
        font.draw(batch,actor.get("名称"),actor.getX(),actor.getY()+70);
        batch.draw(nowTexture, actor.getX(), actor.getY());
    }

    public TextureRegion moveFrame(TextureRegion[][] regions, EnumAction action, int turn){
        int index = turn%4;
        TextureRegion[] frames = new TextureRegion[4];
        if(action == EnumAction.MOVE_S){
            frames[0] = regions[0][0];
            frames[1] = regions[0][1];
            frames[2] = regions[0][2];
            frames[3] = regions[0][3];
        }if(action == EnumAction.MOVE_W){
            frames[0] = regions[1][0];
            frames[1] = regions[1][1];
            frames[2] = regions[1][2];
            frames[3] = regions[1][3];
        }if(action == EnumAction.MOVE_E){
            frames[0] = regions[2][0];
            frames[1] = regions[2][1];
            frames[2] = regions[2][2];
            frames[3] = regions[2][3];
        }if(action == EnumAction.MOVE_N){
            frames[0] = regions[3][0];
            frames[1] = regions[3][1];
            frames[2] = regions[3][2];
            frames[3] = regions[3][3];
        }

        return frames[index];
    }
}
