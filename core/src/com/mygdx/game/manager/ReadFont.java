package com.mygdx.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

/**
 * Created by ttwings on 2017/4/12.
 */
public class ReadFont {
    AssetManager manager;
    SpriteBatch batch;
    public BitmapFont font;
    public void creat(){
        manager = new AssetManager();

        // set the loaders for the generator and the fonts themselves
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // load to fonts via the generator (implicitely done by the FreetypeFontLoader).
        // Note: you MUST specify a FreetypeFontGenerator defining the ttf font file name and the size
        // of the font to be generated. The names of the fonts are arbitrary and are not pointing
        // to a file on disk!
        FreetypeFontLoader.FreeTypeFontLoaderParameter size1Params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "font/myfont.ttf";
        size1Params.fontParameters.size = 20;
        size1Params.fontParameters.characters = ReadData.readArrayFromFile("font/gb2312.txt").get(0);
        manager.load("size20.ttf", BitmapFont.class, size1Params);
    }
    void initFont(){
        if (manager.update() && manager.isLoaded("size10.ttf")) {
//            batch.begin();
            manager.get("size10.ttf", BitmapFont.class).draw(batch, "First font!", 20, 20);
            manager.get("size20.ttf", BitmapFont.class).draw(batch, "Second font!", 20, 50);
//            batch.end();
            font = manager.get("size20.ttf", BitmapFont.class);
            font.getData().markupEnabled = true;
        }
    }

    public BitmapFont getFont(){
//        manager.load("size10.ttf",BitmapFont.class);
//        BitmapFont font;
        manager.finishLoading();
        font = manager.get("size20.ttf", BitmapFont.class);
        font.getData().markupEnabled = true;
        return font;
    }


    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (manager.update() && manager.isLoaded("size10.ttf")) {
            batch.begin();
            manager.get("size10.ttf", BitmapFont.class).draw(batch, "First font!第一个字", 20, 20);
            manager.get("size20.ttf", BitmapFont.class).draw(batch, "Second font!", 20, 50);
            batch.end();
        }

        if (Gdx.input.justTouched() && manager.isLoaded("size10.ttf")) {
            // unload all the things and check if they really get disposed properly
            manager.unload("size10.ttf");
            manager.finishLoading();
            if (manager.isLoaded("size10.ttf")) throw new RuntimeException("broken");
            if (!manager.isLoaded("size20.ttf")) throw new RuntimeException("broken");

            manager.unload("size20.ttf");
            manager.finishLoading();
            if (manager.isLoaded("size10.ttf")) throw new RuntimeException("broken");
            if (manager.isLoaded("size20.ttf")) throw new RuntimeException("broken");
        }
    }

    public void dispose(){
        manager.dispose();
//        batch.dispose();
    }

}
