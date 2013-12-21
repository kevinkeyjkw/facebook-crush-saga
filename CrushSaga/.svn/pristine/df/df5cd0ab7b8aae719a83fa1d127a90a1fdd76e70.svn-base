/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.data.CrushSagaDataModel;
import crush_saga.file.CrushSagaFileManager;
import crush_saga.ui.CrushSagaMiniGame;
import crush_saga_main.CrushSaga.CrushSagaPropertyType;
import static crush_saga_main.CrushSagaConstants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kevin
 */
public class SelectLevelHandler implements ActionListener{
    private CrushSagaMiniGame game;
    private String levelFile;
    public SelectLevelHandler(CrushSagaMiniGame miniGame,String initLevelFile)
    {
        game = miniGame;
        levelFile = initLevelFile;//levelFile = DATA_PATH + LEVEL_OPTIONS
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(game.isCurrentScreenState(SAGA_SCREEN_STATE))
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String dataPath = props.getProperty(CrushSagaPropertyType.DATA_PATH);
            ArrayList<String> levels = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_OPTIONS);
            ArrayList<String> levelDescriptions = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_DESCRIPTIONS);
            ArrayList<String> levelNames = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_NAMES);
            
            
            CrushSagaDataModel data = (CrushSagaDataModel)game.getDataModel();
            CrushSagaFileManager fileManager = game.getFileManager();
            //load level etc
            fileManager.loadLevel(levelFile);
            data.setCurrentLevel(levelFile.substring(7, levelFile.length()));// WE DONT WANT ./DATA/ IN ./data/./zombie_crush_saga/LevelOne.txt
            data.setStarsEarned(game.getPlayerRecord().getStarsEarned(levelFile.substring(7, levelFile.length())));
            
            for(int i = 0;i < levelNames.size();i++){
                if(levelFile.substring(7, levelFile.length()).equals(levels.get(i))){
                    data.setLevelName(levelNames.get(i));
                }
            }
            
            //initiate play level button
            game.getGUIButtons().get(SAGA_PLAY_LEVEL_BUTTON_TYPE).setState(VISIBLE_STATE);
            game.getGUIButtons().get(SAGA_PLAY_LEVEL_BUTTON_TYPE).setEnabled(true);
            game.getGUIButtons().get(LEVEL_DIALOG_CLOSE_BUTTON_TYPE).setState(VISIBLE_STATE);
            game.getGUIButtons().get(LEVEL_DIALOG_CLOSE_BUTTON_TYPE).setEnabled(true);
            //INITIATE STARS EARNED
            int numOfStars = data.getStarsEarned();
            if(numOfStars == 3){
            game.getGUIDecor().get(STAR_ONE_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_TWO_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_THREE_TYPE).setState(FILLED_STATE);
            }else if(numOfStars == 2){
                game.getGUIDecor().get(STAR_ONE_TYPE).setState(FILLED_STATE);
                game.getGUIDecor().get(STAR_TWO_TYPE).setState(FILLED_STATE);
                game.getGUIDecor().get(STAR_THREE_TYPE).setState(UNFILLED_STATE);
            }else if(numOfStars == 1){
                game.getGUIDecor().get(STAR_ONE_TYPE).setState(FILLED_STATE);
                game.getGUIDecor().get(STAR_TWO_TYPE).setState(UNFILLED_STATE);
                game.getGUIDecor().get(STAR_THREE_TYPE).setState(UNFILLED_STATE);
            }else{
                game.getGUIDecor().get(STAR_ONE_TYPE).setState(UNFILLED_STATE);
                game.getGUIDecor().get(STAR_TWO_TYPE).setState(UNFILLED_STATE);
                game.getGUIDecor().get(STAR_THREE_TYPE).setState(UNFILLED_STATE);
            }
            data.displayLevelDialog();
            for(int i = 0;i < levels.size();i++)
            {
                if(levelFile.equals(dataPath + levels.get(i)))
                {
                    game.setCurrentLevelDescription(levelDescriptions.get(i));
                    return;
                }
            }
        }
    }
}
