package com.mygdx.game.action;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.actor.BaseActor;

/**
 * Created by ttwings on 2017/8/28.
 */
public interface TextureComponent {
    void init();
    void updata();
    void draw(Batch batch);
}
