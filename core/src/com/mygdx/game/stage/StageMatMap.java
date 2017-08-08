package com.mygdx.game.stage;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.manager.Constants;
import com.mygdx.game.manager.FontManager;
import com.mygdx.game.manager.ReadData;
import com.mygdx.game.tools.LazyBitmapFont;

/**
 * Created by ttwings on 2017/4/15.
 */
public class StageMatMap extends Stage{
    LazyBitmapFont font;
    String[][] matMap = new String[10][10];
    int x0,y0;

    public StageMatMap(String dataFile){
        font = FontManager.getInstance().fontL;
        matMap = ReadData.readStrMatSwapY(dataFile);
    }
    void drawMatMap(String[][] matMap){
        int w,h;
        w = matMap[0].length;
        h = matMap.length;
        String s = "";
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                if (!matMap[i][j].equals("x")){
                    if (i==y0 &&  j == x0){
                        s = "[RED]"+matMap[i][j];
                    }else {
                        s = matMap[i][j];
                    }
                    font.draw(getBatch(),x0+":"+y0,32,780);
                    font.draw(getBatch(),s,j*64+32,i*32);
                }
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Constants.KEY_U) {
            y0 += 1;
        }
        if (keycode == Constants.KEY_D) {
            y0 -= 1;
        }
        if (keycode == Constants.KEY_L) {
            x0 -= 1;
        }
        if (keycode == Constants.KEY_R) {
            x0 += 1;
        }
        if (keycode == Input.Keys.P) {
            StageManager.getInstance().currentStage=StageMap.getInstance();
        }
        if (keycode == Input.Keys.I) {
        }
        else if (keycode == Input.Keys.SPACE) {
        }
        return false;
    }
    private void handleDebugInput() {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;
        if (Gdx.input.isKeyPressed(Constants.KEY_L)) {
//            this.getCamera().position.x += -10;

        }
        if (Gdx.input.isKeyPressed(Constants.KEY_R)) {
//            this.getCamera().position.x += 10;

        }
        if (Gdx.input.isKeyPressed(Constants.KEY_U)) {
//            this.getCamera().position.y += 10;

        }
        if (Gdx.input.isKeyPressed(Constants.KEY_D)) {
//            this.getCamera().position.y += -10;

        }
        if (Gdx.input.isKeyPressed(Constants.KEY_START)) {
        }
    }

    @Override
    public void act() {
        super.act();
        handleDebugInput();
//        x0 = (int)getCamera().position.x;
//        y0 = (int)getCamera().position.y;
    }
    @Override
    public void draw() {
        super.draw();
        getBatch().begin();
        drawMatMap(matMap);
        getBatch().end();
    }
}
