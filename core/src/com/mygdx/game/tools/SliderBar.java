package com.mygdx.game.tools;

/**
 * Created by ttwings on 2017/7/22.
 */
import com.badlogic.gdx.math.Interpolation;

/**
 * 控制进度条变化，比如从10%到50%,不直接一蹴而就的从10%直接到50%，而是进行渐变
 * @Description:
 * @author whs
 * @date 2015-11-24 上午9:26:44
 * @version V1.0
 *
 */
public class SliderBar {

    // 变化时长
    private static final float duration = 0.6F;
    private float end;
    private float start;
    private float time;
    public float percent;

    public SliderBar() {
    }

    /**
     * 重置数据
     * @author whs
     * @date 2015-11-24 上午9:33:37
     * @Description:
     */
    public void reset() {
        percent = 0;
        end = 0;
        start = 0;
        time = 0.0F;
    }

    /**
     * 初始化设置
     * @author whs
     * @date 2015-11-24 上午9:35:45
     * @param start   变化的开始，为上一次百分比
     * @param end     变化的结束，为新的百分比
     * @Description:
     */
    public void setup(float start, float end) {
        this.start = start;
        this.percent = start;
        this.end = end;
        this.time = 0.0F;
    }

    public void update(float delta) {

        if (percent != end) {
            time = delta + time;
            if (time < duration) {
                // 采用正弦淡出变化,百分比根据变化系数进行进行变化
                float p = Interpolation.sineOut.apply(time / duration);
                percent = start + p * (end - start);
            } else {
                percent = end;
            }
        }
    }

}