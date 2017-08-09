package com.mygdx.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.gameClass.Tile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ttwings on 16/11/19.
 */
// 主要用于纹理的载入
public class Cache {
    private String TAG = Gdx.app.getClass().getName();
    private Cache(){init();}
    private static Cache instance = new Cache();
    public static Cache instance(){
        return  instance;
    }
    public Map<String,Texture> textureMap = new HashMap<String, Texture>();
//    public Map<String,TextureRegion> tileSet = new HashMap<>();
    public Map<String,Tile> tileMap = new HashMap<>();
    public AssetManager assetManager = new AssetManager();
    public Texture animation(String filename){
        return loadTexture("Graphics/Animations/",filename);
    }
    public Texture character(String filename){
        return loadTexture("Graphics/Characters/",filename);
    }
    public Texture backgound(String filename){
        return loadTexture("Graphics/Backgrounds/",filename);
    }
    public Texture skillAction(String filename){
        return loadTexture("Graphics/Animations/SkillsAction/",filename);
    }
    public Texture skillMod(String filename){
        return loadTexture("Graphics/Animations/Skills/",filename);
    }
    public Texture actorStand(String filename){
        return loadTexture("Graphics/Actors/",filename);
    }
    public Texture miniRole(String filename){
        return loadTexture("Graphics/MiniRole/",filename);
    }
    public Texture battleMap(String filename){
        return loadTexture("Graphics/BattleMaps/",filename);
    }
    public Texture battleBack2(String filename){
        return loadTexture("Graphics/Battlebacks2/",filename);
    }
    public Texture face(String filename){
        return loadTexture("Graphics/Faces/", filename);
    }
    public Texture battler(String filename){
        return loadTexture("Graphics/Battlers/", filename);
    }
    public Texture system(String filename){
        return loadTexture("Graphics/System/", filename);
    }
    public Texture menu(String filename){
        return loadTexture("Graphics/Menu/", filename);
    }
    public Texture actorMenu(String filename){
        return loadTexture("Graphics/ActorMenu/", filename);
    }
    public Texture title1(String filename){
        return loadTexture("Graphics/Title1/", filename);
    }
    public Texture title2(String filename){
        return loadTexture("Graphics/Title2/", filename);
    }
    public Texture tileset(String filename){
        return loadTexture("Graphics/Tilesets/",filename);
    }
    public Texture stage(String filename){
        return loadTexture("Graphics/Stage/",filename);
    }

    public Texture normal(String filename){
        return loadTexture("",filename);
    }

    public void empty(){
        this.textureMap.clear();
    }
   void init(){
//        initTileMap();
   }
//
    public void initTileMap(){
        tileMap = ReadData.tileMap("Data/Tiles.txt");
    }


    TextureRegion getTileFromData(String s){
        String tileSetStr,tileStr,sizeStr,posStr,offStr;
        String[] dataStr = ReadData.tile(s);
        int x,y,offX,offY,w,h;
        TextureRegion tempRegion,tile;
        Texture tileSet;
//      0	1	2	3	4	5	6	7	8
//      ID	图块名	图集名	宽	高	X坐标	Y坐标	X偏移	Y偏移
        tileSetStr = dataStr[2];
        w = Integer.parseInt(dataStr[3]);
        h = Integer.parseInt(dataStr[4]);
        x = Integer.parseInt(dataStr[5]);
        y = Integer.parseInt(dataStr[6]);
        offX = Integer.parseInt(dataStr[7]);
        offY = Integer.parseInt(dataStr[8]);
        tileSet = tileset(tileSetStr);
        tile = new TextureRegion(tileSet,x,y,w,h);
        return tile;
    }
    /*
     * 从文件载入纹理
     * @param filename1
     * @param filename2
     * @return
     */
    public Texture loadTexture(String filename1,String filename2){
        String s;
        Texture t;
        s = filename1+filename2;
//        if (s=="" || s == null){
//            Gdx.app.debug(TAG,"载入图像错误");
//        }
//        if (textureMap.keySet().contains(s)){
//            t = textureMap.get(s);
//
//        }else{
//            t = new Texture(s);
//            textureMap.put(s,t);
//        }
        assetManager.load(s,Texture.class);
        assetManager.finishLoading();
        t = assetManager.get(s);
        return t;
    }
}
