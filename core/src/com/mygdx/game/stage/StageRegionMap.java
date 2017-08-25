package com.mygdx.game.stage;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.manager.Cache;
import com.mygdx.game.manager.Constants;
import com.mygdx.game.map.MapRegion;

/**
 * Created by Administrator on 2017/3/30.
 */
public class StageRegionMap extends Stage{
    public StageRegionMap(){
        init();
    }
    Cache cache = Cache.instance();
    MapRegion mapRegion = new MapRegion();
    TextureRegion regionTextureMat0[][] = new TextureRegion[10][10];
    TextureRegion regionTextureMat1[][] = new TextureRegion[10][10];
//    BitmapFont font;
    int x0,y0;
    int w,h;
    public StageRegionMap(String mapFile){
        cache.initTileMap();
        mapRegion.readRegionMap(mapFile);
//        mapRegion = MapManager.getInstance().mapRegion;
//        font = FontManager.getInstance().getFont();
//        Gdx.app.debug("",mapRegion.floorMat[0][0]);
//        regionTextureMat = initTileMap(x0-13,y0-13,x0+13,y0+20);
        w = mapRegion.w;
        h = mapRegion.h;
        regionTextureMat0 = initTileMap(0,0,w-1,h-1,0);
        regionTextureMat1 = initTileMap(0,0,w-1,h-1,1);
    }
    void init(){
        cache.initTileMap();
//        mapRegion = MapManager.getInstance().mapRegion;
//        font = FontManager.getInstance().getFont();
//        Gdx.app.debug("",mapRegion.floorMat[0][0]);
//        regionTextureMat = initTileMap(x0-13,y0-13,x0+13,y0+20);
        regionTextureMat0 = initTileMap(0,0,95,95,0);
        regionTextureMat1 = initTileMap(0,0,95,95,1);
    }
    TextureRegion[][] initTileMap(int x0,int y0,int x1,int y1,int index){
        String tileStr;
        int w,h;
        w = x1-x0+1;
        h = y1-y0+1;
        TextureRegion[][] map;
        map = new TextureRegion[h][w];
        for(int i=0;i<h;++i){
            for(int j=0;j<w;++j){
//                tileStr = mapLocal.datas[y0+i][x0+j].split(",")[index];
                tileStr = mapRegion.floorMat[y0+i][x0+j].split(",")[index];
                if (tileStr.equals("")||tileStr==null||tileStr.equals("空气")){
                    map[i][j] = null;
                }else {
                    map[i][j] = cache.tileMap.get(tileStr).textureRegion;
                }
            }
        }
        return map;
    }
    void renderTileMap(TextureRegion[][] tileMap){
        int w,h,length;
        length = 0;
        w = tileMap[0].length;
        h = tileMap.length;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;++j){
                if (tileMap[i][j]!= null){
                    getBatch().draw(tileMap[i][j],j*32,i*32);
                }
            }
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
        renderTileMap(regionTextureMat0);
        renderTileMap(regionTextureMat1);
        getBatch().end();
    }
}
