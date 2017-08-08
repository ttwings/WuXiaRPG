package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.gameClass.Tile;
import com.mygdx.game.manager.AnimationManager;
import com.mygdx.game.manager.Cache;
import com.mygdx.game.map.MapLocal;

import java.util.*;

/**
 * Created by Administrator on 2017/4/18.
 */
public class Show {
    public static ArrayList<String> addMsg(ArrayList<String> battleMsg, String s, int sub) {
        int length;
        int num;
        //        sub = 49;
        length = s.length();
        num = length / sub + 1;
        if (num == 1) {
            battleMsg.add(s);
        }
        if (num == 2) {
            battleMsg.add(s.substring(0, sub));
            battleMsg.add(s.substring(sub));
        }
        if (num == 3) {
            battleMsg.add(s.substring(0, sub));
            battleMsg.add(s.substring(sub, sub * 2));
            battleMsg.add(s.substring(sub * 2));
        }
        if (num == 4) {
            battleMsg.add(s.substring(0, sub));
            battleMsg.add(s.substring(sub, sub * 2));
            battleMsg.add(s.substring(sub * 2, sub * 3));
            battleMsg.add(s.substring(sub * 3));
        }
        if (num == 5) {
            battleMsg.add(s.substring(0, sub));
            battleMsg.add(s.substring(sub, sub * 2));
            battleMsg.add(s.substring(sub * 2, sub * 3));
            battleMsg.add(s.substring(sub * 3, sub * 4));
            battleMsg.add(s.substring(sub * 4));
        }
        return battleMsg;
    }

    public static String[] str2array(String s, int sub) {
        int length;
        int num;
        //        sub = 49;
        length = s.length();
        num = length / sub + 1;
        String[] strings = new String[num];
//		Gdx.app.debug("num",num+"");
        if (num == 1) {
//			battleMsg.add(s);
            strings[0] = s;
        }
        if (num == 2) {
            strings[1] = (s.substring(0, sub));
            strings[0] = (s.substring(sub));
        }
        if (num == 3) {
            strings[2] = (s.substring(0, sub));
            strings[1] = (s.substring(sub, sub * 2));
            strings[0] = (s.substring(sub * 2));
        }
        if (num == 4) {
            strings[3] = (s.substring(0, sub));
            strings[2] = (s.substring(sub, sub * 2));
            strings[1] = (s.substring(sub * 2, sub * 3));
            strings[0] = (s.substring(sub * 3));
        }
        if (num == 5) {
            strings[4] = (s.substring(0, sub));
            strings[3] = (s.substring(sub, sub * 2));
            strings[2] = (s.substring(sub * 2, sub * 3));
            strings[1] = (s.substring(sub * 3, sub * 4));
            strings[0] = (s.substring(sub * 4));
        } else {
            for (int i = 0; i < num - 1; i++) {
                strings[num - 1 - i] = s.substring(sub * i, sub * (i + 1));
            }
            strings[0] = s.substring((num - 1) * sub);
//			Gdx.app.debug("Show","信息太长了，不适合这种方法");
        }
        return strings;
    }

    public static String[][] miniMap(String[][] regionMap, int x, int y, int w, int h) {
        String[][] miniMap = new String[h][w];
        if (y - h / 2 >= 0 && x - w / 2 >= 0 && x + w / 2 < regionMap[0].length && y + h / 2 < regionMap.length) {
            for (int j = -h / 2; j < h / 2; j++) {
                for (int i = -w / 2; i < w / 2; i++) {
                    if (i == 0 && j == 0) {
                        miniMap[j + h / 2][i + w / 2] = "[GREEN]" + regionMap[j + y][i + x];
                    } else {
                        miniMap[j + h / 2][i + w / 2] = regionMap[j + y][i + x];
                    }
                }
            }
        }

        return miniMap;
    }

    public static String disHP(int cur, int max) {
        String s = "";
        if (cur / max == 1) {
            s = "[GREEN]|||||";
        } else if (cur * 10 / max == 9) {
            s = "[GREEN]||||\\";
        } else if (cur * 10 / max == 8) {
            s = "[FOREST]||||";
        } else if (cur * 10 / max == 7) {
            s = "[FOREST]|||\\";
        } else if (cur * 10 / max == 6) {
            s = "[YELLOW]|||";
        } else if (cur * 10 / max == 5) {
            s = "[YELLOW]||\\";
        } else if (cur * 10 / max == 4) {
            s = "[CORAL]||";
        } else if (cur * 10 / max == 3) {
            s = "[CORAL]|\\";
        } else if (cur * 10 / max == 2) {
            s = "[RED]|";
        } else if (cur * 10 / max == 1) {
            s = "[RED]\\";
        } else if (cur * 10 / max == 0) {
            s = "[RED]:";
        }
        return s;
    }

    public static String disExp(int cur, int max) {
        String s = "";
        if (cur / max == 1) {
            s = "[GREEN]■■■■■■■■■■";
        } else if (cur * 10 / max == 9) {
            s = "[GREEN]■■■■■■■■■";
        } else if (cur * 10 / max == 8) {
            s = "[FOREST]■■■■■■■■";
        } else if (cur * 10 / max == 7) {
            s = "[FOREST]■■■■■■■";
        } else if (cur * 10 / max == 6) {
            s = "[YELLOW]■■■■■■";
        } else if (cur * 10 / max == 5) {
            s = "[YELLOW]■■■■■";
        } else if (cur * 10 / max == 4) {
            s = "[CORAL]■■■■";
        } else if (cur * 10 / max == 3) {
            s = "[CORAL]■■■";
        } else if (cur * 10 / max == 2) {
            s = "[RED]■■";
        } else if (cur * 10 / max == 1) {
            s = "[RED]■";
        } else if (cur * 10 / max == 0) {
            s = "[RED]";
        }
        return s;
    }

    public static void showFps(Batch batch, BitmapFont font, int fps, int x, int y) {
        font.draw(batch, "[RED]" + fps + "", x, y);
    }

    public static void renderWin(Batch batch, BitmapFont font, String[][] win, int x0, int y0) {
        renderWin(batch, font, win, x0, y0, 20, 20);
    }

    public static void renderWin(Batch batch, BitmapFont font, String[][] win, int x0, int y0, int width, int height) {
        int w, h;
        w = win[0].length;
        h = win.length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (win[i][j] != null && !win[i][j].equals("x")) {
                    font.draw(batch, win[i][j], j * width + x0, i * height + y0);
                }
            }
        }
    }

    //	输出 字符串数组
    public static void renderStrs(Batch batch, BitmapFont font, String[] strings, int x0, int y0, int height) {
        int h = strings.length;
        for (int i = 0; i < h; i++) {
            if (strings[i] != null) {
                font.draw(batch, strings[i], x0, i * height + y0);
            }
        }
    }

    public static void renderTileMap(Batch batch, TextureRegion[][] tileMap) {
        int w, h, length;
        length = 11;
        w = tileMap[0].length;
        h = tileMap.length;
        for (int i = h - 1; i > -1; i--) {
            for (int j = 0; j < w; ++j) {
                if (tileMap[i][j] != null) {
                    batch.draw(tileMap[i][j], (j - length) * 32 + 336, (i - length) * 32 + 336);
                }
            }
        }
    }

    public static void renderTileMap(Batch batch, TextureRegion[][] tileMap, int dx, int dy) {
        int w, h, length;
        w = tileMap[0].length;
        h = tileMap.length;
        for (int i = h - 1; i > -1; i--) {
            for (int j = 0; j < w; ++j) {
                if (tileMap[i][j] != null) {
                    batch.draw(tileMap[i][j], j * 32 + dx, i * 32 + dy);
                }
            }
        }
    }

    public static TextureRegion[][] initTileMap(Map<String, Tile> tileMap, MapLocal map, int x0, int y0, int x1, int y1, int index) {
        String tileStr = "";
        int w, h;
        w = x1 - x0 + 1;
        h = y1 - y0 + 1;
        TextureRegion[][] textureRegions;
        textureRegions = new TextureRegion[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                tileStr = map.datas[i + y0][j + x0].split(",")[index];
                if (tileStr.equals("空气") || tileStr.equals("") || tileStr == null) {
                    textureRegions[i][j] = null;
                } else if (tileMap.containsKey(tileStr)) {
                    textureRegions[i][j] = tileMap.get(tileStr).textureRegion;

                } else {
                    Gdx.app.debug(map.getClass().getName(), tileStr);
                }
            }
        }
        return textureRegions;
    }

    public static TextureRegion[][] actorsMap(Map<String, Tile> tileMap, Map<String, BaseActor> actorMap, int x0, int y0, int x1, int y1) {
        int w, h, x, y, nx, ny;
        w = x1 - x0 + 1;
        h = y1 - y0 + 1;
        TextureRegion[][] map;
        map = new TextureRegion[h][w];
        x = actorMap.get("主角").lx;
        y = actorMap.get("主角").ly;
        nx = actorMap.get("主角").lx;
        ny = actorMap.get("主角").ly;
        map[y1 - y][x1 - x] = tileMap.get(actorMap.get("主角").characterName).textureRegion;
        //        map[y1 - y][x1 - x] = textureMap.loadTile(baseActor.characterName);
        if ((ny - (y - 13)) >= 0 && (ny - (y - 13)) < h)
            if ((nx - (x - 13)) >= 0 && (nx - (x - 13)) < w) {
                map[ny - (y - 13)][nx - (x - 13)] = tileMap.get(actorMap.get("主角").characterName).textureRegion;
                //                map[ny - (y - 13)][nx - (x - 13)] = textureMap.loadTile(npc1.characterName);

            }
        return map;
    }

    public static TextureRegion[][] actorsMap(Map<String, Tile> tileMap, Map<String, BaseActor> actorMap, String me) {
        int w, h, x, y;
        w = 16;
        h = 16;
        TextureRegion[][] map;
        map = new TextureRegion[h][w];
        Set set = actorNameSet(actorMap, me);
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            String key = (String) iter.next();
            BaseActor baseActor = actorMap.get(key);
            x = baseActor.lx;
            y = baseActor.ly;
            map[y][x] = tileMap.get(baseActor.characterName).textureRegion;
        }
        return map;
    }

    public static Set<String> actorNameSet(Map<String, BaseActor> actorMap, String me) {
        int x0, y0, x1, y1;
        Set set = actorMap.keySet();
        Set<String> nameSet = new HashSet<>();
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            String key = (String) iter.next();
            BaseActor baseActor = actorMap.get(key);
            x1 = baseActor.rx;
            y1 = baseActor.ry;
            x0 = actorMap.get(me).rx;
            y0 = actorMap.get(me).ry;
            if (x0 == x1 && y0 == y1) {
                nameSet.add(key);
            }
        }
        return nameSet;
    }

    public static void renderCall(Batch batch, Label label, BaseActor actor, float dur) {
        if (actor.call.length() == 0 || actor.call == null) {
//			Gdx.app.debug("Call","");
        } else {
            label.setText(actor.call);
//			label.draw(batch,actor.call,actor.getX(),actor.getY()+turn*4 + 70);
            label.setX(actor.getX());
            label.setY(actor.getY() + 70);
            label.addAction(Actions.moveTo(actor.getX(), actor.getY() + 170, dur));
//			label.addAction(Actions.moveBy());
            label.draw(batch, 1f);
//			Gdx.app.debug("Show",label.getY()+"");
//			if (label.)
//			actor.call = "";
        }
    }

    public static void renderFont(Batch batch, BitmapFont font, String str, int x, int y) {
        font.draw(batch, str, x, y);
    }

    public static void actorsName(Batch batch, Map<String, BaseActor> actorMap, BitmapFont font, int offx, int offy, String me) {
        int x, y, x0, y0, x1, y1;
        Set set = actorMap.keySet();
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            String key = (String) iter.next();
            BaseActor baseActor = actorMap.get(key);
            x = (int) baseActor.getX();
            y = (int) baseActor.getY();
            x1 = baseActor.rx;
            y1 = baseActor.ry;
            x0 = actorMap.get(me).rx;
            y0 = actorMap.get(me).ry;
            if (x0 == x1 && y0 == y1) {
                font.draw(batch, key, x + offx, y + offy);
            }
        }
    }

    public static void mapLayer(Batch batch, TiledMap map, int x, int y, String layerName) {
        if (map == null) {
//			System.out.println("map is null");
            return;
        }
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerName);
        TiledMapTileLayer.Cell cell;
        int h, w;
//		绘制区域控制
        h = layer.getHeight();
        w = layer.getWidth();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cell = layer.getCell(j, i);
                if (cell != null) {
                    batch.draw(cell.getTile().getTextureRegion(), j * 32 + x, i * 32 + y);
                }
            }
        }
    }

    public static void mapLayers(Batch batch, TiledMap map, int x, int y) {
        mapLayer(batch, map, x, y, "floor");
        mapLayer(batch, map, x, y, "wall");
        mapLayer(batch, map, x, y, "obj");
    }

    public static void faceTexture(Batch batch, String faceName, int x, int y) {
        batch.draw(Cache.instance().face(faceName), x, y);
    }
//
}
