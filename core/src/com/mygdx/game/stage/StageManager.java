package com.mygdx.game.stage;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by apple on 16/12/25.
 */
public class StageManager {
    private StageManager(){init();}
    private static StageManager instance = new StageManager();
    public static  StageManager getInstance(){
        return instance;
    }
    public Stage currentStage,perStage,nextStage;
    public Map<String,Stage> stageMap;
    Camera camera;
    public void init(){
//        currentStage = StageMain.getInstance();
//        currentStage = StageMain.getInstance();
        perStage = null;
        nextStage = null;
        stageMap = new HashMap<String, Stage>();
    }
    public void addStage(String name,Stage stage){
        stageMap.put(name,stage);
    }
    public void setCurrentStage(Stage stage){
        this.currentStage = stage;
    }
    public void changeStage(Stage stage){
        this.perStage = currentStage;
        camera = currentStage.getCamera();
        this.currentStage = stage;
    }
    public void draw(){
        currentStage.draw();
    }
}
