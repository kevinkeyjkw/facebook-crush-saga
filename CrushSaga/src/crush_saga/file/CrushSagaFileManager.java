/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.file;

import crush_saga.data.CrushSagaDataModel;
import crush_saga.data.CrushSagaLevelRecord;
import crush_saga.data.CrushSagaRecord;
import crush_saga.ui.CrushSagaMiniGame;
import crush_saga_main.CrushSaga;
import crush_saga_main.CrushSaga.CrushSagaPropertyType;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kevin
 */
public class CrushSagaFileManager {
    private CrushSagaMiniGame game;
    private int[] blockedTilePos;
    private int[] bottomTilePos;
    private int[] jellyTilesPos;
    public CrushSagaFileManager(CrushSagaMiniGame miniGame)
    {
        game = miniGame;
    }
    public void loadLevel(String levelFile){
        /*
         * FORMAT OF LEVEL FILE IS
         * SCORE REQUIRED
         * MOVES REMAINING
         * BOTTOM TILE ARRAY SIZE
         * POSITION OF BOTTOM TILE I
         * POSITION OF BOTTOM TILE J
         * ETC
         * BLOCKED TILE ARRAY SIZE
         * POSITION OF BLOCKED TILE I
         * POSITION OF BLOCKED TILE J
         * JELLY TILE ARRAY SIZE
         * POSITION OF JELLY TILE I
         * POSITION OF JELLY TILE J
         * ETC
         */
        try{
            File fileToOpen = new File(levelFile);
            
            FileReader inputFile = new FileReader(fileToOpen);
            BufferedReader br = new BufferedReader(inputFile);
            //FIRST THING READ FROM LEVEL FILE IS SCORE REQUIRED TO PASS THE LEVEL
            //((CrushSagaDataModel)game.getDataModel()).setCurrentLevel(levelFile);
            
            String scoreRequired = br.readLine();
            String movesRemaining = br.readLine();
            //BOTTOM TILES
            String numBottomTiles = br.readLine();
            //NUMBOTTOMTILES WONT BE NULL BECAUSE IT WILL ALWAYS HAVE A NUMBER, 0 IF NO BOTTOM TILES
                //NUM BOTTOM TILES NEEDED IS BOTTOM TILE POSITION ARRAY SIZE DIVIDED BY 2
                int bottomTileArraySize = Integer.parseInt(numBottomTiles);
                bottomTilePos = new int[bottomTileArraySize];
                ((CrushSagaDataModel)game.getDataModel()).setNumTilesBottom(bottomTileArraySize/2);
                
                int count = bottomTileArraySize;
                int counter = 0;
                String col,row;
                int i,j;
                
                while(count > 0){
                    col = br.readLine();
                    row = br.readLine();
                    i = Integer.parseInt(col);
                    j = Integer.parseInt(row);
                    bottomTilePos[counter++] = i;
                    bottomTilePos[counter++] = j;
                    count-=2;
                }
            
            //BLOCKED TILES
                String numBlockedTiles = br.readLine();
                int blockedTilesArraySize = Integer.parseInt(numBlockedTiles);
                blockedTilePos = new int[blockedTilesArraySize];
                count = blockedTilesArraySize;
                counter = 0;
                
                while(count > 0){
                    //READ WHICH I,J OF TILE GRID SHOULD HAVE BLOCKED TILES
                    col = br.readLine();
                    row = br.readLine();
                    i = Integer.parseInt(col);
                    j = Integer.parseInt(row);
                    blockedTilePos[counter++] = i;
                    blockedTilePos[counter++] = j;
                    count-=2;
                }
                
                String numJellyTiles = br.readLine();
                int jellyTilesArraySize = Integer.parseInt(numJellyTiles);
                jellyTilesPos = new int[jellyTilesArraySize];
                count = jellyTilesArraySize;
                counter=0;
                while(count>0){
                    col = br.readLine();
                    row = br.readLine();
                    i = Integer.parseInt(col);
                    j = Integer.parseInt(row);
                    jellyTilesPos[counter++] = i;
                    jellyTilesPos[counter++] = j;
                    count-=2;
                }
                
            
            if(scoreRequired != null)
            ((CrushSagaDataModel)game.getDataModel()).setScoreRequired(Integer.parseInt(scoreRequired));
            if(movesRemaining != null){
            ((CrushSagaDataModel)game.getDataModel()).setTotalMoves(Integer.parseInt(movesRemaining));
            ((CrushSagaDataModel)game.getDataModel()).setMovesRemaining(Integer.parseInt(movesRemaining));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void saveRecord(CrushSagaRecord recordToSave){
        try{
            CrushSagaDataModel data = ((CrushSagaDataModel)game.getDataModel());
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String dataPath = props.getProperty(CrushSagaPropertyType.DATA_PATH);
            String recordPath = dataPath + props.getProperty(CrushSagaPropertyType.RECORD_FILE_NAME);
            File fileToWrite = new File(recordPath);
            File f = new File(recordPath);
             FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            
            if(fileToWrite.exists()){
                fileToWrite.delete();
                f.createNewFile();
            }
            HashMap <String, CrushSagaLevelRecord> levelRecords = recordToSave.getLevelRecords();
            ArrayList<String> levels = props.getPropertyOptionsList(CrushSagaPropertyType.LEVEL_OPTIONS);
            bw.write(String.valueOf(levelRecords.size()));//NUMOFLEVELS
            bw.newLine();
            for(int i = 0;i < levelRecords.size();i++){
                    bw.write(levels.get(i));//LEVEL PATH
                    bw.newLine();
                    bw.write(String.valueOf(recordToSave.getHighScore(levels.get(i))));
                    bw.newLine();
                    bw.write(String.valueOf(recordToSave.getStarsEarned(levels.get(i))));
                    bw.newLine();
                    bw.write(String.valueOf(recordToSave.getFastestTime(levels.get(i))));
                    bw.newLine();
                    //CONVERT LONG TO STRING, THEN CONVERT STRING TO INT
            }//FORMAT OF RECORD: NUMOFLEVELS,LEVELONE,HIGHSCORE,STARSEARNED,TOTALTIME
            bw.close();
            //File.create()
            //EVERYTIME YOU SAVE, DELETE OLD FILE, WHEN LOAD FILE, IF NO FILE EXISTS DO NOTHING
            //WHEN SAVING, NO MATTER IF FILE EXISTS OR NOT, USE FILE.CREATE(),AND SAVE THE RECORD. DON'T USE
            //BYTEARRAY, USE TXT AND BUFFEREDWRITER,FILEWRITER,BUFFEREDREADER,FILEREADER, USE TWO FOR LOOPS
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }
    public CrushSagaRecord loadRecord(){
        CrushSagaRecord recordToLoad = new CrushSagaRecord();
        try{
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String dataPath = props.getProperty(CrushSagaPropertyType.DATA_PATH);
            String recordPath = dataPath + props.getProperty(CrushSagaPropertyType.RECORD_FILE_NAME);
            File fileToOpen = new File(recordPath);
            /*
             *String numOflevels = buffer.readLine()
             */
            if(fileToOpen.exists()){
            FileReader fr = new FileReader(fileToOpen);
            BufferedReader br = new BufferedReader(fr);
            br.mark(10);
            int numLevels = 0;
            if(br.readLine()!= null){
                br.reset();
                numLevels = Integer.parseInt(br.readLine());
            }
                for(int i = 0;i < numLevels;i++){
                String levelName = br.readLine();
                CrushSagaLevelRecord levelRecord = new CrushSagaLevelRecord();
                levelRecord.highScore = Integer.parseInt(br.readLine());
                levelRecord.starsEarned = Integer.parseInt(br.readLine());
                levelRecord.fastestTime = Integer.parseInt(br.readLine());
                recordToLoad.addCrushSagaLevelRecord(levelName, levelRecord);
                }
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return recordToLoad;
    }
    public int[] getBlockedTilePos(){
        return blockedTilePos;
    }
    public int[] getBottomTilePos(){
        return bottomTilePos;
    }
    public int[] getJellyTilesPos(){
        return jellyTilesPos;
    }
}
