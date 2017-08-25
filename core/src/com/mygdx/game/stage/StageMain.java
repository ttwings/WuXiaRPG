package com.mygdx.game.stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.manager.*;
import com.mygdx.game.tools.LazyBitmapFont;

/**
 * Created by apple on 16/12/13.
 * 主窗口，开始游戏、继续游戏、设置、推出游戏等。
 */
public class StageMain extends Stage {
    public String[] mainMenu;
    public String title,tempStr;
    StageManager stageManager = StageManager.getInstance();
    public int w, h;
    public int index;
    float stateTime = 0.0f;
    public StageMain() {
    init();
    }
    LazyBitmapFont font,mainFont;
    FontManager fontManager = FontManager.getInstance();
    Texture background = null;
    private void init(){
        w = Constants.VIEWPORT_WIDTH;
        h = Constants.VIEWPORT_HEIGHT;
        index = 0;
        new Stage(new StretchViewport(w, h));
        mainMenu = new String[]{"初入江湖", "梦回武林", "秘籍宝典", "归隐山林"};
        title = "武侠与江湖";
        tempStr = "";
        for (int i=0;i<4;i++){
            tempStr += mainMenu[i];
        }
        font = fontManager.fontL;
        mainFont = fontManager.fontL64;
        background = Cache.instance().backgound("Background00023.jpg");
    }
    @Override
    public void draw() {
        stateTime = stateTime + 0.16f;
        getBatch().begin();
        getBatch().draw(background,0,0,w,h);
        mainFont.draw(getBatch(),"[BLACK]武侠与江湖",w/2-150,h/2+200);
        drawIndex();
        getBatch().end();
        super.draw();
    }
    void drawIndex(){
        for (int i=0;i<4;i++){
            if (i==index){
                font.draw(getBatch(),"[RED]"+mainMenu[i],w / 2 - 64, h - i * 32 - 300);
            }else {
                font.draw(getBatch(),"[BLACK]"+mainMenu[i],w / 2 - 64, h - i * 32 - 300);
            }
        }
    }
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.E) {
            changeIndex(-1);
        }
        if (keycode == Input.Keys.D) {
            changeIndex(1);
        }
        if (keycode == Input.Keys.S) {
        }
        if (keycode == Input.Keys.F) {
        }
        if (keycode == Input.Keys.J) {
            stageManager.changeStage(StageMap.getInstance());
            Gdx.app.log("StageMain", "按键J测试");
        }
        if (keycode == Input.Keys.K) {
            Gdx.app.log("StageMain", "按键k测试");
        }
        else if (keycode == Input.Keys.SPACE) {
        }
        return false;
    }
    void changeIndex(int x) {
        index = index + x;
        if ((index + x) > mainMenu.length - 1) {
            index = mainMenu.length - 1;
        }
        if (index + x < 0) {
            index = 0;
        }
    }
    @Override
    public void clear() {
        super.clear();
    }
    @Override
    public void dispose(){
        fontManager.dispose();
    }
}
