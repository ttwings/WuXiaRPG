package com.mygdx.game.stage;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.manager.Constants;
import com.mygdx.game.manager.FontManager;
import com.mygdx.game.manager.ReadData;
import com.mygdx.game.stage.StageManager;
import com.mygdx.game.stage.StageMap;
import com.mygdx.game.tools.LazyBitmapFont;
import groovy.lang.*;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.File;
import java.util.Date;

/**
 * Created by ttwings on 2017/8/7.
 */
public class StageItem extends Stage {
    BaseActor baseActor;
    float w, h;
    int x0, y0;
    int indexX = 1, indexY = 0;
    String[][] stageMat;
    LazyBitmapFont font;

    public StageItem(BaseActor actor) {
        this.baseActor = actor;
        init();
    }

    void init() {
        w = Constants.VIEWPORT_WIDTH;
        h = Constants.VIEWPORT_HEIGHT;
        x0 = 100;
        y0 = 100;
        baseActor = ReadData.actorMap("Data/Actors.txt").get("万震山");
        stageMat = ReadData.readStrMatSwapY("Data/itemStage.txt");
        changeMat(baseActor);
        font = FontManager.getInstance().fontL;
    }

    void changeMat(BaseActor actor) {
        stageMat[33][1] = actor.equips[0];
        stageMat[32][1] = actor.equips[1];
        stageMat[31][1] = actor.equips[2];
        stageMat[30][1] = actor.equips[3];
        stageMat[29][1] = actor.equips[4];

        stageMat[23][1] = actor.items[0];
        stageMat[22][1] = actor.items[1];
        stageMat[21][1] = actor.items[2];
        stageMat[20][1] = actor.items[3];
        stageMat[19][1] = actor.items[4];
        stageMat[18][1] = actor.items[5];
        stageMat[17][1] = actor.items[6];
        stageMat[16][1] = actor.items[7];
        stageMat[15][1] = actor.items[8];
        stageMat[14][1] = actor.items[9];

        stageMat[9][2] = actor.money + "";

    }

    @Override
    public boolean keyUp(int keycode) {
        // Reset game world
        if (keycode == Input.Keys.E) {
//            Gdx.app.log("StageMain", "按键E测试");
            select(0, 1);
        }
        if (keycode == Input.Keys.D) {
//            Gdx.app.log("StageMain", "按键D测试");
            select(0, -1);
        }
        if (keycode == Input.Keys.S) {
//            Gdx.app.log("StageMain", "按键S测试");
//            changeIndex(1);
        }
        if (keycode == Input.Keys.F) {
//            Gdx.app.log("StageMain", "按键F测试");
//            changeIndex(1);
        }
        if (keycode == Constants.KEY_A) {
            try {
//                evalScripText(baseActor);
                parse(baseActor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (keycode == Input.Keys.H) {
//            StageManager.getInstance().currentStage = StageMap.getInstance();
        }
        if (keycode == Input.Keys.P) {
            StageManager.getInstance().currentStage = StageMap.getInstance();
        }
        // Select next sprite
        else if (keycode == Input.Keys.SPACE) {
        }
        return false;
    }

    void select(int x, int y) {
        indexX = indexX + x;
        indexY = indexY + y;
    }

    void renderStageMat() {
        int w, h;
        w = stageMat[0].length;
        h = stageMat.length;
        String s;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!stageMat[i][j].equals(".")) {
                    if (j == indexX && i == indexY) {
                        font.draw(getBatch(), "[RED]" + stageMat[i][j] + "[]", j * 100 + 32, i * 20);
                    } else {
                        font.draw(getBatch(), stageMat[i][j], j * 100 + 32, i * 20);
                    }
                }
            }
        }
    }

    @Override
    public void draw() {
        getBatch().begin();
        renderStageMat();
        getBatch().end();
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
//        fontManager.dispose();
        font.dispose();
    }
//  test groovy script
    public static void parse(BaseActor actor) throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        File sorceFile = new File("../src/com/mygdx/game/tools/TestGroovy.groovy");
        Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sorceFile));
        GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
        instance.setProperty("actor",actor);
        instance.invokeMethod("printName", actor.name);
        instance.invokeMethod("printActorName",actor);

//        System.out.print(name);
//        Long time = (long)instance.invokeMethod("getTime",new Date());
//        System.out.println(time);
//        Date date = (Date)instance.invokeMethod("getDate",time);
//        System.out.println(date);
//        instance = null;
//        testGroovyClass = null;

    }

    public static void evalScripText(BaseActor actor) throws Exception {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        binding.setVariable("name", actor.name);
        shell.evaluate("println 'hello world! i am '+ name");
    }
}
