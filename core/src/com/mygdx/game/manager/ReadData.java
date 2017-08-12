package com.mygdx.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.action.Attack;
import com.mygdx.game.actor.BaseActor;
import com.mygdx.game.gameClass.Tile;
import com.mygdx.game.map.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ttwings on 2017/4/11.
 */
public class ReadData {
    public static ArrayList<String> readArrayFromFile(String filePath) {
        File file;
//		File file = new File(filePath);
//		FileHandle fileHandle;
        file =  Gdx.files.internal(filePath).file();
        String lineString;
        ArrayList<String> tempArray = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file))); // 读取的数据行
            while ((lineString = br.readLine()) != null) {
                tempArray.add(lineString);
//                通过读取的文本内容， 制作字典文件
//                 FontManager.getInstance().addCharacters(lineString);
            }
            br.close();
        } catch (Exception e) {
        }
        return tempArray;
    }
    public static void saveActorsToFile(Map<String,BaseActor>actorMap,String filePath){
        Json json = new Json();
        String s="";
        FileHandle file = Gdx.files.local(filePath);
        BaseActor actor;
            for(Object o:actorMap.keySet()){
                actor = actorMap.get(o);
                s += json.toJson(actor)+"\n";
            }
            file.writeString(s,false);
    }
    public static Map<String,BaseActor> loadActorFromFile(String filePath){
        Json json = new Json();
        FileHandle file = Gdx.files.local(filePath);
        BaseActor actor;
        String lineString;
        Map<String,BaseActor> actorMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.file()))); // 读取的数据行
            while ((lineString = br.readLine()) != null) {
                actor = json.fromJson(BaseActor.class,lineString);
                actorMap.put(actor.name,actor);
            }
            br.close();
        } catch (Exception e){

        } finally {

        }
        return actorMap;
    }

    public static String[][] readStrMatSwapY(String filePath) {
        ArrayList<String> tempArray = readArrayFromFile(filePath);
        String[][] strMat;
        String[] line;
        line = tempArray.get(0).split("\t");
        int w, h;
        h = tempArray.size();
        w = line.length;
        strMat = new String[h][w];
        for (int i = 0; i < h; i++) {
            line = tempArray.get(h-1-i).split("\t");
            strMat[i] = line;
        }
        return strMat;
    }

    public static String[][] readStrMat(String filePath) {
        ArrayList<String> tempArray = readArrayFromFile(filePath);
        String[][] strMat;
        String[] line;
        line = tempArray.get(0).split("\t");
        int w, h;
        h = tempArray.size();
        w = line.length;
        strMat = new String[h][w];
        for (int i = 0; i < h; i++) {
            line = tempArray.get(i).split("\t");
            strMat[i] = line;
        }
        return strMat;
    }
    // 将文件按照name(名字)放到map中
    public static Map<String, String[]> nameMap(String fileName) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        ArrayList<String> arrayList = readArrayFromFile(fileName);
        String[] strings;
        String name;
        if (arrayList.size() == 0) {
            Gdx.app.log("nameMap", "没有数据");
        }
        for (int i = 1; i < arrayList.size(); i++) {
            strings = arrayList.get(i).split("\t");
            name = strings[1];
            map.put(name, strings);
        }
        return map;
    }
    // attack map
    public static Map<String, Attack> attackMap(String fileName) {
        Map<String, Attack> map = new HashMap<String, Attack>();
        ArrayList<String> arrayList = readArrayFromFile(fileName);
        String[] strings;
        String s;
        String name;
        Attack attack;
        if (arrayList.size() == 0) {
            Gdx.app.log("nameMap", "没有数据");
        }
        for (int i = 1; i < arrayList.size(); i++) {
            s = arrayList.get(i);
            strings = s.split("\t");
            name = strings[1];
            attack = new Attack(strings);
            map.put(name, attack);
        }
        return map;
    }
    // actor map
    public static Map<String,BaseActor> actorMap(String fileName){
        Map<String, BaseActor> map = new HashMap<String, BaseActor>();
        ArrayList<String> arrayList = readArrayFromFile(fileName);
        String[] strings;
        String s;
        String name;
        BaseActor actor;
        if (arrayList.size() == 0) {
//            Gdx.app.log("nameMap", "没有数据");
        }
        for (int i = 1; i < arrayList.size(); i++) {
            s = arrayList.get(i);
            strings = s.split("\t");
            name = strings[1];
            actor = new BaseActor(strings);
            map.put(name,actor);
        }
        return map;
    }
//  获取房间信息，街道，走廊也算作房间的一种
    public static Map<String,Room> roomMap(String fileName){
        Map<String, Room> map = new HashMap<>();
        ArrayList<String> arrayList = readArrayFromFile(fileName);
        String[] strings;
        String s = "";
        Room room;
        if (arrayList.size() == 0) {
            Gdx.app.log("nameMap", "没有数据");
        }
        for (int i = 1; i < arrayList.size(); i++) {
            s = arrayList.get(i);
            strings = s.split("\t");
            if(strings.length<2){
                Gdx.app.debug(Room.class.getName(),strings[0]);
            }
            room = new Room(strings);
            map.put(room.name,room);
        }
        return map;
    }

    public static Map<String,Tile> tileMap(String fileName){
        Map<String, Tile> map = new HashMap<>();
        ArrayList<String> arrayList = readArrayFromFile(fileName);
        String[] strings;
        String s;
        String name;
        Tile tile;
        if (arrayList.size() == 0) {
            Gdx.app.log("nameMap", "没有数据");
        }
        for (int i = 1; i < arrayList.size(); i++) {
            s = arrayList.get(i);
            strings = s.split("\t");
            tile = new Tile(strings);
            map.put(tile.name,tile);
        }
        return map;
    }

    public static String[] readRowNames(String fileName) {
        ArrayList<String> arrayList;
        arrayList = readArrayFromFile(fileName);
        return arrayList.get(0).split("\t");
    }

    public static String getRowName(String fileName, int index) {
        return readRowNames(fileName)[index];
    }

    public static int getIndex(String fileName, String rowName) {
        String[] strings;
        int index = 0;
        strings = readRowNames(fileName);
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(rowName) || strings[i] == rowName) {
                index = i;
            }
        }
        return index;
    }

    public static String[] data(String fileName, String name) {
        return nameMap(fileName).get(name);
    }
    public static String[] actor(String name) {
        return nameMap("data/Actors.txt").get(name);
    }

    public static String[] skillAnimation(String name) {
        return nameMap("data/SkillAnimations.txt").get(name);
    }

    public static String[] miniRole(String name) {
        return nameMap("data/MiniRoles.txt").get(name);
    }
    public static String[] tile(String name) {
        return nameMap("data/Tiles.txt").get(name);
    }

    public static String data(String fileName, String name, String colName) {
        return data(fileName, name)[getIndex(name, colName)];
    }
    public static String actor(String name, String colName) {
        return actor(name)[getIndex("data/Actors.txt", colName)];
    }
    public static String tile(String name, String colName) {
        return tile(name)[getIndex("data/Tiles.txt", colName)];
    }

    public static String skillAnimation(String name, String colName) {
        return skillAnimation(name)[getIndex("data/SkillAnimations.txt", colName)];
    }

}