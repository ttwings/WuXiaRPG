package com.mygdx.game.manager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.action.Attack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by apple on 16/11/20.
 */
public class DataManager {
	private DataManager() {
	}
	private static DataManager instance = new DataManager();
	public static DataManager getInstance() {
		return instance;
	}
	// Map<String, String[]> map = new HashMap<String, String[]>();
	public ArrayList<String> readArrayFromFile(String filePath) {
		File file;
//		file = new File(filePath);
//		FileHandle fileHandle;
		file =  Gdx.files.internal(filePath).file();
		String lineString;
		ArrayList<String> tempArray = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file))); // 读取的数据行
			while ((lineString = br.readLine()) != null) {
				tempArray.add(lineString);
				// FontManager.getInstance().addCharacters(lineString);
			}
			br.close();
		} catch (Exception e) {
		}
		return tempArray;
	}
	public String readStr(String filePath) {
		File file;
		file = new File(filePath);
		String strdata = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file))); // 读取的数据行
			strdata = br.toString();
			br.close();
		} catch (Exception e) {
		}
		return strdata;
	}
	public String[][] readStrMat(String filePath) {
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
	public Map<String, String[]> nameMap(String fileName) {
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
	public Map<String, Attack> attackMap(String fileName) {
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

	public String[] readRowNames(String fileName) {
		ArrayList<String> arrayList;
		arrayList = readArrayFromFile(fileName);
		return arrayList.get(0).split("\t");
	}

	public String getRowName(String fileName, int index) {
		return readRowNames(fileName)[index];
	}

	public int getIndex(String fileName, String rowName) {
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

	public String[] data(String fileName, String name) {
		return nameMap(fileName).get(name);
	}
	public String[] actor(String name) {
		return nameMap("data/Actors.txt").get(name);
	}
	public String[] skillAnimation(String name) {
		return nameMap("data/SkillAnimations.txt").get(name);
	}

	public String[] miniRole(String name) {
		return nameMap("data/MiniRoles.txt").get(name);
	}
	public String[] tile(String name) {
		return nameMap("data/Tiles.txt").get(name);
	}
	public String miniRole(String name, String colName) {
		return miniRole(name)[getIndex("data/MiniRoles.txt", colName)];
	}
	public String miniRole(String name, int col) {
		return miniRole(name)[col];
	}

	public String data(String fileName, String name, String colName) {
		return data(fileName, name)[getIndex(name, colName)];
	}
	public String actor(String name, String colName) {
		return actor(name)[getIndex("data/Actors.txt", colName)];
	}
	public String tile(String name, String colName) {
		return tile(name)[getIndex("data/Tiles.txt", colName)];
	}

	public String skillAnimation(String name, String colName) {
		return skillAnimation(name)[getIndex("data/SkillAnimations.txt", colName)];
	}

}
