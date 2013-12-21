/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga_main;

import properties_manager.PropertiesManager;
import crush_saga.ui.CrushSagaMiniGame;
import xml_utilities.InvalidXMLFileFormatException;
/**
 *
 * @author Kevin
 */
public class CrushSaga {
    static CrushSagaMiniGame miniGame = new CrushSagaMiniGame();
    
    static String PROPERTY_TYPES_LIST = "property_types.txt";
    static String UI_PROPERTIES_FILE_NAME = "properties.xml";
    static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";    
    static String DATA_PATH = "./data/";
    
    public static void main(String[]args)
    {
     try{
         PropertiesManager props = PropertiesManager.getPropertiesManager();
         props.addProperty(CrushSagaPropertyType.UI_PROPERTIES_FILE_NAME,UI_PROPERTIES_FILE_NAME);
         props.addProperty(CrushSagaPropertyType.PROPERTIES_SCHEMA_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            props.addProperty(CrushSagaPropertyType.DATA_PATH.toString(), DATA_PATH);
            props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
         
            String gameFlavorFile = props.getProperty(CrushSagaPropertyType.GAME_FLAVOR_FILE_NAME);
            props.loadProperties(gameFlavorFile, PROPERTIES_SCHEMA_FILE_NAME);
            
            String appTitle = props.getProperty(CrushSagaPropertyType.GAME_TITLE_TEXT);
            int fps = Integer.parseInt(props.getProperty(CrushSagaPropertyType.FPS));
            
            miniGame.initMiniGame(appTitle, fps);
            miniGame.startGame();
           // miniGame.getDataModel().beginGame();
     }catch(InvalidXMLFileFormatException ixmlffe)
     {
         
     }   
    }
    public enum CrushSagaPropertyType
    {
         UI_PROPERTIES_FILE_NAME,
        PROPERTIES_SCHEMA_FILE_NAME,
        GAME_FLAVOR_FILE_NAME,
        RECORD_FILE_NAME,
        GAME_TITLE_TEXT,
         DATA_PATH,
        IMG_PATH,
        
        WINDOW_WIDTH,
        WINDOW_HEIGHT,
        FPS,
        GAME_WIDTH,
        GAME_HEIGHT,
        GAME_LEFT_OFFSET,
        GAME_TOP_OFFSET,
        
        MALLET_IMAGE_NAME,
        MALLET_BUTTON_IMAGE_NAME,
        MALLET_BUTTON_MOUSE_OVER_IMAGE_NAME,
    TIME_DISPLAY_IMAGE_NAME,
    SCORE_DISPLAY_IMAGE_NAME,
    LEVEL_DIALOG_IMAGE_NAME,
    WIN_DIALOG_IMAGE_NAME,
    LOSE_DIALOG_IMAGE_NAME,
    LEVEL_STAR_COMPLETED_IMAGE_NAME,
    LEVEL_STAR_UNCOMPLETED_IMAGE_NAME,
    SPLASH_SCREEN_BUTTONS_IMAGE_NAMES,
    SPLASH_SCREEN_BUTTONS_MOUSE_OVER_IMAGE_NAMES,
    SCROLL_BUTTONS_IMAGE_NAMES,
    SCROLL_BUTTONS_MOUSE_OVER_IMAGE_NAMES,
    SAGA_QUIT_GAME_BUTTON_IMAGE_NAME,
    SAGA_QUIT_GAME_MOUSE_OVER_BUTTON_IMAGE_NAME,
    SPLASH_BACKGROUND_IMAGE_NAME,
    GAME_BACKGROUND_IMAGE_NAME,
    SAGA_BACKGROUND_IMAGE_NAME,
    TYPE_A_TILE_IMAGE_NAME,
    TYPE_B_TILE_IMAGE_NAME,
    TYPE_C_TILE_IMAGE_NAME,
    TYPE_D_TILE_IMAGE_NAME,
    TYPE_E_TILE_IMAGE_NAME,
    TYPE_F_TILE_IMAGE_NAME,
    TYPE_BLOCKED_TILE_IMAGE_NAME,
    TYPE_BOTTOM_TILE_IMAGE_NAME,
    TYPE_BOTTOM_TILE_SELECTED_IMAGE_NAME,
    TYPE_A_TILE_SELECTED_IMAGE_NAME,
    TYPE_B_TILE_SELECTED_IMAGE_NAME,
    TYPE_C_TILE_SELECTED_IMAGE_NAME,
    TYPE_D_TILE_SELECTED_IMAGE_NAME,
    TYPE_E_TILE_SELECTED_IMAGE_NAME,
    TYPE_F_TILE_SELECTED_IMAGE_NAME,
    TYPE_FOUR_IN_ROW_HORIZONTAL_TILE_IMAGE_NAME,
    TYPE_FOUR_IN_ROW_VERTICAL_TILE_IMAGE_NAME,
    TYPE_FIVE_IN_ROW_TILE_IMAGE_NAME,
    TYPE_LT_TILE_IMAGE_NAME,
    TYPE_FOUR_IN_ROW_HORIZONTAL_TILE_SELECTED_IMAGE_NAME,
    TYPE_FOUR_IN_ROW_VERTICAL_TILE_SELECTED_IMAGE_NAME,
    TYPE_FIVE_IN_ROW_TILE_SELECTED_IMAGE_NAME,
    TYPE_LT_TILE_SELECTED_IMAGE_NAME,
    LEVEL_OPTIONS,
    LEVEL_BUTTONS_IMAGE_NAMES,
    LEVEL_BUTTONS_MOUSE_OVER_IMAGE_NAMES,
    LEVEL_BUTTONS_LOCKED_IMAGE_NAMES,
    LEVEL_DESCRIPTIONS,
    SAGA_PLAY_LEVEL_BUTTON_IMAGE_NAME,
    SAGA_PLAY_LEVEL_MOUSE_OVER_BUTTON_IMAGE_NAME,
    QUIT_LEVEL_BUTTON_IMAGE_NAME,
    QUIT_LEVEL_BUTTON_MOUSE_OVER_IMAGE_NAME,
    LEVEL_DIALOG_CLOSE_BUTTON_IMAGE_NAME,
    LEVEL_DIALOG_CLOSE_BUTTON_MOUSE_OVER_IMAGE_NAME,
    TRY_AGAIN_BUTTON_IMAGE_NAME,
    TRY_AGAIN_MOUSE_OVER_BUTTON_IMAGE_NAME,
    PROGRESS_BAR_IMAGE_NAMES,
    POINTS_IMAGE_NAMES,
    JELLY_IMAGE_NAME,
    LEVEL_NAMES
    
    }
}
