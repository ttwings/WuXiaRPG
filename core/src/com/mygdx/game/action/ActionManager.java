package com.mygdx.game.action;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.map.MapLocal;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Administrator on 2017/3/29.
 */
public class ActionManager implements GamepadKey {
    private ActionManager(){init();}
    private static ActionManager instance = new ActionManager();
    public  static ActionManager getInstance(){
        return instance;
    }
    public Map<String,GamepadKey> actionKeyMap = new HashMap<>();
    ActionMove actionMove = new ActionMove();
    ActionAttack actionAttack = new ActionAttack();
    public String actionName = "";
    void init(){
        actionKeyMap.put("",actionMove);
        actionKeyMap.put("移动",actionMove);
        actionKeyMap.put("闲逛",actionMove);
        actionKeyMap.put("整理",actionMove);
        actionKeyMap.put("制作",actionMove);
        actionKeyMap.put("出招",actionAttack);
        actionKeyMap.put("战斗",actionAttack);
        actionKeyMap.put("专长",actionAttack);
    }
    @Override
    public void passU(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passU(mapLocal,actor);
    }
    @Override
    public void passD(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passD(mapLocal,actor);
    }
    @Override
    public void passL(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passL(mapLocal,actor);
    }
    @Override
    public void passR(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passR(mapLocal,actor);
    }
    @Override
    public void passA(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passA(mapLocal,actor);
    }
    @Override
    public void passB(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passB(mapLocal,actor);
    }
    @Override
    public void passX(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passX(mapLocal,actor);
    }
    @Override
    public void passY(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passY(mapLocal,actor);
    }
    @Override
    public void passL1(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passL1(mapLocal,actor);
    }
    @Override
    public void passL2(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passL2(mapLocal,actor);
    }
    @Override
    public void passR1(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passR1(mapLocal,actor);
    }
    @Override
    public void passR2(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passR2(mapLocal,actor);
    }
    @Override
    public void passStart(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passStart(mapLocal,actor);
    }
    @Override
    public void passBack(MapLocal mapLocal, BaseActor actor) {
        actionKeyMap.get(actionName).passBack(mapLocal,actor);
    }
}
