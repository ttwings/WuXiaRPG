package com.mygdx.game.action;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actor.BaseActor;

/**
 * Created by ttwings on 2017/8/29.
 */
public enum ToolActions implements State<BaseActor>{

    砍柴{
        @Override
        public void enter(BaseActor entity) {
            if (entity.handObj.equals("柴刀")){
                if (entity.objName.equals("原木")){
                    update(entity);
                }else{
                    entity.put("思考","要拿柴刀砍空气么");
                    System.out.println("砍空气么");
                }

            }else {
                entity.put("思考","没有柴刀怎么劈柴");
                System.out.println("没柴刀怎么砍柴");
            }
        }

        @Override
        public void update(BaseActor entity) {
            int d20 = MathUtils.random(20);
            int maxNum,curNum,num;
            if(d20>10){
                entity.put("描述","原木刷刷的被你砍成柴薪了");
                System.out.println("原木刷刷的被你砍成柴薪了");
            }
            if (d20==1){
                entity.put("描述","笨拙的你，不小心砍到了自己的手指");
                entity.put("流血","10");
            }
            if (d20==20){
                int expMax,exp,expEx;
                expMax = 100;
                exp = Integer.parseInt(entity.get("基本刀法"));
                expEx = exp + 1;
                if (exp<expMax)
                entity.put("描述","你从砍柴中，领悟到一点刀法经验");
                entity.put("基本刀法",expEx);
            }
        }

        @Override
        public void exit(BaseActor entity) {

        }

        @Override
        public boolean onMessage(BaseActor entity, Telegram telegram) {
            return false;
        }
    }
}
