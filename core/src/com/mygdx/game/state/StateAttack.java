package com.mygdx.game.state;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.game.actor.BaseActor;

/**
 * Created by ttwings on 2017/8/26.
 */
public class StateAttack implements State<BaseActor> {
    @Override
    public void enter(BaseActor entity) {

    }

    @Override
    public void update(BaseActor entity) {

    }

    @Override
    public void exit(BaseActor entity) {

    }

    @Override
    public boolean onMessage(BaseActor entity, Telegram telegram) {
        return false;
    }
}
