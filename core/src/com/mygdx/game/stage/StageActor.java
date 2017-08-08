package com.mygdx.game.stage;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.manager.*;
import com.mygdx.game.tools.LazyBitmapFont;

/**
 * Created by apple on 16/12/25.
 * 人物信息窗口，显示人物信息。
 */
public class StageActor extends Stage{
    BaseActor baseActor;
    float w,h;
    int x0,y0;
    String[][] stageMat;
    LazyBitmapFont font;
    public StageActor(String name){
        baseActor = ReadData.actorMap("Data/Actors.txt").get(name);
        init();
        changeMat(baseActor);
    }
    void init(){
        w = Constants.VIEWPORT_WIDTH;
        h = Constants.VIEWPORT_HEIGHT;
        x0 = 100;
        y0 = 100;
        stageMat = ReadData.readStrMatSwapY("Data/actorStage.txt");
        font = FontManager.getInstance().fontL;
    }
    void changeMat(BaseActor actor){
        stageMat[37][0] = actor.nickname;
        stageMat[37][1] = actor.name;
        stageMat[37][2] = actor.country;
        stageMat[37][3] = actor.sex;
        stageMat[37][4] = actor.LV+"";
//        stageMat[37][5] = actor.nickname;

        stageMat[33][0] = actor.HP+"";
        stageMat[33][1] = actor.DHP+"";
        stageMat[31][0] = actor.MP+"";
        stageMat[29][0] = actor.ap+"";
        stageMat[26][1] = actor.Str+"";
        stageMat[25][1] = actor.Con+"";
        stageMat[24][1] = actor.Dex+"";
        stageMat[23][1] = actor.Wis+"";
        stageMat[22][1] = actor.Cha+"";

//        stageMat[20][0] = "称号";
//        stageMat[19][0] = actor.nickname;
//        stageMat[0][0] = actor.name;
//        stageMat[18][1] = actor.name;
//        stageMat[][]
    }
    @Override
    public boolean keyUp(int keycode) {
        // Reset game world
        if (keycode == Input.Keys.E) {
//            Gdx.app.log("StageMain", "按键E测试");
        }
        if (keycode == Input.Keys.D) {
//            Gdx.app.log("StageMain", "按键D测试");
        }
        if (keycode == Input.Keys.S) {
//            Gdx.app.log("StageMain", "按键S测试");
//            changeIndex(1);
        }
        if (keycode == Input.Keys.F) {
//            Gdx.app.log("StageMain", "按键F测试");
//            changeIndex(1);
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
    void renderStageMat(){
        int w,h;
        w = stageMat[0].length;
        h = stageMat.length;
        String s;
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                if (!stageMat[i][j].equals(".")){
                    font.draw(getBatch(),stageMat[i][j],j*100+32+200,i*20);
                }
            }
        }
    }
    @Override
    public void draw(){
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
}
