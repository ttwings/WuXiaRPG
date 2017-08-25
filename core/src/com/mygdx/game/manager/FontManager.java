package com.mygdx.game.manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.tools.LazyBitmapFont;
/**
 * Created by Administrator on 2017/3/15.
 */
public class FontManager {
    private FontManager() {
        init();
    }
    private static FontManager instance = new FontManager();
    public static FontManager getInstance() {
        return instance;
    }
    public AssetManager manager;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/msyh.ttf"));
    public LazyBitmapFont fontL;
    public LazyBitmapFont fontL24;
    public LazyBitmapFont fontL28;
    public LazyBitmapFont fontL64;

    void init() {
        fontL  = new LazyBitmapFont(generator,18);
        fontL24 = new LazyBitmapFont(generator,24);
        fontL28 = new LazyBitmapFont(generator,28);
        fontL64 = new LazyBitmapFont(generator,64);
        fontL.getData().markupEnabled = true;
        fontL24.getData().markupEnabled = true;
        fontL28.getData().markupEnabled = true;
        fontL64.getData().markupEnabled = true;
    }
    public void dispose() {
        fontL.dispose();
        fontL24.dispose();
    }
}
