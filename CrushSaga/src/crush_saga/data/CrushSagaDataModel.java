/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.data;

import crush_saga.ui.CrushSagaMiniGame;
import static crush_saga_main.CrushSagaConstants.*;
import crush_saga.ui.CrushSagaPanel;
import crush_saga.ui.CrushSagaTile;
import crush_saga_main.CrushSaga.CrushSagaPropertyType;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Sprite;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kevin
 */
public class CrushSagaDataModel extends MiniGameDataModel{
    private MiniGame game;
    private CrushSagaPanel sagaPanel;
    private SpriteType tileASpriteType;
    private SpriteType tileBSpriteType;
    private SpriteType tileCSpriteType;
    private SpriteType tileDSpriteType;
    private SpriteType tileESpriteType;
    private SpriteType tileFSpriteType;
    private SpriteType tileFourInRowHorizontalSpriteType;
    private SpriteType tileFourInRowVerticalSpriteType;
    private SpriteType tileFiveInRowSpriteType;
    private SpriteType tileLTSpriteType;
    private SpriteType tileBlockedSpriteType;
    private SpriteType tileBottomSpriteType;
    private SpriteType thirtyPointsSpriteType;
    private SpriteType twentyFivePointsSpriteType;
    private SpriteType twentyPointsSpriteType;
    private SpriteType jellySpriteType;
    GregorianCalendar startTime;
    GregorianCalendar endTime;
    private Timer timer;
    private GridMethods gm;

    //IN GAME LEVEL STATS
    private boolean initializingGrid = false;
    private int gameScore = 0;
    private int starsEarned = 1;
    private int scoreRequired = 1000;
    private String currentLevel;
    private String levelName="";
    private int movesRemaining = 0;
    private int totalMoves = 0;
    private int numTilesBottom = 0;
    CrushSagaTile selectedTile = null;
    private boolean isMallet = false;    
    private boolean isRunning1 = false;
    private boolean isRunning2 = false;
    private boolean isRunning3 = false;
    private boolean isRunning4 = false;
    private boolean swapBack = false;
    private CrushSagaTile tileToBeSwappedOne;
    private CrushSagaTile tileToBeSwappedTwo;
    private CrushSagaTile deleteThisTileSurroundings;
    private CrushSagaTile deleteThisTilesColumn;
    private CrushSagaTile deleteThisTilesRow;
    private boolean deleteVertically = false;
    private boolean deleteHorizontally = false;
    private boolean deleteSurroundings = false;
    private int gridColumns = 10;
    private int gridRows = 10;
    public static CrushSagaTile[][] tileGrid;
    public static CrushSagaTile[][] jellyGrid; 
    public static ArrayList<CrushSagaTile> movingTiles;
    public CrushSagaDataModel(MiniGame initMiniGame)
    {
        game = initMiniGame;
        sagaPanel = (CrushSagaPanel)game.getCanvas();
        tileGrid = new CrushSagaTile[gridColumns][gridRows];
        jellyGrid = new CrushSagaTile[gridColumns][gridRows];
        movingTiles = new ArrayList();
        //only has to be done once during entire application
         initTileSpriteTypes();
         gm = new GridMethods(this);
    }
    public void initTileSpriteTypes()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(CrushSagaPropertyType.IMG_PATH);
        String tile_image_file_path;
        BufferedImage img;
        //initiate tileASpriteType
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_A_TILE_IMAGE_NAME));
        tileASpriteType = new SpriteType(TILE_SPRITE_TYPE_A);
        tileASpriteType.addState(INVISIBLE_STATE, img);
        tileASpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_A_TILE_SELECTED_IMAGE_NAME));
        tileASpriteType.addState(SELECTED_STATE,img);
        //initiate tileBSpriteType
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_B_TILE_IMAGE_NAME));
        tileBSpriteType = new SpriteType(TILE_SPRITE_TYPE_B);
        tileBSpriteType.addState(INVISIBLE_STATE, img);
        tileBSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_B_TILE_SELECTED_IMAGE_NAME));
        tileBSpriteType.addState(SELECTED_STATE,img);
        //initiate tileCSpriteType
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_C_TILE_IMAGE_NAME));
        tileCSpriteType = new SpriteType(TILE_SPRITE_TYPE_C);
        tileCSpriteType.addState(INVISIBLE_STATE, img);
        tileCSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_C_TILE_SELECTED_IMAGE_NAME));
        tileCSpriteType.addState(SELECTED_STATE,img);
        //initiate tileDSpriteType
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_D_TILE_IMAGE_NAME));
        tileDSpriteType = new SpriteType(TILE_SPRITE_TYPE_D);
        tileDSpriteType.addState(INVISIBLE_STATE, img);
        tileDSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_D_TILE_SELECTED_IMAGE_NAME));
        tileDSpriteType.addState(SELECTED_STATE,img);
        //initiate tileESpriteType
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_E_TILE_IMAGE_NAME));
        tileESpriteType = new SpriteType(TILE_SPRITE_TYPE_E);
        tileESpriteType.addState(INVISIBLE_STATE, img);
        tileESpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_E_TILE_SELECTED_IMAGE_NAME));
        tileESpriteType.addState(SELECTED_STATE,img);
        //initiate tileFSpriteType
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_F_TILE_IMAGE_NAME));
        tileFSpriteType = new SpriteType(TILE_SPRITE_TYPE_F);
        tileFSpriteType.addState(INVISIBLE_STATE, img);
        tileFSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_F_TILE_SELECTED_IMAGE_NAME));
        tileFSpriteType.addState(SELECTED_STATE,img);
        //INITIATE FOUR_IN_ROW_HORIZONTAL_TILESPRITETYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_FOUR_IN_ROW_HORIZONTAL_TILE_IMAGE_NAME));
        tileFourInRowHorizontalSpriteType = new SpriteType(TILE_SPRITE_TYPE_FOUR_IN_ROW_HORIZONTAL);
        tileFourInRowHorizontalSpriteType.addState(INVISIBLE_STATE, img);
        tileFourInRowHorizontalSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_FOUR_IN_ROW_HORIZONTAL_TILE_SELECTED_IMAGE_NAME));
        tileFourInRowHorizontalSpriteType.addState(SELECTED_STATE,img);
        //INITIATE FOUR_IN_ROW_VERTICAL_TILESPRITETYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_FOUR_IN_ROW_VERTICAL_TILE_IMAGE_NAME));
        tileFourInRowVerticalSpriteType = new SpriteType(TILE_SPRITE_TYPE_FOUR_IN_ROW_VERTICAL);
        tileFourInRowVerticalSpriteType.addState(INVISIBLE_STATE, img);
        tileFourInRowVerticalSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_FOUR_IN_ROW_VERTICAL_TILE_SELECTED_IMAGE_NAME));
        tileFourInRowVerticalSpriteType.addState(SELECTED_STATE,img);
        //INITIATE FIVEINROWTILESPRITETYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_FIVE_IN_ROW_TILE_IMAGE_NAME));
        tileFiveInRowSpriteType = new SpriteType(TILE_SPRITE_TYPE_FIVE_IN_ROW);
        tileFiveInRowSpriteType.addState(INVISIBLE_STATE, img);
        tileFiveInRowSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_FIVE_IN_ROW_TILE_SELECTED_IMAGE_NAME));
        tileFiveInRowSpriteType.addState(SELECTED_STATE,img);
        //INITIATE LT_TILESPRITETYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_LT_TILE_IMAGE_NAME));
        tileLTSpriteType = new SpriteType(TILE_SPRITE_TYPE_LT);
        tileLTSpriteType.addState(INVISIBLE_STATE, img);
        tileLTSpriteType.addState(VISIBLE_STATE, img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_LT_TILE_SELECTED_IMAGE_NAME));
        tileLTSpriteType.addState(SELECTED_STATE,img);
        //INITIATE BLOCKED TILE SPRITETYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_BLOCKED_TILE_IMAGE_NAME));
        tileBlockedSpriteType = new SpriteType(TILE_SPRITE_TYPE_BLOCKED);
        tileBlockedSpriteType.addState(INVISIBLE_STATE, img);
        tileBlockedSpriteType.addState(VISIBLE_STATE, img);
        tileBlockedSpriteType.addState(BLOCKED_STATE,img);
        //INITIATE BOTTOM TILE SPRITETYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_BOTTOM_TILE_IMAGE_NAME));
        tileBottomSpriteType = new SpriteType(TILE_SPRITE_TYPE_BOTTOM);
        tileBottomSpriteType.addState(INVISIBLE_STATE, img);
        tileBottomSpriteType.addState(VISIBLE_STATE, img);
        tileBottomSpriteType.addState(BLOCKED_STATE,img);
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.TYPE_BOTTOM_TILE_SELECTED_IMAGE_NAME));
        tileBottomSpriteType.addState(SELECTED_STATE, img);
        //INITIATE 30 POINTS TILE
        ArrayList<String> pointImageNames = props.getPropertyOptionsList(CrushSagaPropertyType.POINTS_IMAGE_NAMES);
        img = game.loadImage(imgPath + pointImageNames.get(0));
        thirtyPointsSpriteType = new SpriteType(TILE_SPRITE_TYPE_THIRTY_POINTS);
        thirtyPointsSpriteType.addState(INVISIBLE_STATE, img);
        thirtyPointsSpriteType.addState(VISIBLE_STATE, img);
        //INITIATE 25 POINTS TILE
        img = game.loadImage(imgPath + pointImageNames.get(1));
        twentyFivePointsSpriteType = new SpriteType(TILE_SPRITE_TYPE_TWENTY_FIVE_POINTS);
        twentyFivePointsSpriteType.addState(INVISIBLE_STATE,img);
        twentyFivePointsSpriteType.addState(VISIBLE_STATE,img);
        //INITIATE 20 POINTS TILE
        img = game.loadImage(imgPath + pointImageNames.get(2));
        twentyPointsSpriteType = new SpriteType(TILE_SPRITE_TYPE_TWENTY_POINTS);
        twentyPointsSpriteType.addState(INVISIBLE_STATE,img);
        twentyPointsSpriteType.addState(VISIBLE_STATE,img);
        //INITIATE JELLY SPRITE TYPE
        img = game.loadImage(imgPath + props.getProperty(CrushSagaPropertyType.JELLY_IMAGE_NAME));
        jellySpriteType = new SpriteType(TILE_SPRITE_TYPE_JELLY);
        jellySpriteType.addState(INVISIBLE_STATE,img);
        jellySpriteType.addState(VISIBLE_STATE,img);
    }
    public void initTiles()
    {
        int totalInitialTiles = gridColumns * gridRows;
        initializingGrid = true;
        emptyTileGrids();
        initializeRegularTiles();
        
        gm.moveInitialGridDown();
        //MOVE BOTTOM TILES ONTO GRID IF LEVEL HAS BOTTOM TILES
        initializeBottomTiles();
        //MOVE BLOCKED TILES ONTO GRID ONLY IF THE LEVEL HAS BLOCKED TILES
        initializeBlockedTiles();
        //JELLY TILES
        initializeJellyTiles();
        initializingGrid = false;
    }
    public void initializeRegularTiles(){
        for(int i = 0;i < gridColumns;i++){
            for(int j = gridRows-1;j >= 0;j--){
                CrushSagaTile tile = gm.generateRandomTile(gm.calculateTileXInGrid(i), unassignedTilesY+(70*j),i,j);
                //put it in grid
                tileGrid[i][j] = tile;
            }
        }
        while(gm.deleteMatches()){
            gm.replaceInitialGridTiles();
        }
    }
    public void initializeJellyTiles(){
        int [] jellyTilesPos = ((CrushSagaMiniGame)game).getFileManager().getJellyTilesPos();
        for(int k = 0;k < jellyTilesPos.length;k+=2){
            int a = jellyTilesPos[k];
            int b = jellyTilesPos[k+1];
            CrushSagaTile jellyTile = gm.createTypeJellyTile(gm.calculateTileXInGrid(a), unassignedTilesY + (TILE_IMAGE_HEIGHT*b), 0, 0, VISIBLE_STATE);
            gm.moveJellyWithinGrid(jellyTile, a, b, TILE_GRID_VELOCITY);
        }
    }
    public void emptyTileGrids(){
        for(int i = 0;i < gridColumns;i++){
            for(int j = 0;j < gridRows;j++){
                tileGrid[i][j] = null;
                jellyGrid[i][j] = null;
                        
            }
        }
    }
    public void initializeBottomTiles(){
        int [] bottomTilesPos = ((CrushSagaMiniGame)game).getFileManager().getBottomTilePos();
        for(int k = 0;k < bottomTilesPos.length;k+=2){
            int a = bottomTilesPos[k];
            int b = bottomTilesPos[k+1];
            tileGrid[a][b].setState(INVISIBLE_STATE);
            CrushSagaTile botTile = gm.createTypeBottomTile(gm.calculateTileXInGrid(a),unassignedTilesY+(TILE_IMAGE_HEIGHT*b),0,0,VISIBLE_STATE);
            gm.moveTileWithinGrid(botTile, a, b, TILE_GRID_VELOCITY);
        }
    }
    public void initializeBlockedTiles(){
        int [] blockedTilesPos = ((CrushSagaMiniGame)game).getFileManager().getBlockedTilePos();
        for(int i = 0;i < blockedTilesPos.length;i += 2){
            int a = blockedTilesPos[i];
            int b = blockedTilesPos[i+1];
            tileGrid[a][b].setState(INVISIBLE_STATE);
            CrushSagaTile bt = gm.createTypeBlockedTile(gm.calculateTileXInGrid(a),unassignedTilesY+(TILE_IMAGE_HEIGHT*b),0,0,BLOCKED_STATE);
            
            gm.moveTileWithinGrid(bt, a, b, TILE_GRID_VELOCITY);
        }
    }
    public void createNoMatchesGrid()
    {
        for(int i = 0;i < gridColumns;i++){
            for(int j = 0;j < gridRows;j++){
                if((i + j) % 2 == 0){
                    tileGrid[i][j] = gm.createTypeATile(gm.calculateTileXInGrid(i), gm.calculateTileYInGrid(j), 0, 0, VISIBLE_STATE,i,j);
                }else{
                    tileGrid[i][j] = gm.createTypeBTile(gm.calculateTileXInGrid(i), gm.calculateTileYInGrid(j), 0, 0, VISIBLE_STATE,i,j);
                }
            }
        }
    }
    public void initTestOneGrid()
    {
        createNoMatchesGrid();
        //INSERT TEST SITUATION INTO GRID
        //HORIZONTAL 3-IN-ROW
        CrushSagaTile t;
        
        tileGrid[0][0] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(0),0,0,VISIBLE_STATE,0,0);
        tileGrid[0][2] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,0,2);
        tileGrid[1][1] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,1,1);
        //VERTICAL 3-IN-ROW
        tileGrid[0][4] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(4),0,0,VISIBLE_STATE,0,4);
        tileGrid[1][5] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,1,5);
        tileGrid[2][4] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(4),0,0,VISIBLE_STATE,2,4);
    }
    public void initTestTwoGrid()
    {
        createNoMatchesGrid();
        //HORIZONTAL 4-IN-ROW
        tileGrid[0][1] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,0,1);
        tileGrid[1][1] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,1,1);
        tileGrid[2][2] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,2,2);
        tileGrid[3][1] = gm.createTypeCTile(gm.calculateTileXInGrid(3),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,3,1);
        //VERTICAL 4-IN-ROW
        tileGrid[0][3] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(3),0,0,VISIBLE_STATE,0,3);
        tileGrid[0][4] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(4),0,0,VISIBLE_STATE,0,4);
        tileGrid[1][5] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,1,5);
        tileGrid[0][6] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,0,6);
    }
    public void initTestThreeGrid()
    {
        createNoMatchesGrid();
        //HORIZONTAL 5-IN-ROW
        tileGrid[0][1] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,0,1);
        tileGrid[1][1] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,1,1);
        tileGrid[2][2] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,2,2);
        tileGrid[3][1] = gm.createTypeCTile(gm.calculateTileXInGrid(3),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,3,1);
        tileGrid[4][1] = gm.createTypeCTile(gm.calculateTileXInGrid(4),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,4,1);
        //VERTICAL 5-IN-ROW
        tileGrid[0][3] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(3),0,0,VISIBLE_STATE,0,3);
        tileGrid[0][4] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(4),0,0,VISIBLE_STATE,0,4);
        tileGrid[1][5] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,1,5);
        tileGrid[0][6] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,0,6);
        tileGrid[0][7] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(7),0,0,VISIBLE_STATE,0,7);
    }
    public void initTestFourGrid()
    {
        //T SHAPE,4 CONFIGURATIONS
        createNoMatchesGrid();
        //FIRST CONFIGURATION
        tileGrid[0][1] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,0,1);
        tileGrid[1][0] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(0),0,0,VISIBLE_STATE,1,0);
        tileGrid[2][1] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,2,1);
        tileGrid[1][2] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,1,2);
        tileGrid[1][3] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(3),0,0,VISIBLE_STATE,1,3);
        //SECOND CONFIGURATION
        tileGrid[4][1] = gm.createTypeCTile(gm.calculateTileXInGrid(4),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,4,1);
        tileGrid[5][1] = gm.createTypeCTile(gm.calculateTileXInGrid(5),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,5,1);
        tileGrid[6][0] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(0),0,0,VISIBLE_STATE,6,0);
        tileGrid[7][1] = gm.createTypeCTile(gm.calculateTileXInGrid(7),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,7,1);
        tileGrid[6][2] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,6,2);
        //THIRD CONFIGURATION
        tileGrid[0][6] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,0,6);
        tileGrid[1][5] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,1,5);
        tileGrid[1][7] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(7),0,0,VISIBLE_STATE,1,7);
        tileGrid[2][6] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,2,6);
        tileGrid[3][6] = gm.createTypeCTile(gm.calculateTileXInGrid(3),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,3,6);
        //FOURTH CONFIGURATION
        tileGrid[5][6] = gm.createTypeCTile(gm.calculateTileXInGrid(5),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,5,6);
        tileGrid[6][4] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(4),0,0,VISIBLE_STATE,6,4);
        tileGrid[6][5] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,6,5);
        tileGrid[6][7] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(7),0,0,VISIBLE_STATE,6,7);
        tileGrid[7][6] = gm.createTypeCTile(gm.calculateTileXInGrid(7),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,7,6);
    }
    public void initTestFiveGrid()
    {
        //L SHAPE
        createNoMatchesGrid();
        //FIRST CONFIGURATION
        tileGrid[0][0] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(0),0,0,VISIBLE_STATE,0,0);
        tileGrid[0][1] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,0,1);
        tileGrid[0][3] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(3),0,0,VISIBLE_STATE,0,3);
        tileGrid[1][2] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,1,2);
        tileGrid[2][2] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,2,2);
        //SECOND CONFIGURATION
        tileGrid[4][2] = gm.createTypeCTile(gm.calculateTileXInGrid(4),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,4,2);
        tileGrid[5][2] = gm.createTypeCTile(gm.calculateTileXInGrid(5),gm.calculateTileYInGrid(2),0,0,VISIBLE_STATE,5,2);
        tileGrid[6][0] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(0),0,0,VISIBLE_STATE,6,0);
        tileGrid[6][1] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(1),0,0,VISIBLE_STATE,6,1);
        tileGrid[6][3] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(3),0,0,VISIBLE_STATE,6,3);
        //THIRD CONFIGURATION
        tileGrid[0][5] = gm.createTypeCTile(gm.calculateTileXInGrid(0),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,0,5);
        tileGrid[1][5] = gm.createTypeCTile(gm.calculateTileXInGrid(1),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,1,5);
        tileGrid[3][5] = gm.createTypeCTile(gm.calculateTileXInGrid(3),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,3,5);
        tileGrid[2][6] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,2,6);
        tileGrid[2][7] = gm.createTypeCTile(gm.calculateTileXInGrid(2),gm.calculateTileYInGrid(7),0,0,VISIBLE_STATE,2,7);
        //FOURTH CONFIGURATION
        tileGrid[5][5] = gm.createTypeCTile(gm.calculateTileXInGrid(5),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,5,5);
        tileGrid[7][5] = gm.createTypeCTile(gm.calculateTileXInGrid(7),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,7,5);
        tileGrid[8][5] = gm.createTypeCTile(gm.calculateTileXInGrid(8),gm.calculateTileYInGrid(5),0,0,VISIBLE_STATE,8,5);
        tileGrid[6][6] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(6),0,0,VISIBLE_STATE,6,6);
        tileGrid[6][7] = gm.createTypeCTile(gm.calculateTileXInGrid(6),gm.calculateTileYInGrid(7),0,0,VISIBLE_STATE,6,7);
    }
    public void createNoMovesGrid()
    {
        for(int j = 0;j < gridRows;j++){
            for(int i =0;i < gridColumns;i++){
                if(j % 2 == 0)//J IS EVEN,TILEA,TILEB,TILEC,ETC
                {
                    if(i % 3 == 0){
                        tileGrid[i][j] = gm.createTypeATile(gm.calculateTileXInGrid(i),gm.calculateTileYInGrid(j),0,0,VISIBLE_STATE,i,j);
                    }else if(i % 3 == 1){
                        tileGrid[i][j] = gm.createTypeBTile(gm.calculateTileXInGrid(i),gm.calculateTileYInGrid(j),0,0,VISIBLE_STATE,i,j);
                    }else if(i % 3 == 2){
                        tileGrid[i][j] = gm.createTypeCTile(gm.calculateTileXInGrid(i),gm.calculateTileYInGrid(j),0,0,VISIBLE_STATE,i,j);
                    }
                }else//J IS ODD,TILED,TILEE,TILEF,ETC
                {
                    if(i % 3 == 0){
                        tileGrid[i][j] = gm.createTypeDTile(gm.calculateTileXInGrid(i),gm.calculateTileYInGrid(j),0,0,VISIBLE_STATE,i,j);
                    }else if(i % 3 == 1){
                        tileGrid[i][j] = gm.createTypeETile(gm.calculateTileXInGrid(i),gm.calculateTileYInGrid(j),0,0,VISIBLE_STATE,i,j);
                    }else if(i % 3 == 2){
                        tileGrid[i][j] = gm.createTypeFTile(gm.calculateTileXInGrid(i),gm.calculateTileYInGrid(j),0,0,VISIBLE_STATE,i,j);
                    }
                }
            }
        }
    }
    public void scrollUp()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Sprite s = game.getGUIDecor().get(BACKGROUND_TYPE);
        if(((CrushSagaMiniGame)game).getCurrentScreenState().equals(SAGA_SCREEN_STATE) && s.getY() < 0)
        {
        float currentY = s.getY();
        s.setY(currentY + 10);
        
        ArrayList<String> levels = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_OPTIONS);
        for(String level: levels)
            {
            float levelY = game.getGUIButtons().get(level).getY();
            game.getGUIButtons().get(level).setY(levelY + 10);
            }
        }
    }
    public void scrollDown()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Sprite s = game.getGUIDecor().get(BACKGROUND_TYPE);
        if(((CrushSagaMiniGame)game).getCurrentScreenState().equals(SAGA_SCREEN_STATE) && s.getY() > SAGA_SCREEN_INITIAL_Y )
        {
        float currentY = s.getY();
        s.setY(currentY - 10);
        
        ArrayList<String> levels = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_OPTIONS);
        for(String level: levels)
            {
            float levelY = game.getGUIButtons().get(level).getY();
            game.getGUIButtons().get(level).setY(levelY - 10);
            }
        
        }
    }
    public void activateMallet(){
        isMallet = true;
    }
    public void deactivateMallet(){
        isMallet = false;
    }
    public void displayLevelDialog()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        game.getGUIDialogs().get(LEVEL_DIALOG_TYPE).setState(VISIBLE_STATE);
        //deactivate level buttons when level dialog is open
        ArrayList<String> levels = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_OPTIONS);
        for(String level: levels)
        {
            game.getGUIButtons().get(level).setEnabled(false);
        }
    }
    public void closeLevelDialog()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        game.getGUIDialogs().get(LEVEL_DIALOG_TYPE).setState(INVISIBLE_STATE);
        //disabled level buttons when level dialog is open
        ((CrushSagaMiniGame)game).enableLevelButtons();
    }
    //only called when switching between screens, just empty the stack and tile grid, when play level, generate new tiles
//    public void enableTiles(boolean enable)
//    {
//        tileGrid = new CrushSagaTile[gridColumns][gridRows];   
//    }
    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) 
    {
        int col = gm.calculateGridCellColumn(x);
        int row = gm.calculateGridCellRow(y);
        if(col < gridColumns && col >= 0 && row < gridRows && row >= 0 && tileGrid[col][row] != null)
            selectTile(tileGrid[col][row]);
        
    }
    @Override
    public void checkMouseDragOnSprites(MiniGame game,int x,int y)
    {
        int col = gm.calculateGridCellColumn(x);
        int row = gm.calculateGridCellRow(y);
        int changeInY = Math.abs(y - lastMouseY);//IF CHANGEINY > CHANGINX MOVE UP OR DOWN, ELSE MOVE RIGHT OR LEFT, REMEMBER NOT TO LEAVE GRID
        int changeInX = Math.abs(x - lastMouseX);
        int lowRow;
        int highRow;
        int rightCol;
        int leftCol;
        if(movingTiles.isEmpty() && col < gridColumns && col >= 0 && row < gridRows && row >= 0 && tileGrid[col][row] != null 
                && !tileGrid[gm.calculateGridCellColumn(lastMouseX)][gm.calculateGridCellRow(lastMouseY)].getState().equals(BLOCKED_STATE))//CHECK IF DRAG IS IN GRID
        {
            //LET TILE MOVE WITH MOUSE UNTIL MOVED MORE THAN IMAGE SIZE, THEN SWAP REGARDLESS OF LEGAL OR ILLEGAL MOVE
            //if(changeInY >= changeInX && y >= gm.calculateTileYInGrid(0) + TILE_IMAGE_HEIGHT/2 && y <= gm.calculateTileYInGrid(gridRows-1) + TILE_IMAGE_HEIGHT/2)//PREVENT TOP AND BOTTOM MOST TILES FROM EXITING GRID
            if(changeInY >= changeInX && y >= gm.calculateTileYInGrid(0) && y <= gm.calculateTileYInGrid(gridRows-1) + TILE_IMAGE_HEIGHT)//PREVENT TOP AND BOTTOM MOST TILES FROM EXITING GRID
            {//changeInY >= changeInX && y >= calculateTileYInGrid(0) + TILE_IMAGE_HEIGHT/2 && y <= calculateTileYInGrid(gridRows-1)-TILE_IMAGE_HEIGHT/2
                if(gm.calculateTileYInGrid(gm.calculateGridCellRow(lastMouseY)) + (y-lastMouseY) >= 0 && gm.calculateTileYInGrid(gm.calculateGridCellRow(lastMouseY)) + (y-lastMouseY) <= gm.calculateTileYInGrid(gridRows-1) && changeInY <= TILE_IMAGE_HEIGHT){
                gm.moveTile(tileGrid[col][gm.calculateGridCellRow(lastMouseY)],gm.calculateTileXInGrid(col),gm.calculateTileYInGrid(gm.calculateGridCellRow(lastMouseY)) + (y-lastMouseY),TILE_GRID_VELOCITY);
                }
                if(changeInY > TILE_IMAGE_HEIGHT){
                 //setStopDragging(true);
                    stopDragging = true;
                    if(y <= lastMouseY)//UP,SWAP WITH TOP TILE,ONLY SWAP IF NOT TOPMOST AND BOTTOM MOST TILE
                    {//tileGrid[col][row] = tileSelected    tileGrid[col][row+1] = selectedTile
                        highRow = gm.calculateGridCellRow(lastMouseY)-1;
                        lowRow = gm.calculateGridCellRow(lastMouseY);
                        if(tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_FIVE) || tileGrid[col][highRow].getTileType().equals(TILE_TYPE_FIVE)){
                            gm.executeTileTypeFive(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else if(tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_LT) || tileGrid[col][highRow].getTileType().equals(TILE_TYPE_LT)){
                            gm.executeTileTypeLT(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else if(tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL) || tileGrid[col][highRow].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL)){
                            gm.executeTileTypeFourHorizontal(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else if(tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_FOUR_VERTICAL) || tileGrid[col][highRow].getTileType().equals(TILE_TYPE_FOUR_VERTICAL)){
                            gm.executeTileTypeFourVertical(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else{//regular swap
                            //gm.executeRegularMove(tileGrid[col][row], tileGrid[col][row+1]);
                            gm.executeRegularMove(tileGrid[col][lowRow], tileGrid[col][highRow]);
                            tileGrid[col][lowRow].setState(VISIBLE_STATE);
                            tileGrid[col][highRow].setState(VISIBLE_STATE);
                            //selectedTile = null;
                         }
                    }else// if(y > lastMouseY)
                    {//DOWN,SWAP WITH BOTTOM TILE tileGrid[col][row-1] = selectedTile
                        highRow = gm.calculateGridCellRow(lastMouseY);
                        lowRow = gm.calculateGridCellRow(lastMouseY)+1;
                        if(tileGrid[col][highRow].getTileType().equals(TILE_TYPE_FIVE) || tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_FIVE)){
                            gm.executeTileTypeFive(tileGrid[col][highRow], tileGrid[col][lowRow]);
                        }else if(tileGrid[col][highRow].getTileType().equals(TILE_TYPE_LT) || tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_LT)){
                            gm.executeTileTypeLT(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else if(tileGrid[col][highRow].getTileType().equals(TILE_TYPE_FOUR_VERTICAL) || tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_FOUR_VERTICAL)){
                            gm.executeTileTypeFourVertical(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else if(tileGrid[col][highRow].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL) || tileGrid[col][lowRow].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL)){
                            gm.executeTileTypeFourHorizontal(tileGrid[col][lowRow], tileGrid[col][highRow]);
                        }else{
                            gm.executeRegularMove(tileGrid[col][highRow], tileGrid[col][lowRow]);
                            tileGrid[col][highRow].setState(VISIBLE_STATE);
                            tileGrid[col][lowRow].setState(VISIBLE_STATE);
                            //selectedTile = null;
                        }
                    }//THE REASON IT IS ROW-1 IS ONCE YOU'VE PAST THE MID POINT MARK, TILEGRID[COL][ROW]IS NOT THE ORIGINAL ONE YOU WERE MOVING
                    selectedTile = null;
                }
            }//else if(changeInX > changeInY && x >= gm.calculateTileXInGrid(0) + TILE_IMAGE_WIDTH/2 && x <= gm.calculateTileXInGrid(gridRows-1) + TILE_IMAGE_WIDTH/2)
            else if(changeInX > changeInY && x >= gm.calculateTileXInGrid(0) && x <= gm.calculateTileXInGrid(gridRows-1) + TILE_IMAGE_WIDTH)
            {
                
                //gm.moveTile(tileGrid[gm.calculateGridCellColumn(lastMouseX)][row],x - TILE_IMAGE_HEIGHT/2,gm.calculateTileYInGrid(row),TILE_GRID_VELOCITY);
                if(gm.calculateTileXInGrid(gm.calculateGridCellColumn(lastMouseX))+ x - lastMouseX >= gm.calculateTileXInGrid(0) && gm.calculateTileXInGrid(gm.calculateGridCellColumn(lastMouseX))+ x - lastMouseX <= gm.calculateTileXInGrid(gridColumns - 1))
                gm.moveTile(tileGrid[gm.calculateGridCellColumn(lastMouseX)][row],gm.calculateTileXInGrid(gm.calculateGridCellColumn(lastMouseX))+ x - lastMouseX,gm.calculateTileYInGrid(row),TILE_GRID_VELOCITY);
                if(changeInX > TILE_IMAGE_WIDTH){
                    //setStopDragging(true);
                    stopDragging = true;
                    if(x <= lastMouseX)
                    {
                        rightCol = gm.calculateGridCellColumn(lastMouseX);
                        leftCol = gm.calculateGridCellColumn(lastMouseX)-1;
                        if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_FIVE) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_FIVE)){
                            gm.executeTileTypeFive(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_LT) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_LT)){
                            gm.executeTileTypeLT(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_FOUR_VERTICAL) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_FOUR_VERTICAL)){
                            gm.executeTileTypeFourVertical(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL)){
                            gm.executeTileTypeFourHorizontal(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else{
                            gm.executeRegularMove(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                            tileGrid[leftCol][row].setState(VISIBLE_STATE);
                            tileGrid[rightCol][row].setState(VISIBLE_STATE);
                            //selectedTile = null;
                        }
                    }else// if(x > lastMouseX)
                    {
                        rightCol = gm.calculateGridCellColumn(lastMouseX)+1;
                        leftCol = gm.calculateGridCellColumn(lastMouseX);
                        if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_FIVE) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_FIVE)){
                            gm.executeTileTypeFive(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_LT) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_LT)){
                            gm.executeTileTypeLT(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_FOUR_VERTICAL) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_FOUR_VERTICAL)){
                            gm.executeTileTypeFourVertical(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else if(tileGrid[rightCol][row].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL) || tileGrid[leftCol][row].getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL)){
                            gm.executeTileTypeFourHorizontal(tileGrid[leftCol][row], tileGrid[rightCol][row]);
                        }else{
                            gm.executeRegularMove(tileGrid[rightCol][row], tileGrid[leftCol][row]);
                            tileGrid[leftCol][row].setState(VISIBLE_STATE);
                            tileGrid[rightCol][row].setState(VISIBLE_STATE);
                            //selectedTile = null;
                        }
                    }
                    selectedTile = null;
                }
            }
        }
    }
    //THIS METHOD CHANGES THE TILE TO SELECTED_STATE OR VISIBLE_STATE
    public void selectTile(CrushSagaTile tileSelected)
    {
        if(!tileSelected.getState().equals(BLOCKED_STATE) && movingTiles.isEmpty()){
            //CHECK IF MALLET IS ON,IF SO DELETE THE TILE ONLY IF IT IS NOT TYPE BOTTOM OR BLOCKED
            if(isMallet && !tileSelected.getTileType().equals(TILE_TYPE_BOTTOM)){
                //DELETE THE TILE SELECTED
                tileGrid[tileSelected.getGridColumn()][tileSelected.getGridRow()] = null;
                jellyGrid[tileSelected.getGridColumn()][tileSelected.getGridRow()] = null;
                gm.activateNumbersHorizontal(tileSelected.getGridColumn(), tileSelected.getGridRow(), 1, TILE_TYPE_THIRTY_POINTS);
                //MOVE TILES DOWN
                gm.moveAllTilesDown();
                //GENERATE NEW TILES
                gm.fillEmptyCells();
                movesRemaining--;
                //RESET MALLET
                deactivateMallet();
                return;
            }
                //NO TILE HAS BEEN SELECTED
                if(selectedTile == null)
                {
                    selectedTile = tileSelected;
                    tileSelected.setState(SELECTED_STATE);
                }else if(selectedTile == tileSelected)
                {
                    tileSelected.setState(VISIBLE_STATE);
                    selectedTile = null;
                }else if(XOR(tileSelected.getGridRow() == selectedTile.getGridRow(),tileSelected.getGridColumn() == selectedTile.getGridColumn())
                        && (tileSelected.getGridRow() == (selectedTile.getGridRow() -1) || 
                        tileSelected.getGridRow() == (selectedTile.getGridRow() + 1) ||
                        tileSelected.getGridColumn() == (selectedTile.getGridColumn() + 1) ||
                        tileSelected.getGridColumn() == (selectedTile.getGridColumn() - 1))){

                    //EXECUTE FIVE-IN-ROW SPECIAL TILE 
                    if(tileSelected.getTileType().equals(TILE_TYPE_FIVE) || selectedTile.getTileType().equals(TILE_TYPE_FIVE))
                    {
                        gm.executeTileTypeFive(tileSelected, selectedTile);
                    }else if(tileSelected.getTileType().equals(TILE_TYPE_LT) || selectedTile.getTileType().equals(TILE_TYPE_LT)){
                        //EXECUTE LT SPECIAL TILE
                        gm.executeTileTypeLT(tileSelected,selectedTile);
                    }else if(tileSelected.getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL) || selectedTile.getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL)){
                        gm.executeTileTypeFourHorizontal(tileSelected,selectedTile);
                    }else if(tileSelected.getTileType().equals(TILE_TYPE_FOUR_VERTICAL) || selectedTile.getTileType().equals(TILE_TYPE_FOUR_VERTICAL)){
                        gm.executeTileTypeFourVertical(tileSelected,selectedTile);
                    }else{
                        gm.executeRegularMove(tileSelected, selectedTile);
                    }
                    selectedTile.setState(VISIBLE_STATE);
                    selectedTile = null;
                }else{
                    selectedTile.setState(VISIBLE_STATE);
                    selectedTile = tileSelected;
                    tileSelected.setState(SELECTED_STATE);
                }
        }
    }
    public void endGameAsWin()
    {
        super.endGameAsWin();
        long gameTime = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        //IF GAME SCORE IS TWICE THE SCORE REQUIRED, REWARD 3 STARS,ELSE IF 1.5 TIMES SCORE REQUIRED REWARD 2 STARS,ETC
        if(gameScore >= scoreRequired * 1.4){
        ((CrushSagaMiniGame)game).getPlayerRecord().addWin(currentLevel, gameScore, 3, gameTime);
            game.getGUIDecor().get(STAR_ONE_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_TWO_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_THREE_TYPE).setState(FILLED_STATE);
        }else if(gameScore >= scoreRequired * 1.2){
            ((CrushSagaMiniGame)game).getPlayerRecord().addWin(currentLevel, gameScore, 2, gameTime);
            game.getGUIDecor().get(STAR_ONE_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_TWO_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_THREE_TYPE).setState(UNFILLED_STATE);
        }else if(gameScore >= scoreRequired){
            ((CrushSagaMiniGame)game).getPlayerRecord().addWin(currentLevel, gameScore, 1, gameTime);
            game.getGUIDecor().get(STAR_ONE_TYPE).setState(FILLED_STATE);
            game.getGUIDecor().get(STAR_TWO_TYPE).setState(UNFILLED_STATE);
            game.getGUIDecor().get(STAR_THREE_TYPE).setState(UNFILLED_STATE);
        }else{
            ((CrushSagaMiniGame)game).getPlayerRecord().addWin(currentLevel, gameScore, 0, gameTime);
        }
        ((CrushSagaMiniGame)game).savePlayerRecord();
        
        game.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(VISIBLE_STATE);
        timer.cancel();
        timer.purge();
    }
    public void endGameAsLoss()
    {
        super.endGameAsLoss();
        timer.cancel();
        timer.purge();
        //((MahjongSolitaireMiniGame)miniGame).getPlayerRecord().addLoss(currentLevel);
        //((MahjongSolitaireMiniGame)miniGame).savePlayerRecord();
        game.getGUIDialogs().get(LOSE_DIALOG_TYPE).setState(VISIBLE_STATE);
        game.getGUIButtons().get(TRY_AGAIN_BUTTON_TYPE).setState(VISIBLE_STATE);
        game.getGUIButtons().get(TRY_AGAIN_BUTTON_TYPE).setEnabled(true);
    }
    public void swapTiles(CrushSagaTile t1,CrushSagaTile t2)
    {
            int tempCol = t2.getGridColumn();
            int tempRow = t2.getGridRow();
            gm.moveTileWithinGrid(t2,t1.getGridColumn(),t1.getGridRow(),TILE_GRID_VELOCITY);
            gm.moveTileWithinGrid(t1,tempCol,tempRow,TILE_GRID_VELOCITY);
    }
    public boolean XOR(boolean x,boolean y)
    {
        if((x && !y) || (!x && y))
            return true;
        return false;
    }
    //getter methods
    public GridMethods getGridMethods(){return gm;}
    public int getGridColumns(){return gridColumns;}
    public int getGridRows(){return gridRows;}
    public int getScoreRequired(){return scoreRequired;}
    public int getStarsEarned(){return starsEarned;}
    public int getGameScore(){return gameScore;}
    public MiniGame getMiniGame(){return game;}
    public int getMovesRemaining(){return movesRemaining;}
    public int getNumTilesBottom(){return numTilesBottom;}
    public boolean getMallet(){return isMallet;}
    public void addToGameScore(int num){gameScore += num;}
    public String getCurrentLevel(){return currentLevel;}
    public SpriteType getTileASpriteType(){return tileASpriteType;}
    public SpriteType getTileBSpriteType(){return tileBSpriteType;}
    public SpriteType getTileCSpriteType(){return tileCSpriteType;}
    public SpriteType getTileDSpriteType(){return tileDSpriteType;}
    public SpriteType getTileESpriteType(){return tileESpriteType;}
    public SpriteType getTileFSpriteType(){return tileFSpriteType;}
    public SpriteType getTileBottomSpriteType(){return tileBottomSpriteType;}
    public SpriteType getTileBlockedSpriteType(){return tileBlockedSpriteType;}
    public SpriteType getTileFourInRowHorizontalSpriteType(){return tileFourInRowHorizontalSpriteType;}
    public SpriteType getTileFourInRowVerticalSpriteType(){return tileFourInRowVerticalSpriteType;}
    public SpriteType getTileFiveInRowSpriteType(){return tileFiveInRowSpriteType;}
    public SpriteType getTileLTSpriteType(){return tileLTSpriteType;}
    public SpriteType getTileThirtyPointsSpriteType(){return thirtyPointsSpriteType;}
    public SpriteType getTileTwentyFivePointsSpriteType(){return twentyFivePointsSpriteType;}
    public SpriteType getTileTwentyPointsSpriteType(){return twentyPointsSpriteType;}
    public SpriteType getJellySpriteType(){return jellySpriteType;}
    public CrushSagaTile [][] getTileGrid(){return tileGrid;}
    public CrushSagaTile[][] getJellyGrid(){return jellyGrid;}
    public Iterator<CrushSagaTile> getMovingTiles(){return movingTiles.iterator();}
    public boolean getInitializingGrid(){return initializingGrid;}
    public String getLevelName(){return levelName;}
        
    
    //setter methods
    public void setSwapBack(boolean b){
        swapBack = b;
    }
    public void setTileToBeSwappedOne(CrushSagaTile t){
        tileToBeSwappedOne = t;
    }
    public void setTileToBeSwappedTwo(CrushSagaTile t){
        tileToBeSwappedTwo = t;
    }
    public void setDeleteSurroundings(boolean b){
        deleteSurroundings = b;
    }
    public void setDeleteThisTileSurroundings(CrushSagaTile t){
        deleteThisTileSurroundings = t;
    }
    public void setDeleteVertically(boolean b){
        deleteVertically = true;
    }
    public void setDeleteThisTilesColumn(CrushSagaTile t){
        deleteThisTilesColumn = t;
    }
    public void setDeleteHorizontally(boolean b){
        deleteHorizontally = true;
    }
    public void setDeleteThisTilesRow(CrushSagaTile t){
        deleteThisTilesRow = t;
    }
    public void setLevelName(String l){levelName = l;}
    public void setTotalMoves(int m){totalMoves = m;}
    public void setMovesRemaining(int moves){movesRemaining = moves;}
    public void setGameScore(int score){gameScore = score;}
    public void setScoreRequired(int req){scoreRequired = req;}
    public void setNumTilesBottom(int num){numTilesBottom = num;}
    public void setStarsEarned(int stars){starsEarned = stars;}
    public void setMallet(boolean b){isMallet = b;}
    public void setGridColumns(int columns){gridColumns = columns;}
    public void setGridRows(int rows){gridRows = rows;}
    public void setCurrentLevel(String level){currentLevel = level;}
    public void setInitializngGrid(boolean b){initializingGrid = b;}
    public void subtractOneMovesRemaining(){movesRemaining--;}
    public void addOneMovesRemaining(){movesRemaining++;}
    public void putTilesInPlace(){
        if(inProgress() && !stopDragging){
            
            for(int i = 0;i < gridColumns;i++)
            {
                for(int j = 0;j < gridRows;j++)
                {
                    //DO NOT PUT TILES BACK IN PLACE IF STILL DRAGGING, IF STOPDRAGGING=FALSE, PLAYER 
                    if(tileGrid[i][j] != null && (tileGrid[i][j].getX() != gm.calculateTileXInGrid(i) || tileGrid[i][j].getY() != gm.calculateTileYInGrid(j)) )
                    {
                        gm.moveTileWithinGrid(tileGrid[i][j],i,j,TILE_GRID_VELOCITY);
                        if(selectedTile != null){
                        selectedTile.setState(VISIBLE_STATE);
                        selectedTile = null;
                        }
                    }
                }
            }
        }
    }
    /**
     * This method creates and returns a textual description of
     * the timeInMillis argument as a time duration in the format
     * of (H:MM:SS).
     * 
     * @param timeInMillis The time to be represented textually.
     * 
     * @return A textual representation of timeInMillis.
     */
    public String timeToText(long timeInMillis)
    {
        // FIRST CALCULATE THE NUMBER OF HOURS,
        // SECONDS, AND MINUTES
        long hours = timeInMillis/MILLIS_IN_AN_HOUR;
        timeInMillis -= hours * MILLIS_IN_AN_HOUR;        
        long minutes = timeInMillis/MILLIS_IN_A_MINUTE;
        timeInMillis -= minutes * MILLIS_IN_A_MINUTE;
        long seconds = timeInMillis/MILLIS_IN_A_SECOND;
              
        // THEN ADD THE TIME OF GAME SUMMARIZED IN PARENTHESES
        String minutesText = "" + minutes;
        if (minutes < 10)   minutesText = "0" + minutesText;
        String secondsText = "" + seconds;
        if (seconds < 10)   secondsText = "0" + secondsText;
        return hours + ":" + minutesText + ":" + secondsText;
    }
    /**
     * This method builds and returns a textual representation of
     * the game time. Note that the game may still be in progress.
     * 
     * @return The duration of the current game represented textually.
     */
    public String gameTimeToText()
    {
        // CALCULATE GAME TIME USING HOURS : MINUTES : SECONDS
        if ((startTime == null) || (endTime == null))
            return "";
        long timeInMillis = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        return timeToText(timeInMillis);
    }
    /*
     * Called when a new game is startefd
     */
    @Override
    public void reset(MiniGame miniGame) {
        selectedTile = null;
        beginGame();
        initTiles();//initiate random tiles again
        //reset time and score
        startTime = new GregorianCalendar();
        gameScore = 0;
        movesRemaining = totalMoves;
        //CONSTANTLY CHECKS IF ANY MOVES LEFT AND DELETES MATCHES
        timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                    
                    if(!isRunning1){
                        isRunning1 = true;
                        if(movingTiles.isEmpty())
                        {
                            //PUT TILES BACK IN ORIGINAL POSITION WHEN DRAGGED OUT OF PLACE
                            putTilesInPlace();
                            //DELETE MATCHES
                            if(gm.deleteMatches()){
                                gm.moveAllTilesDown();
                                gm.fillEmptyCells();
                            }
                            //IF IN PROGRESS, NO MORE MATCHES,MOVES AND SCORE LESS THAN REQURIED,END AS LOSS
                            if(inProgress() && !gm.hasMatches() && movesRemaining == 0 && (gameScore < scoreRequired || gm.numTilesBottom() < numTilesBottom || gm.jellyLeft()))
                            {
                                endGameAsLoss();
                            }else if(inProgress() && gameScore >= scoreRequired && movesRemaining == 0 && gm.numTilesBottom() == numTilesBottom && !gm.jellyLeft())
                            {
                                endGameAsWin();
                            }
                        }
                        isRunning1 = false;
            }
                }
            }, 0, 400);
            //CHECK IF ANY MOVES REMAINING
            timer.scheduleAtFixedRate(new TimerTask(){
                public void run(){
                  if(!isRunning1 && !isRunning2){
                      isRunning2 = true;
                        if(movingTiles.size() == 0 && inProgress() && !gm.movesRemaining()){
                            endGameAsLoss();
                        }
                        isRunning2 = false;
                  }
                    
                }
            },0,1000);
            //UPDATES PROGRESS BAR
            timer.scheduleAtFixedRate(new TimerTask(){
                public void run(){
                    if(!isRunning1 && !isRunning2 && !isRunning3){
                        isRunning3 = true;
                        if(((CrushSagaMiniGame)game).getCurrentScreenState().equals(GAME_SCREEN_STATE)){
                                if(gameScore <= scoreRequired*0.1)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_ONE_STATE);
                                else if(gameScore <= scoreRequired*0.2)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_TWO_STATE);
                                else if(gameScore <= scoreRequired*0.3)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_THREE_STATE);
                                else if(gameScore <= scoreRequired*0.4)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_FOUR_STATE);
                                else if(gameScore <= scoreRequired*0.5)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_FIVE_STATE);
                                else if(gameScore <= scoreRequired*0.6)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_SIX_STATE);
                                else if(gameScore <= scoreRequired*0.7)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_SEVEN_STATE);
                                else if(gameScore <= scoreRequired*0.8)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_EIGHT_STATE);
                                else if(gameScore <= scoreRequired*0.9)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_NINE_STATE);
                                else if(gameScore >= scoreRequired)
                                    game.getGUIDecor().get(PROGRESS_BAR_TYPE).setState(PROGRESS_TEN_STATE);
                        }
                        isRunning3 = false;
                    }
                }
            },0,100);
            //THIS TIMER TAKES CARE OF SWAPPING BACK NONMATCHING TILES
            timer.scheduleAtFixedRate(new TimerTask(){
                public void run(){
                    if(!isRunning1 && !isRunning2 && !isRunning3 && !isRunning4){
                        isRunning4 = true;
                    
                        if(movingTiles.isEmpty() && swapBack)
                        {
                            int i1 = tileToBeSwappedOne.getGridColumn();
                            int j1 = tileToBeSwappedOne.getGridRow();
                            int i2 = tileToBeSwappedTwo.getGridColumn();
                            int j2 = tileToBeSwappedTwo.getGridRow();
                            gm.swapTiles(tileGrid[i1][j1], tileGrid[i2][j2]);
                            swapBack = false;
                        }
                        if(movingTiles.isEmpty() && deleteSurroundings){
                            gm.deleteSurroundingTiles(deleteThisTileSurroundings);
                            gm.moveAllTilesDown();
                            gm.fillEmptyCells();
                            deleteSurroundings = false;
                        }
                        if(movingTiles.isEmpty() && deleteVertically){
                            gm.deleteEntireColumn(deleteThisTilesColumn);
                            gm.moveAllTilesDown();
                            gm.fillEmptyCells();
                            deleteVertically = false;
                        }
                        if(movingTiles.isEmpty() && deleteHorizontally){
                            gm.deleteEntireRow(deleteThisTilesRow);
                            gm.moveAllTilesDown();
                            gm.fillEmptyCells();
                            deleteHorizontally = false;
                        }
                        isRunning4 = false;
                }
                }
            }, 0, 100);
            
    }
    /**
     * Called each frame, this method updates all the game objects.
     * 
     * @param game The game to be updated.
     */
    @Override
    public void updateAll(MiniGame game) {
     
        try{
            game.beginUsingData();
            //if game is still in progress, keep track of time
            if(inProgress())
            endTime = new GregorianCalendar();
            for(int i = 0;i < movingTiles.size();i++)
            {
                CrushSagaTile tile = movingTiles.get(i);
                tile.update(game);
                
                if(!tile.isMovingToTarget())
                {
                    movingTiles.remove(tile);
                }   
            }
        }finally{
            game.endUsingData();
        }
        
    }

    @Override
    public void updateDebugText(MiniGame game) {
     
    }
    
}