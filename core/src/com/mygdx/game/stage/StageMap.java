package com.mygdx.game.stage;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.action.Attack;
import com.mygdx.game.action.SkillActions;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.action.ActionManager;
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
    int w = 16;
    int h = 16;
    int x;
    int y;

    String me = "虚竹";
    Json json = new Json();
    String[][] regionMap = new String[10][10];
    String[][] mapWin = new String[5][5];
    TextureRegion[][] actorMap = new TextureRegion[h][w];
    TextureRegion[][] animMap = new TextureRegion[h][w];
    FontManager fontManager = FontManager.getInstance();
    //    BitmapFont font;
    BitmapFont font24;
    ActionManager actionManager = ActionManager.getInstance();
    AnimationManager animationManager = AnimationManager.getInstance();
    BaseActor baseActor;
    BaseActor npc1;
    public Attack attack1, attack2;
    public int ax = 15, ay = 15;
    public String[][] menuWin = new String[20][20];
    public String[][] lookWin = new String[3][15];
    public String[][] timeWin = new String[3][12];
    public String[][] faceWin = new String[8][40];
    public String[][] battleWin = new String[9][19];
    public String[][] messageWin = new String[25][9];
    public String[][] skillWin = new String[4][40];
    public String[][] buffWin = new String[2][20];
    public ArrayList<String> battleMsg = new ArrayList<>();
    public ArrayList<String> nomalMsg = new ArrayList<>();
    GameCalendar calendar;
    MapLocal mapLocal;
    public Animation animation;
    public String attackMessage = "";
    public Map<String, BaseActor> actors = new HashMap<>();
    public Map<String, Tile> tileMap = new HashMap<>();
    Map<String, Room> roomMap = new HashMap<>();
    String roomMsg = "";
    int fps;
    float dur = 0;
    TiledMap tiledMap, tiledMap1, tiledMap2;
    Map<String, TiledMap> tiledMapName = new HashMap<>();
    TmxMapLoader tmxMapLoader = new TmxMapLoader();
    TextureRegion[][] frames;
    //    进度条
    Texture progessBarTexture;
    Texture knobTexture;
    ProgressBar progressBar;
    float count = 0;
    //    圆形cd
    CircularProgress circularProgress;
    Texture circularCoverTexture;
    //    另一种风格CD
    ProgressActor progressActor;
    //    另一种圆形
    ProgressTimer progress;
    //    伤害数字显示
    BitmapFont dmgFont;
    Label label;
    Label.LabelStyle labelStyle;

    LazyBitmapFont font;

    void init() {
        loadData();
        UIStage.setViewport(getViewport());
        tiledMapName.put("武馆大厅", tmxMapLoader.load("tileMaps/wuguan-keting.tmx"));
//        mapLocal = new MapLocal(128,128);
        baseActor = actors.get(me);
        npc1 = actors.get("段誉");
        baseActor.target = "段誉";
        npc1.target = me;
        npc1.actionStr = "闲逛";
        npc1.lx = 10;
        npc1.ly = 10;
        baseActor.actionStr = "移动";
        actionManager.actionName = baseActor.actionStr;

        x = baseActor.lx;
        y = baseActor.ly;
        battleMsg = new ArrayList<>();
        calendar = new GameCalendar(100000);

        updataMenuWin();
        font = fontManager.fontL;
        font24 = fontManager.fontL;
        dmgFont = fontManager.fontL;
        roomMsg = getRoomDesc(roomMap, regionMap[baseActor.ry][baseActor.rx]);

        addMsg(roomMsg);
        tiledMap = tiledMapName.get("武馆大厅");
        frames = TextureRegion.split(Cache.instance().character("001-Fighter01.png"), 32, 48);
//      进度条测试
        progessBarTexture = Cache.instance().system("bar.png");
        knobTexture = Cache.instance().system("knob.png");
        ProgressBar.ProgressBarStyle pbs = new ProgressBar.ProgressBarStyle();
        pbs.background = new TextureRegionDrawable(new TextureRegion(progessBarTexture));
//        pbs.background.
//        pbs.background.setMinWidth(200);
//        pbs.background.setMinWidth(100);
        pbs.knob = new TextureRegionDrawable(new TextureRegion(knobTexture));
        progressBar = new ProgressBar(0, 100, 1, false, pbs);
        progressBar.setSize(400, 10);
        progressBar.setPosition(640 - 100, 300);
        progressBar.setValue(count);
        addActor(progressBar);
        //        UIStage.addActor(baseActor);
//        UIStage.addActor(npc1);
//        圆形进度条测试
        circularProgress = new CircularProgress(new TextureRegion(Cache.instance().system("circular.png")));
        circularProgress.setPosition(640 - 50, 60);
        circularCoverTexture = Cache.instance().system("circularCover.png");
//        另一种风格测试
        progressActor = new ProgressActor();
        UIStage.addActor(progressActor);
        progress = new ProgressTimer(circularCoverTexture, 100, 100, 5, 16, 3, ProgressTimer.DIRECTION_COUNTERCLOCKWISE);
//   用label显示伤害
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.YELLOW;
        label = new Label("", labelStyle);
        addActor(label);
    }

    void loadData() {
        actors = ReadData.actorMap("Data/Actors.txt");
        tileMap = ReadData.tileMap("Data/Tiles.txt");
        menuWin = ReadData.readStrMatSwapY("Data/mapStageWin.txt");
        regionMap = ReadData.readStrMatSwapY("Data/mapWuGuanMat.txt");
        roomMap = ReadData.roomMap("Data/Rooms.txt");
        baseActor = actors.get(me);
    }

    String getRoomDesc(Map<String, Room> roomMap, String name) {
        String desc = "";
        if (roomMap.containsKey(name)) {
            desc = roomMap.get(name).describe;
        }
        return desc;
    }

    void updataMenuWin() {
        timeWin[0][4] = calendar.printCalenar();
        timeWin[0][0] = baseActor.region;
//        timeWin[1][0] = "[LIGHT_GRAY]" + calendar.moon();
//        timeWin[0][10] = "dur"+dur;

//        menuWin[32][0] = baseActor.nickname;
//        menuWin[31][0] = baseActor.name;
//        menuWin[31][4] = baseActor.family;
//        menuWin[30][2] = Show.disExp(baseActor.HP,baseActor.maxHP);
//        menuWin[29][5] = baseActor.HP + "";
//        menuWin[29][2] = Show.disExp(baseActor.MP,baseActor.maxMP);
//        menuWin[28][5] = baseActor.MP + "";
//        menuWin[28][2] = Show.disExp(baseActor.HP,baseActor.maxHP);

        faceWin[5][5] = baseActor.nickname;
        faceWin[4][5] = baseActor.name;
        faceWin[3][5] = baseActor.family;
        faceWin[5][10] = "气血";
        faceWin[4][10] = "真气";
        faceWin[3][10] = "精力";
        faceWin[5][12] = Show.disExp(baseActor.HP, baseActor.maxHP);
        faceWin[4][12] = Show.disExp(baseActor.MP, baseActor.maxMP);
        faceWin[5][18] = String.valueOf(baseActor.HP);
        faceWin[4][18] = String.valueOf(baseActor.MP);

//        menuWin[21][3] = baseActor.skills[0];
//        menuWin[18][3] = baseActor.target;
//        menuWin[25][5] = baseActor.actionStr;
//        menuWin[25][0] = baseActor.region;
//        menuWin[12][0] = Chinesed.number(2000000000);
//        FontManager.getInstance().addCharacters(Chinesed.number(2000000000));
//        menuWin[11][0] = Money.moneyStr(2000000000);

//        menuWin[38][19] = baseActor.Con+"";
//        menuWin[37][19] = baseActor.Dex+"";
//        menuWin[36][19] = baseActor.Int+"";
//        menuWin[35][19] = baseActor.Wis+"";
//        menuWin[34][19] = baseActor.Cha+"";
//        menuWin[30][0] = calendar.printCalenar();
//        menuWin[31][2] = "[LIGHT_GRAY]" + calendar.moon();
        messageWin = addMessage(nomalMsg, messageWin);
        battleWin = addMessage(battleMsg, battleWin);
        strings = Show.str2array(roomMsg, 19);
        skillWin[1][0] = "A";
        skillWin[1][5] = "B";
        skillWin[1][10] = "X";
        skillWin[1][15] = "Y";
        skillWin[1][20] = "R1";
        skillWin[1][25] = "R2";
        skillWin[1][30] = "L1";
        skillWin[1][35] = "L2";
        skillWin[1][1] = baseActor.skills[0];
        skillWin[1][6] = "伏虎拳";
        skillWin[1][11] = "罗汉伏魔";
        skillWin[1][16] = "降龙伏虎";
        skillWin[1][21] = "无";
        skillWin[1][26] = "无";
        skillWin[1][31] = "少林身法";
        skillWin[1][36] = "少林内功";
//        skillWin[0][1] = "3";
//        skillWin[0][6] = "5";
//        skillWin[0][11] = "15";
//        skillWin[0][16] = "13";
//        skillWin[0][21] = "0";
//        skillWin[0][26] = "0";
//        skillWin[0][31] = "20";
//        skillWin[0][36] = "100";
        buffWin[0][18] = "[YELLOW]内伤";
        buffWin[0][16] = "[GREEN]中毒";
        buffWin[0][14] = "[RED]流血";
        buffWin[1][18] = "[GREEN]饱食";
        buffWin[1][16] = "[BLUE]畅饮";
        buffWin[1][14] = "[YELLOW]高兴";
    }

    String[] strings;

    void renderAnimation(Animation animation, int x, int y, float dur) {
        if (animation != null && !animation.isAnimationFinished(dur)) {
            getBatch().draw((TextureRegion) animation.getKeyFrame(dur), x, y);
            font.draw(getBatch(), "[YELLOW]信息", baseActor.getX(), baseActor.getY() + 70 + dur * 32);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Constants.KEY_A) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passA(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_B) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passB(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_X) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passX(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_Y) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passY(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_R1) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passR1(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_R2) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passR2(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_L1) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passL1(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_L2) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passL2(mapLocal, baseActor);
        }
        if (keycode == Constants.KEY_START) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passStart(mapLocal, baseActor);
            loadData();
        }
        if (keycode == Constants.KEY_BACK) {
            actionManager.actionKeyMap.get(baseActor.actionStr).passBack(mapLocal, baseActor);
        }

        if (keycode == Input.Keys.Q) {
            StageManager.getInstance().currentStage = new StageActor(me);
        }
        if (keycode == Input.Keys.I) {
            StageManager.getInstance().currentStage = new StageItem(baseActor);
        }
        if (keycode == Input.Keys.M) {
            StageManager.getInstance().currentStage = new StageRegionMap("Data/RegionFloorMat1.txt");
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
        this.calendar = calendar.opratorAdd(1);
        handleDebugInput();
        mapWin = Show.miniMap(regionMap, baseActor.rx, baseActor.ry, 5, 5);
        updataMenuWin();
//        actorMap = Show.actorsMap(tileMap,actors,me);

        fps = Gdx.graphics.getFramesPerSecond();
        baseActor.updata();
        roomMsg = getRoomDesc(roomMap, regionMap[baseActor.ry][baseActor.rx]);

        tiledMap = tiledMapName.get(regionMap[baseActor.ry][baseActor.rx]);
        baseActor.act(0.2f);
        count = count + Gdx.graphics.getDeltaTime() * 10;
        progressBar.setValue(count);
        circularProgress.setPercent(count / 100);
//        progressBar.act(0.2f);
    }


    @Override
    public void draw() {
        super.draw();
        getBatch().begin();
        Show.mapLayers(getBatch(), tiledMap, 0, 0);
//        Show.renderTileMap(getBatch(),actorMap,384,192);
        Show.mapLayer(getBatch(), tiledMap, 0, 0, "cover");
        dur = animationManager.dur;
        Show.actorsName(getBatch(), actors, font, 0, 70, me);
//        Show.renderFont(getBatch(),dmgFont,"伤害",(int)baseActor.getX(),(int)baseActor.getY()+68);
        animationManager.updata();
//        图片素材都是从左下角开始绘制，单个帧动画192*192大小，人物32*32大小。俩个的中心偏移量即 （192-32）/2 即 80 的偏移量
//       同一时间，只有一个战斗动画。多个战斗动画，顺序播放
        getCamera().position.set(baseActor.getX(), baseActor.getY(), 0);
        renderAnimation(animation, (int) SkillActions.handPos(baseActor).x - 80, (int) SkillActions.handPos(baseActor).y - 80, animationManager.dur);

        getBatch().draw(animationManager.moveFrame(frames, baseActor.enumAction, baseActor.turn), baseActor.getX(), baseActor.getY());
        Show.renderCall(getBatch(), label, baseActor, 2.0f);

        getBatch().end();

        UIStage.getBatch().begin();
        Show.renderWin(UIStage.getBatch(), font, menuWin, 900, 10);
        Show.renderWin(UIStage.getBatch(), font, lookWin, 384, 736);
        Show.renderWin(UIStage.getBatch(), font, timeWin, 480, 780);
        Show.renderWin(UIStage.getBatch(), font, battleWin, 10, 80);
        Show.renderWin(UIStage.getBatch(), font, mapWin, 940, 704, 64, 20);
        Show.renderWin(UIStage.getBatch(), font, messageWin, 10, 280);
        Show.renderWin(UIStage.getBatch(), font, faceWin, 15, 680);
        Show.renderWin(UIStage.getBatch(), font, skillWin, 250, 20);
        Show.renderWin(UIStage.getBatch(), font, buffWin, 500, 760);
        Show.renderStrs(UIStage.getBatch(), font, strings, 10, 560, 20);
        Show.faceTexture(UIStage.getBatch(), baseActor.faceName, 18, 700);
        Show.showFps(UIStage.getBatch(), font, fps, 10, 760);
//        progressBar.draw(UIStage.getBatch(),1.0f);
//        circularProgress.draw(UIStage.getBatch(),0.5f);
//        UIStage.getBatch().draw(circularCoverTexture,640-60,50);
        progressActor.drawProgress(UIStage.getBatch(), 1, 400, 80, 1, 1);
//        progress.draw(Gdx.graphics.getDeltaTime()/5);
        UIStage.getBatch().end();
    }


    @Override
    public void dispose() {
        super.dispose();
        fontManager.dispose();
    }
}
