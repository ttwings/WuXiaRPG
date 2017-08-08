package com.mygdx.game.tools;

/**
 * Created by ttwings on 2017/7/22.
 */
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.manager.Cache;

/**
 * 黄色进度条公共接口
 *
 * @author whs
 * @date 2015-8-28 下午3:22:55
 * @version V1.0
 * @Description:
 */
public class ProgressActor extends Actor {

    // 相对于背景的x轴偏移
    private static final float PROGRESS_XOFFSET = 7.0F;
    // 控制进度条渐变
    public SliderBar sliderBar;
    // 绿色进度条
    private NinePatch progress;
    private TextureRegion bgRegion;
    // 飞镖形状的那头，与背景分开，方便覆盖进度尽头
//    private TextureRegion topRegion;
    // 变化块
    private TextureRegion progressRegion;

    // 指定进度条高度（指的是变化的那块，非背景高度）
    private float progressHeigth = 20F;
    // 长度极限值
    private float maxProgressWidth;
    private float x;
    private float y;
    // 整体不进行缩放
    private float scaleX = 1F;
    private float scaleY = 1F;
    // 测试百分比变量，用于改变进度条百分比
    private float testP;
    private float width;
    private float height;
    // 原有的进度百分比
    private float expectedProgress;
    private float progressWidth;

    public ProgressActor() {
//        topRegion = Assets.testAtlas.findRegion("top");
//        topRegion = new TextureRegion(Cache.instance().system("top.png"));
//        bgRegion = Assets.testAtlas.findRegion("bg");
        bgRegion = new TextureRegion(Cache.instance().system("bar.png"));
        progressRegion = new TextureRegion(Cache.instance().system("knob.png"));
//        progressRegion = Assets.testAtlas.findRegion("progress");
        // 在这里，我是用九宫格拉伸处理的进度条
        progress = new NinePatch(progressRegion, 6, 6, 0, 0);
        width = bgRegion.getRegionWidth();
        height = bgRegion.getRegionHeight();
        setSize(width, height);
//        setOrigin();
        setOrigin(Align.center);
        maxProgressWidth = width - PROGRESS_XOFFSET * 2;
        sliderBar = new SliderBar();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        scaleX = getScaleX();
        scaleY = getScaleY();
        // 针对缩放的情况进行处理
        x = getX() + (1 - scaleX) * width;
        y = getY() + (1 - scaleY) * height;
        maxProgressWidth = scaleX * (width - PROGRESS_XOFFSET * 2);
        drawProgress(batch, parentAlpha, x, y, scaleX, scaleY);

    }

    // 在该处演示百分比的变化
    public float getProgress() {
        float f = (int) testP / 3F;
        if (f > 1) {
            testP = 0;
        }
        return f;

    }

    /**
     * 绘制进度
     *
     * @author whs
     * @date 2015-8-28 下午3:27:28
     * @param @param batch
     * @param @param parentAlpha
     * @param @param x
     * @param @param y
     * @param @param scaleX
     * @param @param scaleY
     * @return void
     * @Description: TODO
     */
    public void drawProgress(Batch batch, float parentAlpha, float x, float y, float scaleX, float scaleY) {

        // 以单纯一张图的形式绘制背景
        batch.draw(bgRegion, x, y, width * scaleX, height * scaleY);
        // 获得当前进度条宽度
        progressWidth = maxProgressWidth * limit(sliderBar.percent);

        /**
         * 使用九宫格进行进度绘制，同时，对为0的情况进行处理，并适应整体的缩放
         */
        progress.draw(batch, x + PROGRESS_XOFFSET * scaleX, y + (height * scaleY - progressHeigth * scaleY) / 2,
                progressWidth < 12 ? 12 : progressWidth, progressHeigth * scaleY);
        // 绘制尽头飞镖图案，盖住进度尽头
//        batch.draw(topRegion, x + bgRegion.getRegionWidth() - 35,
//                y - (topRegion.getRegionHeight() - bgRegion.getRegionHeight()) / 2);

    }

    /**
     * 进度范围限制
     *
     * @author whs
     * @date 2015-8-10 下午5:43:04
     * @param @param percent
     * @param @return
     * @return float
     * @Description: TODO
     */
    protected static float limit(float percent) {
        if (percent > 1.0F) {
            percent = 1.0F;
        } else if (percent < 0.0F) {
            return 0.0F;
        }
        return percent;
    }

    /**
     * 在外部使用的时候重置进度条
     * @author whs
     * @date 2015-11-24 下午2:41:06
     * @Description:
     */
    public void start() {
        expectedProgress = getProgress();
        sliderBar.reset();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        testP += delta;
        float progress = getProgress();
        /**
         * 更新进度
         */
        if (expectedProgress != progress) {
            sliderBar.setup(expectedProgress, progress);
            expectedProgress = progress;
        }
        sliderBar.update(delta);
    }
}
