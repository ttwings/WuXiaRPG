package com.mygdx.game.manager;

import javafx.stage.Stage;

/**
 * Created by Administrator on 2016/12/7.
 * 基本的场景管理类。
 */
public class SceneManager {
    private SceneManager(){};
    private static SceneManager instance = new SceneManager();
    public static SceneManager getInstance(){
        return instance;
    }
    public Stage scene;
    public void init(){
        this.scene = new Stage();
    }
}
