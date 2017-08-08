package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class WorldMain implements ApplicationListener {
    private static final String TAG = "WorldMain";
    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private boolean paused;
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        worldController = WorldController.instance();
        worldRenderer = new WorldRenderer(worldController);
    }
    @Override
    public void render() {
        if (!paused) {
            worldController.update();
        }
//        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,0xff / 255.0f);
        Gdx.gl.glClearColor(0f, 0f, 0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
    }
    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }
    @Override
    public void pause() {
    	this.paused = true;
    }
    @Override
    public void resume() {
    	this.paused = false;
    }
    @Override
    public void dispose() {
    	worldRenderer.dispose();
    }
}