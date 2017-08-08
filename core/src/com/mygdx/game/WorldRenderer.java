package com.mygdx.game;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.manager.Constants;
import com.mygdx.game.stage.StageManager;
public class WorldRenderer implements Disposable {
    StageManager stageManager;
    WorldController worldController;
    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    /**
     * 设置帧速
     * @param fps 每秒帧数
     */
    public void sleep(int fps) {
        // limit fps
        long diff, start = System.currentTimeMillis();
        if (fps > 0) {
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                }
            }
            start = System.currentTimeMillis();
        }
    }
    private void init() {
        stageManager = StageManager.getInstance();
    }
    public void render() {
        sleep(Constants.FPS);
        stageManager.currentStage.draw();
    }
    public void resize(int width, int height) {
    }
    @Override
    public void dispose() {
        stageManager.currentStage.dispose();
    }
}