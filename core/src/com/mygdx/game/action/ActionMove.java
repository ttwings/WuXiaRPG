package com.mygdx.game.action;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.manager.EnumAction;
import com.mygdx.game.map.MapLocal;
import com.mygdx.game.stage.StageMap;
/**
 * Created by Administrator on 2017/3/29.
 */
public class ActionMove implements GamepadKey {
    @Override
    public void passU(MapLocal mapLocal, BaseActor actor) {
//        move(mapLocal,actor,0,1);
        actor.move(0,1);
        actor.enumAction = EnumAction.MOVE_N;
        actor.faceTo = EnumAction.FACE_N;
    }
    @Override
    public void passD(MapLocal mapLocal, BaseActor actor) {
//        move(mapLocal,actor,0,-1);
        actor.move(0,-1);
        actor.enumAction = EnumAction.MOVE_S;
        actor.faceTo = EnumAction.FACE_S;
    }
    @Override
    public void passL(MapLocal mapLocal, BaseActor actor) {
//        move(mapLocal,actor,-1,0);
        actor.move(-1,0);
        actor.enumAction = EnumAction.MOVE_W;
        actor.faceTo = EnumAction.FACE_W;
    }
    @Override
    public void passR(MapLocal mapLocal, BaseActor actor) {
//        move(mapLocal,actor,1,0);
        actor.move(1,0);
//        actor.addAction(Actions.moveBy(100,100,2));
        actor.enumAction = EnumAction.MOVE_E;
        actor.faceTo = EnumAction.FACE_E;
    }
    @Override
    public void passA(MapLocal mapLocal, BaseActor actor) {
//  直接获得物品
//  1、判断地图中物品对象与actor的距离 2、显示最近的物品的名称 3、按键时判断背包大小获取物品
        for (int i=0;i<10;i++){
            if (actor.items[i].length()<2 && actor.objName.length()>0){
                actor.items[i] = actor.objName;
                mapLocal.removeObj("objs",actor.objName);
                StageMap.getInstance().addMsg("得到："+actor.objName);
                break;
            }else if (i==10){
                StageMap.getInstance().addMsg("包满了，装不下了");
            }
        }
    }
    @Override
    public void passB(MapLocal mapLocal, BaseActor actor) {
    }
    @Override
    public void passX(MapLocal mapLocal, BaseActor actor) {
//        互动测试

    }
    @Override
    public void passY(MapLocal mapLocal, BaseActor actor) {
    }
    @Override
    public void passL1(MapLocal mapLocal, BaseActor actor) {
//        actor.actionStr = "战斗";
        actor.actionIndex++;
    }
    @Override
    public void passL2(MapLocal mapLocal, BaseActor actor) {
        actor.actionIndex--;
    }
    @Override
    public void passR1(MapLocal mapLocal, BaseActor actor) {
//        调息
    }
    @Override
    public void passR2(MapLocal mapLocal, BaseActor actor) {
//        actor.actionStr = "制作";
    }
    @Override
    public void passStart(MapLocal mapLocal, BaseActor actor) {
        //        moveRand(mapLocal,actor);
        //        moveTo(mapLocal,actor,40,40);
    }
    @Override
    public void passBack(MapLocal mapLocal, BaseActor actor) {
    }
    void moveRand(MapLocal mapLocal,BaseActor baseActor){
        int rx,ry;
        rx = MathUtils.random(-1,1);
        ry = MathUtils.random(-1,1);
        move(mapLocal,baseActor,rx,ry);
    }
    boolean arrive(MapLocal mapLocal,BaseActor baseActor,int x,int y){
        int dx,dy;
        dx = Math.abs(baseActor.lx-x);
        dy = Math.abs(baseActor.ly-y);
        return (dx==dy && dx==0);
    }
    void moveTo(MapLocal mapLocal,BaseActor baseActor,int x,int y){
        int distance;
        int dx,dy;
        dx = (int) Math.abs(baseActor.getX()-x);
        dy = (int) Math.abs(baseActor.getY()-y);
        if (!arrive(mapLocal,baseActor,x,y)){
            if (dx>dy){
                if (baseActor.getX()>x){
                    move(mapLocal,baseActor,-1,0);
                }else {
                    move(mapLocal,baseActor,1,0);
                }
            }else {
                if (baseActor.getY()>y){
                    move(mapLocal,baseActor,0,-1);
                }else {
                    move(mapLocal,baseActor,0,1);
                }
            }
        }
    }
    void move(MapLocal mapLocal,BaseActor baseActor,int x,int y){

        final int x0,y0;
        String[] datas;
        x0 = baseActor.lx + x;
        y0 = baseActor.ly + y;

        if(x0>-1 && y0>-1 && x0<16 && y0<16){
//            datas=mapLocal.datas[y0][x0].split(",");
//            if (datas[1].equals("空气")){
                baseActor.move(x,y);
//                if (!datas[2].equals("空气")){
//                }
//            }
        } else if(x0 == -1){
            baseActor.rx-=2;
            baseActor.lx = 15;
        }  else if(y0== -1){
            baseActor.ry-=2;
            baseActor.ly = 15;
        }  else if(x0 == 16){
            baseActor.rx +=2;
            baseActor.lx = 0;
        }  else if(y0 == 16){
            baseActor.ry+=2;
            baseActor.ly = 0;
        }
    }
}
