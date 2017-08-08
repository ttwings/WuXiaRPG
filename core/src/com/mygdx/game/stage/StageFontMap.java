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

import java.util.ArrayList;

/**
 * Created by ttwings on 2017/4/14.
 */
public class StageFontMap extends Stage{
    LazyBitmapFont font;
    ArrayList<String> mapArray = new ArrayList<>();
    public StageFontMap(String dataFile){
        font = FontManager.getInstance().fontL;
        mapArray = ReadData.readArrayFromFile(dataFile);

    }
    void drawFontMap(ArrayList<String> mapArray){
        for (int i = mapArray.size()-1;i>-1;i--){
            font.draw(getBatch(),mapArray.get(i),20,600-20*i);
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Constants.KEY_A) {
        }
        if (keycode == Constants.KEY_B) {
        }
        if (keycode == Constants.KEY_R2) {
        }
        if (keycode == Constants.KEY_R1) {
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
            this.getCamera().position.x += -10;
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_R)) {
            this.getCamera().position.x += 10;
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_U)) {
            this.getCamera().position.y += 10;
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_D)) {
            this.getCamera().position.y += -10;
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_START)) {
        }
    }

    @Override
    public void act() {
        super.act();
        handleDebugInput();
    }
    @Override
    public void draw() {
        super.draw();
        getBatch().begin();
        drawFontMap(mapArray);
        getBatch().end();
    }
}
