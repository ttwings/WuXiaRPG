package com.mygdx.game.stage;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.action.Attack;
import com.mygdx.game.action.SkillActions;
import com.mygdx.game.action.ToolActions;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.action.ActionManager;
import com.mygdx.game.actor.TableData;
import com.mygdx.game.gameClass.GameCalendar;
import com.mygdx.game.gameClass.Tile;
import com.mygdx.game.manager.*;
import com.mygdx.game.map.MapLocal;
import com.mygdx.game.map.Room;
import com.mygdx.game.tools.*;

import java.util.*;

/**
 * Created by Administrator on 2017/1/11.
 * 1、与MapManager配合使用，从MapManager中读取地图数据，通过MapManager保存数据。
 * 2、从MapManager中读取tile信息。对照tile表，绘制地图，显示到桌面
 * 3、游戏运行中，对地图信息的变更，及时的显示到桌面。
 * 4、监听键盘，控制人物行动，同时将人物形象，显示到窗口
 */
public class StageMap extends Stage {
    private String TAG = Gdx.class.getSimpleName();
    private StageMap() {
        init();
    }
    private static StageMap instance = new StageMap();
    public static StageMap getInstance() {
        return instance;
    }
    Stage UIStage = new Stage();
    Stage itemStage = new Stage();
    int x;
    int y;
    String me = "段誉";
    String[][] regionMap = new String[64][64];
    String[][] mapWin = new String[5][5];
    FontManager fontManager = FontManager.getInstance();
    ActionManager actionManager = ActionManager.getInstance();
    AnimationManager animationManager = AnimationManager.getInstance();
    BaseActor baseActor;
    BaseActor npc1;
    public Attack attack1;
//      战斗动画坐标
    public int ax = 15, ay = 15;
//    菜单窗口文本
    String[][] menuWin = new String[20][20];
    String[][] lookWin = new String[3][15];
    String[][] timeWin = new String[3][12];
    String[][] faceWin = new String[8][40];
    String[][] battleWin = new String[9][19];
    String[][] messageWin = new String[25][9];
    String[][] skillWin = new String[4][40];
    String[][] buffWin = new String[2][20];
//    动作窗口，场景内可以执行的动作。
    String roomActionMsg = "";
//    信息列表
    ArrayList<String> battleMsg = new ArrayList<>();
    ArrayList<String> nomalMsg = new ArrayList<>();
//    日历
    GameCalendar calendar;
//    本地地图
    MapLocal mapLocal;
    public Animation animation;
    public String attackMessage = "";
    public Map<String, BaseActor> actors = new HashMap<>();
    String roomMsg = "";
    String roomName = "";
    int fps;
    float dur = 0;
    TiledMap tiledMap;
    Map<String, TiledMap> tiledMapName = new HashMap<>();
    TmxMapLoader tmxMapLoader = new TmxMapLoader();
    TextureRegion[][] frames;
    Label label;
    LazyBitmapFont font,font24,dmgFont;
//    测试新的类，用map来包装属性，可以让属性自由扩展。
    Map<String,TableData> peoples;
    Map<String,TableData> foods;
    Map<String, TableData> rooms;
    Map<String, TableData> objs;
//测试tiledmaprender
    OrthogonalTiledMapRenderer renderer;
//最近的物品名称及描述
    String objMsg = "";
//    飘动的信息
    String flyMsg = "擦伤 34";
    void init() {
        loadData();
        UIStage.setViewport(getViewport());
        mapLocal = new MapLocal();
        tiledMapName.put("襄阳.武馆", tmxMapLoader.load("tileMaps/wuguan.tmx"));
        mapLocal.setName("襄阳.武馆");
        mapLocal.setTiledMap(tiledMapName.get("襄阳.武馆"));
        tiledMap = mapLocal.getTiledMap();
        baseActor = actors.get(me);
        baseActor.lx = 20;
        baseActor.ly = 220;
//        baseActor.setX(baseActor.lx*32);
//        baseActor.setY(baseActor.ly*32);
        npc1 = actors.get("段誉");
        baseActor.target = "段誉";
        npc1.target = me;
        npc1.actionStr = "闲逛";
        npc1.lx = 10;
        npc1.ly = 10;
        baseActor.actionStr = "移动";
        x = baseActor.lx;
        y = baseActor.ly;
        battleMsg = new ArrayList<>();
        calendar = new GameCalendar();

        updataMenuWin();
        font = fontManager.fontL;
        font24 = fontManager.fontL;
        dmgFont = fontManager.fontL;
        roomName = regionMap[baseActor.ry][baseActor.rx];
        showRoomMsg(roomName);
        addMsg(roomMsg);
//        战斗动画测试
        frames = TextureRegion.split(Cache.instance().character(baseActor.get("行走图")), 32, 48);
        renderer = new OrthogonalTiledMapRenderer(tiledMap,getBatch());
    }
    void showRoomMsg(String roomName){
        if (roomName.length()>1){
            roomMsg = rooms.get(roomName).get("描述");
            roomActionMsg = "[YELLOW]" + rooms.get(roomName).get("动作");
            if (rooms.get(roomName).get("动作").equals("砍柴")){
                baseActor.actionState= ToolActions.砍柴;
//                System.out.print("砍柴");
            }
        }else {
            roomMsg = "";
            roomActionMsg = "";
        }
    }
//角色数据载入
    Map<String,BaseActor> loadActors(Map<String,TableData> tableDataMap){
//        String key="";
//        BaseActor baseActor;
        Map<String,BaseActor> baseActorMap = new HashMap<>();
        for (String k:tableDataMap.keySet()){
            baseActorMap.put(k,new BaseActor(tableDataMap.get(k)));
        }
        return baseActorMap;
    }
    void loadData() {
//        角色信息数据，主要数据
//        actors = ReadData.actorMap("Data/Actors.txt");
        peoples = ReadData.tableDateMap("Data/Actors.txt");
        actors = loadActors(peoples);
        foods  = ReadData.tableDateMap("Data/Foods.txt");
        rooms  = ReadData.tableDateMap("Data/Rooms.txt");
        objs = ReadData.tableDateMap("Data/Objs.txt");
//        菜单设计矩阵，用于简单的设计菜单布局
        menuWin = ReadData.readStrMatSwapY("Data/mapStageWin.txt");
        regionMap = ReadData.readStrMatSwapY("Data/mapWuGuanMat.txt");
//       地图信息，后期的路径规划，也需要在这里实现。
        baseActor = actors.get(me);
    }
    void updataMenuWin() {
        timeWin[0][4] = calendar.printCalenar();
        timeWin[1][0] = String.valueOf(calendar.getTurn());
        timeWin[0][0] = baseActor.get("区域");


        faceWin[5][5] = baseActor.get("世家");
        faceWin[4][5] = baseActor.get("称号");
        faceWin[3][5] = baseActor.get("名称");
        faceWin[2][5] = "年龄："+baseActor.get("年龄");

        faceWin[5][10] = "气血";
        faceWin[4][10] = "真气";
        faceWin[3][10] = "精力";
        faceWin[5][12] = Show.disExp(Integer.parseInt(baseActor.get("气血")), baseActor.maxHP);
        faceWin[4][12] = Show.disExp(baseActor.MP, baseActor.maxMP);
        faceWin[3][12] = Show.disExp(baseActor.AP,baseActor.maxAP);
        faceWin[5][18] = "X:"+String.valueOf(baseActor.get("坐标x"));
        faceWin[4][18] = "Y:"+String.valueOf(baseActor.get("坐标y"));
        faceWin[3][18] = "LX:"+String.valueOf(baseActor.lx);
        faceWin[2][18] = "LY:"+String.valueOf(baseActor.ly);

        faceWin[0][0] = "[RED]内伤"+baseActor.get("内伤");
        faceWin[0][5] = "[GREEN]中毒"+baseActor.get("中毒");
        faceWin[0][10] = "[RED]流血"+baseActor.get("流血");
        faceWin[1][0] = "[YELLOW]灼烧"+baseActor.get("灼烧");
        faceWin[1][5] = "[BLUE]冰封"+baseActor.get("冰封");
        faceWin[1][10] = "[YELLOW]封穴"+baseActor.get("封穴");

        messageWin = addMessage(nomalMsg, messageWin);
        battleWin = addMessage(battleMsg, battleWin);
        strings = Show.str2array(roomMsg, 19);
        skillWin[1][0] = "A："+baseActor.get("技能1");
        skillWin[1][5] = "B："+baseActor.get("技能2");
        skillWin[1][10] = "X："+baseActor.get("技能3");
        skillWin[1][15] = "Y："+baseActor.get("技能4");
        skillWin[1][20] = "R1："+baseActor.get("技能5");
        skillWin[1][25] = "R2："+baseActor.get("技能6");
        skillWin[1][30] = "L1："+baseActor.get("技能7");
        skillWin[1][35] = "L2："+baseActor.get("技能8");

        skillWin[3][20] = objMsg;

    }

    String[] strings;


    void renderAnimation(Animation animation, int x, int y, float dur) {
        if (animation != null && !animation.isAnimationFinished(dur)) {
            getBatch().draw((TextureRegion) animation.getKeyFrame(dur), x, y);
            font.draw(getBatch(), "[YELLOW]"+flyMsg, baseActor.getX(), baseActor.getY() + 70 + dur * 32);
        }
    }
    void updataObjMsg(){
        if (objs.containsKey(baseActor.objName)){
            objMsg = baseActor.objName + objs.get(baseActor.objName).get("描述");
        }else {
            objMsg = baseActor.objName;
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Constants.KEY_A) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passA(mapLocal, baseActor);
            baseActor.put("坐标x",String.valueOf(Integer.parseInt(baseActor.get("坐标x"))+1));
        }
        if (keycode == Constants.KEY_B) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passB(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_X) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passX(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_Y) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passY(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_R1) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passR1(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_R2) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passR2(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_L1) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passL1(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_L2) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passL2(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_START) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passStart(mapLocal, baseActor);
            loadData();
        }
        if (keycode == Constants.KEY_BACK) {
            actionManager.actionKeyMap.get(baseActor.get("行动状态")).passBack(mapLocal, baseActor);
        }

        if (keycode == Input.Keys.Q) {
            StageManager.getInstance().currentStage = new StageActor(baseActor);
        }
        if (keycode == Input.Keys.I) {
            StageManager.getInstance().currentStage = new StageItem(baseActor);
        }
        if (keycode == Input.Keys.M) {
        } else if (keycode == Input.Keys.SPACE) {
            StageManager.getInstance().currentStage = new StageFontMap("maps/wuguan.txt");
        } else if (keycode == Input.Keys.Z) {
            StageManager.getInstance().currentStage = new StageMatMap("Data/mapWuGuanMat.txt");
        }
        return false;
    }

    private void handleDebugInput() {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;
        if (Gdx.input.isKeyPressed(Constants.KEY_L)) {
            actionManager.passL(mapLocal, baseActor);
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_R)) {
            actionManager.passR(mapLocal, baseActor);
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_U)) {
            actionManager.passU(mapLocal, baseActor);
        }
        if (Gdx.input.isKeyPressed(Constants.KEY_D)) {
            actionManager.passD(mapLocal, baseActor);
        }
    }

    public String[][] addMessage(ArrayList<String> message, String[][] winStr) {
        int size = message.size();
        int maxH = winStr.length;
        if (size > 0) {
            if (size < maxH) {
                for (int i = 0; i < size; i++) {
                    winStr[i][0] = message.get(size - 1 - i);
                }
            } else {
                for (int i = 0; i < maxH; i++) {
                    winStr[i][0] = message.get(size - 1 - i);
                }
            }

        }
        return winStr;
    }

    public void addMsg(String s) {
        nomalMsg = Show.addMsg(nomalMsg, s, 19);
    }

    public void addBattleMsg(String s) {
        battleMsg = Show.addMsg(battleMsg, s, 49);
    }

    @Override
    public void act() {
        super.act();
        UIStage.act();
        calendar.opratorAdd(1);
        handleDebugInput();
        mapWin = Show.miniMap(regionMap, (int)baseActor.getX()/128, (int)baseActor.getY()/128, 5, 5);
        fps = Gdx.graphics.getFramesPerSecond();
        baseActor.updata();
        baseActor.act(Gdx.graphics.getDeltaTime());
        updataObjMsg();
        updataMenuWin();
        roomName = regionMap[(int)baseActor.getY()/128][(int)baseActor.getX()/128];
        showRoomMsg(roomName);
//        flyMsg = baseActor.get("思考");
//        showRoomMsg(regionMap[(int)baseActor.getY()/128][(int)baseActor.getX()/128]);
    }


    @Override
    public void draw() {
        super.draw();
        renderer.setView((OrthographicCamera) getCamera());
//        renderer.render();
        renderer.getBatch().begin();
        renderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("floor"));
        renderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("wall"));
        renderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("obj"));
//        renderer.renderObjects(tiledMap.getLayers().get("objs"));

        renderer.getBatch().end();
        baseActor.objName = Show.objName(tiledMap,"objs",baseActor);
//                getBatch().draw(animationManager.moveFrame(frames, baseActor.enumAction, baseActor.turn), baseActor.getX(), baseActor.getY());

        getBatch().begin();
//        绘制图块对象
        Show.objLayers(getBatch(),tiledMap,baseActor);
        baseActor.actorTextureComponent.draw(getBatch());
        getBatch().end();
        renderer.getBatch().begin();
        renderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("cover"));
        renderer.getBatch().end();
        getBatch().begin();
//        baseActor.textureRegion = animationManager.moveFrame(frames, baseActor.enumAction, baseActor.turn);
        dur = animationManager.dur;
        animationManager.updata();
//        图片素材都是从左下角开始绘制，单个帧动画192*192大小，人物32*32大小。俩个的中心偏移量即 （192-32）/2 即 80 的偏移量
//       同一时间，只有一个战斗动画。多个战斗动画，顺序播放
        getCamera().position.set(baseActor.getX(), baseActor.getY(), 0);
        renderAnimation(animation, (int) SkillActions.handPos(baseActor).x - 80, (int) SkillActions.handPos(baseActor).y - 80, animationManager.dur);
        getBatch().end();
//  UI 场景
        UIStage.getBatch().begin();
        Show.renderWin(UIStage.getBatch(), font, menuWin, 900, 10);
        Show.renderWin(UIStage.getBatch(), font, lookWin, 384, 736);
        Show.renderWin(UIStage.getBatch(), font, timeWin, 480, 780);
        Show.renderWin(UIStage.getBatch(), font, battleWin, 10, 80);
        Show.renderWin(UIStage.getBatch(), font, mapWin, 940, 704, 64, 20);
        Show.renderWin(UIStage.getBatch(), font, messageWin, 10, 280);
        Show.renderWin(UIStage.getBatch(), font, faceWin, 15, 680);
        Show.renderWin(UIStage.getBatch(), font, skillWin, 20, 20);
        Show.renderWin(UIStage.getBatch(), font, buffWin, 500, 760);
        Show.renderStrs(UIStage.getBatch(), font, strings, 10, 560, 20);
        Show.renderStr(UIStage.getBatch(),font,roomName,500,760);
        Show.renderStr(UIStage.getBatch(),font,roomActionMsg,500,740);
//        Show.renderStr(UIStage.getBatch(),font,Show.disHP(baseActor.busy,10),500,720);
        Show.renderStr(UIStage.getBatch(),font,baseActor.get("行动状态"),460,720);
        Show.renderStr(UIStage.getBatch(),font,baseActor.handObj+baseActor.indexItem,460,700);
        Show.renderCD(UIStage.getBatch(),font,"忙碌中",'■',baseActor.busy,500,720);
        Show.faceTexture(UIStage.getBatch(), baseActor.get("头像"), 18, 700);
//        Show.faceTexture(UIStage.getBatch(), baseActor.get("头像"), 100, 100);
        if (baseActor.get("行动状态").equals("战斗")){

            itemStage = new StageItem(baseActor);
            itemStage.draw();
            itemStage.act();
        }
        Show.showFps(UIStage.getBatch(), font, fps, 10, 760);
        UIStage.getBatch().end();
    }


    @Override
    public void dispose() {
        super.dispose();
        fontManager.dispose();
    }
}
