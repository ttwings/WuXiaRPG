package com.mygdx.game.action;

import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.map.MapLocal;

/**
 * Created by Administrator on 2017/3/29.
 */
public interface GamepadKey {
//    按键接口，所有action共享 同时npc也可以调用
    void passU(MapLocal mapLocal,BaseActor actor);
    void passD(MapLocal mapLocal,BaseActor actor);
    void passL(MapLocal mapLocal,BaseActor actor);
    void passR(MapLocal mapLocal,BaseActor actor);
    void passA(MapLocal mapLocal,BaseActor actor);
    void passB(MapLocal mapLocal,BaseActor actor);
    void passX(MapLocal mapLocal,BaseActor actor);
    void passY(MapLocal mapLocal,BaseActor actor);
    void passL1(MapLocal mapLocal,BaseActor actor);
    void passL2(MapLocal mapLocal,BaseActor actor);
    void passR1(MapLocal mapLocal,BaseActor actor);
    void passR2(MapLocal mapLocal,BaseActor actor);
    void passStart(MapLocal mapLocal,BaseActor actor);
    void passBack(MapLocal mapLocal,BaseActor actor);
}
