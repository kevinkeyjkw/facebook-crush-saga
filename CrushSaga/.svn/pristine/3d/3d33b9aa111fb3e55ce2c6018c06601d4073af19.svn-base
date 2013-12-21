/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.data;

import crush_saga.ui.CrushSagaTile;
import crush_saga_main.CrushSaga;
import static crush_saga_main.CrushSagaConstants.*;
import static crush_saga.data.CrushSagaDataModel.*;
import java.util.Timer;
import properties_manager.PropertiesManager;
/**
 *
 * @author Kevin
 */
public class GridMethods {
    CrushSagaDataModel data;
    
  //  CrushSagaTile tileGrid[][];
    int gridRows,gridColumns;
    public GridMethods(CrushSagaDataModel d){
        data = d;
        //tileGrid = data.getTileGrid();
        gridRows = data.getGridRows();
        gridColumns = data.getGridColumns();
    }
    public boolean deleteMatches()
    {
        if(data.inProgress()){
        //CHECK GRID FOR 3XMATCHES,4XMATCHES,5XMATCHES,ONLY VERTICAL OR HORIZONTAL!
        //CHECK 5 IN A ROW FIRST, THEN 4 IN A ROW, THEN FINALLY 3 IN A ROW
        //CHECK VERTICALLY THEN HORIZONTALLY
        for(int i = 0;i < gridColumns;i++){//initiated as such: tileGrid[gridColumns][gridRows]
            for(int j = 0;j < gridRows;j++){
                
                if(tileGrid[i][j] != null && !tileGrid[i][j].getState().equals(BLOCKED_STATE) && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM)){//ONLY IF NOT BLOCKED TILE
                            if(deleteFiveInRowVertical(i,j)){
                                data.addToGameScore(150);
                                return true;
                            }else if(deleteFourInRowVertical(i,j))
                            {//4 IN A ROW
                                data.addToGameScore(100);
                                return true;
                            }else if(j < gridRows - 2 && tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType()) 
                                    && tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType())
                                    && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BLOCKED))
                            {//3 IN A ROW VERTICALLY
                                if(deleteNorthEastL(i,j))
                                {
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteSouthEastL(i,j))
                                { 
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteSouthWestL(i,j))
                                { 
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteNorthWestL(i,j))
                                { 
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteNorthT(i,j))
                                {
                                    initializeLTTile(i,j);
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteEastT(i,j))
                                {
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteSouthT(i,j))
                                { 
                                    data.addToGameScore(100);
                                    return true;
                                }else if(deleteWestT(i,j))
                                {
                                    data.addToGameScore(100);
                                    return true;
                                }else
                                {//ONLY THREE IN A ROW
                                    data.addToGameScore(60);
                                    tileGrid[i][j] = null;
                                    tileGrid[i][j+1] = null;
                                    tileGrid[i][j+2] = null;
                                    if(!data.getInitializingGrid()){
                                    jellyGrid[i][j] = null;
                                    jellyGrid[i][j+1] = null;
                                    jellyGrid[i][j+2] = null;
                                    }
                                activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                                return true;
                                }
                            }//FINISHED DELETING MATCHES VERTICALLY
                }   
            }
        }
        //NOW DELETE MATCHES HORIZONTALLY
        for(int j = 0;j < gridRows;j++){
            for(int i = 0;i < gridColumns;i++){
                if(tileGrid[i][j] != null && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BLOCKED) && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM)){//ONLY IF NOT BLOCKED TILE
                        if(deleteFiveInRowHorizontal(i,j))
                        {//IF 5 IN A ROW
                            data.addToGameScore(150);
                            return true;
                        }else if(deleteFourInRowHorizontal(i,j))
                        {//4 IN A ROW
                            data.addToGameScore(100);
                            return true;
                        }else if(deleteThreeInRowHorizontal(i,j))
                        {//3 IN A ROW 
                            data.addToGameScore(60);
                            return true;
                        }
                }
            }
        }
        }
        return false;
    }
    public boolean deleteNorthT(int i,int j){
        //%%%
        // %
        // %
        if(i > 0 && i < gridColumns - 1 && tileGrid[i-1][j] != null && tileGrid[i+1][j] != null
                    && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j].getTileType())
                    && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i-1][j] = null;
                        tileGrid[i+1][j] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i-1][j] = null;
                        jellyGrid[i+1][j] = null;
                        }
                        activateNumbersHorizontal(i-1,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersVertical(i,j+1,2,TILE_TYPE_TWENTY_POINTS);
                        
                        return true;
                    }
                    return false;
    }
    public boolean deleteEastT(int i,int j){
        //  %
        //%%%
        //  %
        if(i > 1 && tileGrid[i-1][j+1] != null && tileGrid[i-2][j+1] != null 
                    && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+1].getTileType())
                    && tileGrid[i][j].getTileType().equals(tileGrid[i-2][j+1].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i-1][j+1] = null;
                        tileGrid[i-2][j+1] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i-1][j+1] = null;
                        jellyGrid[i-2][j+1] = null;
                        }
                        activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersHorizontal(i-2,j+1,2,TILE_TYPE_TWENTY_POINTS);
                        
                        //ADD SPECIAL TILE
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j+1)),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j+1),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j+1);
                        tileGrid[i][j+1] = specialTile;
                        return true;
                    }
            return false;
    }
    public boolean deleteSouthT(int i,int j){
        // %
        // %
        //%%%
        if(i > 0 && i < gridColumns - 1 && tileGrid[i+1][j+2]!= null && tileGrid[i-1][j+2] != null
                    && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+2].getTileType())
                    && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+2].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i+1][j+2] = null;
                        tileGrid[i-1][j+2] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i+1][j+2] = null;
                        jellyGrid[i-1][j+2] = null;
                        }
                        activateNumbersHorizontal(i-1,j+2,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersVertical(i,j,2,TILE_TYPE_TWENTY_POINTS);
                        
                        //ADD SPECIAL TILE
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j+2)),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j+2),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j+2);
                        tileGrid[i][j+2] = specialTile;
                        return true;
                    }
                    return false;
    }
    public boolean deleteWestT(int i,int j){
        //%
        //%%%
        //%
        if(i < gridColumns - 2 && tileGrid[i+1][j+1] != null && tileGrid[i+2][j+1] != null
                            && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+1].getTileType())
                            && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j+1].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i+1][j+1] = null;
                        tileGrid[i+2][j+1] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i+1][j+1] = null;
                        jellyGrid[i+2][j+1] = null;
                        }
                        activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersHorizontal(i+1,j+1,2,TILE_TYPE_TWENTY_POINTS);
                        
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j+1)),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j+1),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j+1);
                        tileGrid[i][j+1] = specialTile;
                        return true;
                    }
                    return false;
    }
    public boolean deleteNorthEastL(int i,int j){
        //%%%
        //  %
        //  %
        if(i>= 2 && tileGrid[i-1][j] != null && tileGrid[i-2][j] != null 
                            && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j].getTileType())
                            && tileGrid[i][j].getTileType().equals(tileGrid[i-2][j].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i-1][j] = null;
                        tileGrid[i-2][j] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i-1][j] = null;
                        jellyGrid[i-2][j] = null;
                        }
                        activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersHorizontal(i-2,j,2,TILE_TYPE_TWENTY_POINTS);
                        
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*j),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j);
                        tileGrid[i][j] = specialTile;
                        return true;
                    }
            return false;
    }
    public boolean deleteSouthEastL(int i,int j){
        //  %
        //  %
        //%%%
        if(i >= 2 && tileGrid[i-1][j+2] != null && tileGrid[i-2][j+2] != null 
                            && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+2].getTileType())
                            && tileGrid[i][j].getTileType().equals(tileGrid[i-2][j+2].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i-1][j+2] = null;
                        tileGrid[i-2][j+2] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i-1][j+2] = null;
                        jellyGrid[i-2][j+2] = null;
                        }
                        activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersHorizontal(i-2,j+2,2,TILE_TYPE_TWENTY_POINTS);
                        
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j+2)),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j+2),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j+2);
                        tileGrid[i][j+2] = specialTile;
                     return true;   
                    }
            return false;
    }
    public boolean deleteSouthWestL(int i,int j){
        //%
        //%
        //%%%
        if(i <= gridRows - 3 && tileGrid[i+1][j+2]!= null && tileGrid[i+2][j+2] != null 
                && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+2].getTileType()) 
                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j+2].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i+1][j+2] = null;
                        tileGrid[i+2][j+2] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i+1][j+2] = null;
                        jellyGrid[i+2][j+2] = null;
                        }
                        activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersHorizontal(i+1,j+2,2,TILE_TYPE_TWENTY_POINTS);
                        
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j+2)),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j+2),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j+2);
                        tileGrid[i][j+2] = specialTile;
                        return true;
                    }
                    return false;
    }
    public boolean deleteNorthWestL(int i,int j){
        //%%%
        //%
        //%
        if(i <= gridRows - 3 && tileGrid[i+1][j] != null && tileGrid[i+2][j] != null 
                            && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType())
                            && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType()))
                    {
                        tileGrid[i][j] = null;
                        tileGrid[i][j+1] = null;
                        tileGrid[i][j+2] = null;
                        tileGrid[i+1][j] = null;
                        tileGrid[i+2][j] = null;
                        if(!data.getInitializingGrid()){
                        jellyGrid[i][j] = null;
                        jellyGrid[i][j+1] = null;
                        jellyGrid[i][j+2] = null;
                        jellyGrid[i+1][j] = null;
                        jellyGrid[i+2][j] = null;
                        }
                        activateNumbersVertical(i,j,3,TILE_TYPE_TWENTY_POINTS);
                        activateNumbersHorizontal(i+1,j,2,TILE_TYPE_TWENTY_POINTS);
                        
                        CrushSagaTile specialTile;
                        if(data.getInitializingGrid()){
                        specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*j),0,0,VISIBLE_STATE);
                        }else{
                            specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j),0,0,VISIBLE_STATE);
                        }
                        specialTile.setGridCell(i,j);
                        tileGrid[i][j] = specialTile;
                        return true;
                    }
                    return false;
    }
    public boolean deleteFiveInRowHorizontal(int i,int j){
        if(i < gridColumns - 4 && tileGrid[i+1][j] != null && tileGrid[i+2][j] != null && tileGrid[i+3][j] != null && tileGrid[i+4][j] != null
                        &&tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType()) 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+3][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+4][j].getTileType()))
                {//IF 5 IN A ROW
                    //DELETE THOSE TILES
                    tileGrid[i][j] = null;
                    tileGrid[i+1][j] = null;
                    tileGrid[i+2][j] = null;
                    tileGrid[i+3][j] = null;
                    tileGrid[i+4][j] = null;
                    if(!data.getInitializingGrid()){
                    jellyGrid[i][j] = null;
                    jellyGrid[i+1][j] = null;
                    jellyGrid[i+2][j] = null;
                    jellyGrid[i+3][j] = null;
                    jellyGrid[i+4][j] = null;
                    }
                    //MAKE NUMBERS APPEAR
                    activateNumbersHorizontal(i,j,5,TILE_TYPE_THIRTY_POINTS);
                    
                    CrushSagaTile specialTile;
                    if(data.getInitializingGrid()){
                    specialTile = createTypeFiveTile(calculateTileXInGrid(i+2),unassignedTilesY + (TILE_IMAGE_HEIGHT*j),0,0,VISIBLE_STATE);
                    }else{
                        specialTile = createTypeFiveTile(calculateTileXInGrid(i+2),calculateTileYInGrid(j),0,0,VISIBLE_STATE);
                    }
                    specialTile.setGridCell(i+2,j);
                    tileGrid[i+2][j] = specialTile;
                       return true;
                    }
        return false;
    }
    public boolean deleteFiveInRowVertical(int i,int j){
        if(j < gridRows - 4 && tileGrid[i][j+1] != null && tileGrid[i][j+2] != null && tileGrid[i][j+3] != null && tileGrid[i][j+4] != null
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType()) 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+3].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+4].getTileType()))
        {
                    tileGrid[i][j] = null;
                    tileGrid[i][j+1] = null;
                    tileGrid[i][j+2] = null;
                    tileGrid[i][j+3] = null;
                    tileGrid[i][j+4] = null;
                    if(!data.getInitializingGrid()){
                    jellyGrid[i][j] = null;
                    jellyGrid[i][j+1] = null;
                    jellyGrid[i][j+2] = null;
                    jellyGrid[i][j+3] = null;
                    jellyGrid[i][j+4] = null;
                    }
                    
                    activateNumbersVertical(i,j,5,TILE_TYPE_THIRTY_POINTS);
                    
                    //MAKE MIDDLE ELEMENT A SPECIAL TILE
                    CrushSagaTile specialTile;
                    if(data.getInitializingGrid()){
                    specialTile = createTypeFiveTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j)),0,0,VISIBLE_STATE);
                    }else{
                        specialTile = createTypeFiveTile(calculateTileXInGrid(i),calculateTileYInGrid(j),0,0,VISIBLE_STATE);
                    }
                    specialTile.setGridCell(i,j);
                    tileGrid[i][j] = specialTile;
                     return true;
        }
        return false;   
    }
    public boolean deleteFourInRowHorizontal(int i,int j){
        if(i < gridRows - 3 && tileGrid[i+1][j] != null && tileGrid[i+2][j] != null && tileGrid[i+3][j] != null 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType()) 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+3][j].getTileType()))
                {//4 IN A ROW
                    tileGrid[i][j] = null;
                    tileGrid[i+1][j] = null;
                    tileGrid[i+2][j] = null;
                    tileGrid[i+3][j] = null;
                    if(!data.getInitializingGrid()){
                    jellyGrid[i][j] = null;
                    jellyGrid[i+1][j] = null;
                    jellyGrid[i+2][j] = null;
                    jellyGrid[i+3][j] = null;
                    }
                    
                    activateNumbersHorizontal(i,j,4,TILE_TYPE_TWENTY_FIVE_POINTS);
                    
                    CrushSagaTile specialTile;
                    if(data.getInitializingGrid()){
                        specialTile = createTypeFourHorizontalTile(calculateTileXInGrid(i+1),unassignedTilesY + (TILE_IMAGE_HEIGHT*j),0,0,VISIBLE_STATE);
                    }else{
                        specialTile = createTypeFourHorizontalTile(calculateTileXInGrid(i+1),calculateTileYInGrid(j),0,0,VISIBLE_STATE);
                    }
                    specialTile.setGridCell(i+1,j);
                    tileGrid[i+1][j] = specialTile;
                    return true;
                }
                return false;
    }
    public boolean deleteFourInRowVertical(int i,int j){
        if(j < gridRows - 3 && tileGrid[i][j+1] != null && tileGrid[i][j+2] != null && tileGrid[i][j+3] != null
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType()) 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j+3].getTileType()))
                {//4 IN A ROW
                    tileGrid[i][j] = null;
                    tileGrid[i][j+1] = null;
                    tileGrid[i][j+2] = null;
                    tileGrid[i][j+3] = null;
                    if(!data.getInitializingGrid()){
                    jellyGrid[i][j] = null;
                    jellyGrid[i][j+1] = null;
                    jellyGrid[i][j+2] = null;
                    jellyGrid[i][j+3] = null;
                    }
                    
                    activateNumbersVertical(i,j,4,TILE_TYPE_TWENTY_FIVE_POINTS);
                    
                    //CREATE SPECIAL TILE AND ADD TO GRID
                    CrushSagaTile specialTile;
                    if(data.getInitializingGrid()){
                    specialTile = createTypeFourVerticalTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*(j+1)),0,0,VISIBLE_STATE);
                    }else{
                        specialTile = createTypeFourVerticalTile(calculateTileXInGrid(i),calculateTileYInGrid(j+1),0,0,VISIBLE_STATE);
                    }
                    specialTile.setGridCell(i,j+1);
                    tileGrid[i][j+1] = specialTile;
                    return true;
                }
            return false;
    }
    public boolean deleteThreeInRowHorizontal(int i,int j){
        if(i < gridRows - 2 && tileGrid[i+1][j] != null && tileGrid[i+2][j] != null 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType()) 
                        && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType()))
                {//3 IN A ROW 
                    tileGrid[i][j] = null;
                    tileGrid[i+1][j] = null;
                    tileGrid[i+2][j] = null;
                    //DELETE JELLY
                    if(!data.getInitializingGrid()){
                    jellyGrid[i][j] = null;
                    jellyGrid[i+1][j] = null;
                    jellyGrid[i+2][j] = null;
                    }
                    activateNumbersHorizontal(i,j,3,TILE_TYPE_TWENTY_POINTS);
                    return true;
                }
                return false;
    }
    public void deleteTileType(String tileTypeToBeDeleted){
        for(int i = 0;i < gridColumns;i++){
            for(int j = 0;j < gridRows;j++){
                if(tileGrid[i][j] != null && tileGrid[i][j].getTileType().equals(tileTypeToBeDeleted) && !tileGrid[i][j].getState().equals(BLOCKED_STATE)
                        && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM))
                {
                    tileGrid[i][j] = null;
                    jellyGrid[i][j] = null;
                    activateNumbersHorizontal(i,j,1,TILE_TYPE_TWENTY_POINTS);
                    data.addToGameScore(20);
                }
            }
        }
    }
    public void deleteSurroundingTiles(CrushSagaTile middleTile){
        int colPos = middleTile.getGridColumn();
        int rowPos = middleTile.getGridRow();
        for(int i = colPos-1;i < colPos + 2;i++){
            if(i >= 0 && i < gridColumns)
            for(int j = rowPos-1;j < rowPos + 2;j++){
                if(j >= 0 && j < gridRows && tileGrid[i][j] != null && !tileGrid[i][j].getState().equals(BLOCKED_STATE) && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM)){
                tileGrid[i][j] = null;
                jellyGrid[i][j] = null;
                activateNumbersHorizontal(i,j,1,TILE_TYPE_TWENTY_FIVE_POINTS);
                }
            }
        }
        data.addToGameScore(225);
    }
    public void deleteEntireRow(CrushSagaTile tile){
        int row = tile.getGridRow();
            for(int i = 0;i < gridColumns;i++){
                if(tileGrid[i][row] != null && !tileGrid[i][row].getState().equals(BLOCKED_STATE) && !tileGrid[i][row].getTileType().equals(TILE_TYPE_BOTTOM)){
                tileGrid[i][row] = null;
                jellyGrid[i][row] = null;
                activateNumbersHorizontal(i,row,1,TILE_TYPE_TWENTY_FIVE_POINTS);
                }
            }
            
            data.addToGameScore(250);
    }
    public void deleteEntireColumn(CrushSagaTile tile){
        int column = tile.getGridColumn();
        for(int j = 0;j < gridRows;j++){
            if(tileGrid[column][j] != null && !tileGrid[column][j].getState().equals(BLOCKED_STATE) && !tileGrid[column][j].getTileType().equals(TILE_TYPE_BOTTOM)){
            tileGrid[column][j] = null;
            jellyGrid[column][j] = null;
            activateNumbersHorizontal(column,j,1,TILE_TYPE_TWENTY_FIVE_POINTS);
            }
        }
        
        data.addToGameScore(250);
    }
    public void initializeLTTile(int i,int j){
            CrushSagaTile specialTile;
            
            if(data.getInitializingGrid()){
                specialTile = createTypeLTTile(calculateTileXInGrid(i),unassignedTilesY + (TILE_IMAGE_HEIGHT*j),0,0,VISIBLE_STATE);
            }else{
                specialTile = createTypeLTTile(calculateTileXInGrid(i),calculateTileYInGrid(j),0,0,VISIBLE_STATE);
            }
            specialTile.setGridCell(i,j);
            tileGrid[i][j] = specialTile;
    }
    public void activateNumbersVertical(int i,int j,int num,String tileType){
        if(!data.getInitializingGrid()){
            for(int m = j;m < j+num;m++){
                if(tileType.equals(TILE_TYPE_THIRTY_POINTS)){
                moveTile(createTypeThirtyPointsTile(calculateTileXInGrid(i),calculateTileYInGrid(m),0,0,VISIBLE_STATE),calculateTileXInGrid(i),calculateTileYInGrid(m-1),POINT_VELOCITY);
                }else if(tileType.equals(TILE_TYPE_TWENTY_FIVE_POINTS)){
                    moveTile(createTypeTwentyFivePointsTile(calculateTileXInGrid(i),calculateTileYInGrid(m),0,0,VISIBLE_STATE),calculateTileXInGrid(i),calculateTileYInGrid(m-1),POINT_VELOCITY);
                }else if(tileType.equals(TILE_TYPE_TWENTY_POINTS)){
                    moveTile(createTypeTwentyPointsTile(calculateTileXInGrid(i),calculateTileYInGrid(m),0,0,VISIBLE_STATE),calculateTileXInGrid(i),calculateTileYInGrid(m-1),POINT_VELOCITY);
                }
            }
        }
    }
    public void activateNumbersHorizontal(int i,int j,int num,String tileType){
        if(!data.getInitializingGrid()){
            for(int k = i;k < i+num;k++){
                if(tileType.equals(TILE_TYPE_THIRTY_POINTS)){
                moveTile(createTypeThirtyPointsTile(calculateTileXInGrid(k),calculateTileYInGrid(j),0,0,VISIBLE_STATE),calculateTileXInGrid(k),calculateTileYInGrid(j-1),POINT_VELOCITY);
                }else if(tileType.equals(TILE_TYPE_TWENTY_FIVE_POINTS)){
                moveTile(createTypeTwentyFivePointsTile(calculateTileXInGrid(k),calculateTileYInGrid(j),0,0,VISIBLE_STATE),calculateTileXInGrid(k),calculateTileYInGrid(j-1),POINT_VELOCITY);
                }else if(tileType.equals(TILE_TYPE_TWENTY_POINTS)){
                moveTile(createTypeTwentyPointsTile(calculateTileXInGrid(k),calculateTileYInGrid(j),0,0,VISIBLE_STATE),calculateTileXInGrid(k),calculateTileYInGrid(j-1),POINT_VELOCITY);
                }
            }
        }
    }
    
    public void executeTileTypeFive(CrushSagaTile tileSelected,CrushSagaTile selectedTile){
        if(!tileSelected.getTileType().equals(TILE_TYPE_FIVE))
                {
                    tileGrid[selectedTile.getGridColumn()][selectedTile.getGridRow()] = null;
                    deleteTileType(tileSelected.getTileType());
                    moveAllTilesDown();
                    fillEmptyCells();
                    data.subtractOneMovesRemaining();
                }else if(!selectedTile.getTileType().equals(TILE_TYPE_FIVE))
                {
                    tileGrid[tileSelected.getGridColumn()][tileSelected.getGridRow()] = null;
                    deleteTileType(selectedTile.getTileType());
                    moveAllTilesDown();
                    fillEmptyCells();
                    data.subtractOneMovesRemaining();
                }
    }
    public void executeTileTypeLT(CrushSagaTile tileSelected,CrushSagaTile selectedTile){
        swapTiles(tileSelected,selectedTile);
        if(!tileSelected.getTileType().equals(TILE_TYPE_LT)){
            data.setDeleteThisTileSurroundings(selectedTile);
        }else{
            data.setDeleteThisTileSurroundings(tileSelected);
        }
        data.subtractOneMovesRemaining();
        data.setDeleteSurroundings(true);
    }
    public void executeTileTypeFourVertical(CrushSagaTile tileSelected,CrushSagaTile selectedTile){
                swapTiles(tileSelected,selectedTile);
                if(tileSelected.getTileType().equals(TILE_TYPE_FOUR_VERTICAL))
                {
                    data.setDeleteThisTilesColumn(tileSelected);
                }else{
                    data.setDeleteThisTilesColumn(selectedTile);
                }
                data.subtractOneMovesRemaining();
                data.setDeleteVertically(true);
    }
    public void executeTileTypeFourHorizontal(CrushSagaTile tileSelected,CrushSagaTile selectedTile){
            swapTiles(tileSelected,selectedTile);
            if(tileSelected.getTileType().equals(TILE_TYPE_FOUR_HORIZONTAL))
            {
                data.setDeleteThisTilesRow(tileSelected);
            }else{
                data.setDeleteThisTilesRow(selectedTile);
            }
            data.subtractOneMovesRemaining();
            data.setDeleteHorizontally(true);
    }
    public void executeRegularMove(CrushSagaTile tileSelected, CrushSagaTile selectedTile){
                //EXECUTE NO SPECIAL TILES
                //if(!selectedTile.getTileType().equals(TILE_TYPE_BLOCKED) && !tileSelected.getTileType().equals(TILE_TYPE_BLOCKED)){
                    
                
                swapTiles(selectedTile,tileSelected);
                
                if(!hasMatches()){
                    data.setSwapBack(true);
                    data.setTileToBeSwappedOne(tileSelected);
                    data.setTileToBeSwappedTwo(selectedTile);
                }else{
                    data.subtractOneMovesRemaining();
                }
                
                //}
    }
    public boolean hasMatches(){
        for(int i = 0;i < gridColumns;i++){
            for(int j = 0;j < gridRows;j++){
                if(!tileGrid[i][j].getState().equals(BLOCKED_STATE) && !tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM))
                {
                if (i < gridColumns - 4 && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 2][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 3][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 4][j].getTileType())) {
                    return true;
                }
                if (j < gridRows - 4 && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 1].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 3].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 4].getTileType())) {
                    return true;
                }
                if (i < gridRows - 3 && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 2][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 3][j].getTileType())) {
                    return true;
                }
                if (j < gridRows - 3 && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 1].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 3].getTileType())) {
                    return true;
                }
                if (i < gridRows - 2 && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 2][j].getTileType()))return true;
                
                if(j< gridRows - 2 && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 1].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i][j + 2].getTileType())
                )//THREE IN ROW VERTICAL, CHECK L, T
                {
                    if (i > 0 && i < gridColumns - 1
                        && tileGrid[i][j].getTileType().equals(tileGrid[i - 1][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j].getTileType())) {
                    return true;
                }
                if (i > 1 && tileGrid[i][j].getTileType().equals(tileGrid[i - 1][j + 1].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i - 2][j + 1].getTileType())) {
                    return true;
                }
                if (i > 0 && i < gridColumns - 1
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j + 2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i - 1][j + 2].getTileType())) {
                    return true;
                }
                if (i < gridColumns - 2
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j + 1].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 2][j + 1].getTileType())) {
                    return true;
                }
                if (i >= 2 && tileGrid[i][j].getTileType().equals(tileGrid[i - 1][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i - 2][j].getTileType())) {
                    return true;
                }
                if (i >= 2 && tileGrid[i][j].getTileType().equals(tileGrid[i - 1][j + 2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i - 2][j + 2].getTileType())) {
                    return true;
                }
                if (i <= gridRows - 3 && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j + 2].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 2][j + 2].getTileType())) {
                    return true;
                }
                if (i <= gridRows - 3 && tileGrid[i][j].getTileType().equals(tileGrid[i + 1][j].getTileType())
                        && tileGrid[i][j].getTileType().equals(tileGrid[i + 2][j].getTileType())) {
                    return true;
                }
                    return true;
                }
                
               }
            }
        }
        return false;
    }
    public int numTilesBottom(){
        int numTilesBottom = 0;
        int j = gridRows - 1;
        for(int i = 0;i < gridColumns;i++){
            if(tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM))
                numTilesBottom++;
        }
        return numTilesBottom;
    }
    /**
     *STARTING FROM BOTTOM OF GRID, IF A SPACE ON GRID IS NOT NULL, KEEP MOVING THAT TILE DOWN UNTIL IT CAN'T GO DOWN ANYMORE
     */
    public void moveAllTilesDown(){
        
        for(int j = gridRows-1;j >= 0;j--){
            for(int i = 0;i < gridColumns;i++){
                if(tileGrid[i][j] != null)
                {
                    int count = 0;
                    for(int k = j+1;k < gridRows;k++)
                    {
                        if(tileGrid[i][k] == null)
                        {
                            count++;
                            if(k == gridRows-1){
                            moveTileWithinGrid(tileGrid[i][j],i,j+count,TILE_GRID_VELOCITY);
                            tileGrid[i][j] = null;
                            }
                            //moveTileWithinGrid(tileGrid[i][k-1],i,k,TILE_GRID_VELOCITY);
                            //tileGrid[i][k-1] = null;
                        }else if(count != 0)
                        {
                            moveTileWithinGrid(tileGrid[i][j],i,j+count,TILE_GRID_VELOCITY);
                            tileGrid[i][j] = null;
                            break;
                        }
                        
                        
                    }
                }
            }
        }
    }
    public void moveTile(CrushSagaTile tileToBeMoved,int x,int y,int velocity)
    {
        tileToBeMoved.setTarget(x, y);
        tileToBeMoved.startMovingToTarget(velocity);
        movingTiles.add(tileToBeMoved);   
    }
    public void swapTiles(CrushSagaTile t1,CrushSagaTile t2)
    {
        if(!t1.getState().equals(BLOCKED_STATE) && !t2.getState().equals(BLOCKED_STATE)){
            int tempCol = t2.getGridColumn();
            int tempRow = t2.getGridRow();
            moveTileWithinGrid(t2,t1.getGridColumn(),t1.getGridRow(),TILE_GRID_VELOCITY);
            moveTileWithinGrid(t1,tempCol,tempRow,TILE_GRID_VELOCITY);
        }
    }
    public void moveTileWithinGrid(CrushSagaTile tileToBeMoved,int gridCol,int gridR,int velocity)
    {
            tileToBeMoved.setTarget(calculateTileXInGrid(gridCol), calculateTileYInGrid(gridR));
            tileToBeMoved.startMovingToTarget(velocity);
            movingTiles.add(tileToBeMoved);
            tileToBeMoved.setGridCell(gridCol, gridR);
            tileGrid[gridCol][gridR] = tileToBeMoved;
            tileToBeMoved.setGridCell(gridCol, gridR);
    }
    public void moveJellyWithinGrid(CrushSagaTile tileToBeMoved,int gridCol,int gridRow,int velocity)
    {
            tileToBeMoved.setTarget(calculateTileXInGrid(gridCol), calculateTileYInGrid(gridRow));
            tileToBeMoved.startMovingToTarget(velocity);
            movingTiles.add(tileToBeMoved);
            tileToBeMoved.setGridCell(gridCol, gridRow);
            jellyGrid[gridCol][gridRow] = tileToBeMoved;
    }
    public int calculateGridCellColumn(int x)
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        int gameWidth = Integer.parseInt(props.getProperty(CrushSaga.CrushSagaPropertyType.GAME_WIDTH.toString()));
        int columnValue = gridColumns - ((int)(gameWidth - x))/TILE_IMAGE_WIDTH - 1;
        return columnValue;
    }
    public int calculateGridCellRow(int y)
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        int gameHeight = Integer.parseInt(props.getProperty(CrushSaga.CrushSagaPropertyType.GAME_HEIGHT.toString()));
        int rowValue = gridRows - ((int)(gameHeight - y))/TILE_IMAGE_HEIGHT - 1;
        return rowValue;
    }
    public int calculateTileXInGrid(int column)
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        int gameWidth = Integer.parseInt(props.getProperty(CrushSaga.CrushSagaPropertyType.GAME_WIDTH.toString()));
        int cellWidth = TILE_IMAGE_WIDTH;
        return (int)(gameWidth - (cellWidth * (gridColumns -column)));
    }
    public int calculateTileYInGrid(int row)
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        int gameHeight = Integer.parseInt(props.getProperty(CrushSaga.CrushSagaPropertyType.GAME_HEIGHT.toString()));
        int cellHeight = TILE_IMAGE_HEIGHT;
        return (int)(gameHeight - (cellHeight * (gridRows -row)));
    }
    public void fillEmptyCells()
    {
        for(int i = 0;i < gridColumns;i++){
            int count = 0;
            for(int j = gridRows-1;j >= 0;j--){
                if(tileGrid[i][j] == null){
                    CrushSagaTile newTile = generateRandomTile(calculateTileXInGrid(i),-70-(70*count++),i,j);
                    moveTileWithinGrid(newTile,i,j,TILE_GRID_VELOCITY);
                }
            }
        }
    }
    public CrushSagaTile generateRandomTile(int initX,int initY,int gridCol,int gridRow)
    {
        double rand = Math.random();
            if(rand < 0.16){
                return createTypeATile(initX,initY,0,0,VISIBLE_STATE,gridCol,gridRow);
            }else if(rand < 0.32){
                return createTypeBTile(initX,initY,0,0,VISIBLE_STATE,gridCol,gridRow);
            }else if(rand < 0.48){
                return createTypeCTile(initX,initY,0,0,VISIBLE_STATE,gridCol,gridRow);
            }else if(rand < 0.64){
                return createTypeDTile(initX,initY,0,0,VISIBLE_STATE,gridCol,gridRow);
            }else if(rand < 0.8){
                return createTypeETile(initX,initY,0,0,VISIBLE_STATE,gridCol,gridRow);
            }else{
                return createTypeFTile(initX,initY,0,0,VISIBLE_STATE,gridCol,gridRow);
            }        
    }
    public CrushSagaTile createTypeATile(float initX,float initY,float initVx,float initVy,String initState,int gridCol,int gridRow)
    {
        CrushSagaTile newTile = new CrushSagaTile(data.getTileASpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_A);
        newTile.setGridCell(gridCol, gridRow);
        return newTile;
    }
    //GENERATES A TYPE B TILE
    public CrushSagaTile createTypeBTile(float initX,float initY,float initVx,float initVy,String initState,int gridCol,int gridRow)
    {
        CrushSagaTile newTile = new CrushSagaTile(data.getTileBSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_B);
        newTile.setGridCell(gridCol, gridRow);
        return newTile;
    }
    //GENERATES A TYPE C TILE
    public CrushSagaTile createTypeCTile(float initX,float initY,float initVx,float initVy,String initState,int gridCol,int gridRow)
    {
        CrushSagaTile newTile = new CrushSagaTile(data.getTileCSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_C);
        newTile.setGridCell(gridCol, gridRow);
        return newTile;
    }
    //GENERATES A TYPE D TILE
    public CrushSagaTile createTypeDTile(float initX,float initY,float initVx,float initVy,String initState,int gridCol,int gridRow)
    {
        CrushSagaTile newTile = new CrushSagaTile(data.getTileDSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_D);
        newTile.setGridCell(gridCol, gridRow);
        return newTile;
    }
    //GENERATES A TYPE E TILE
    public CrushSagaTile createTypeETile(float initX,float initY,float initVx,float initVy,String initState,int gridCol,int gridRow)
    {
        CrushSagaTile newTile = new CrushSagaTile(data.getTileESpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_E);
        newTile.setGridCell(gridCol, gridRow);
        return newTile;
    }
    //GENERATES A TYPE F TILE
    public CrushSagaTile createTypeFTile(float initX,float initY,float initVx,float initVy,String initState,int gridCol,int gridRow)
    {
        CrushSagaTile newTile = new CrushSagaTile(data.getTileFSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_F);
        newTile.setGridCell(gridCol, gridRow);
        return newTile;
    }
    //GENERATE 4-IN-ROW HORIZONTAL SPECIAL
    public CrushSagaTile createTypeFourHorizontalTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileFourInRowHorizontalSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_FOUR_HORIZONTAL);
        return newTile;
    }
    //GENERATE 4-IN-ROW VERTICAL SPECIAL
    public CrushSagaTile createTypeFourVerticalTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileFourInRowVerticalSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_FOUR_VERTICAL);
        return newTile;
    }
    //GENERATE 5-IN-ROW SPECIAL
    public CrushSagaTile createTypeFiveTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileFiveInRowSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_FIVE);
        return newTile;
    }
    //GENERATE LT SPECIAL
    public CrushSagaTile createTypeLTTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileLTSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_LT);
        return newTile;
    }
    public CrushSagaTile createTypeBlockedTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileBlockedSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_BLOCKED);
        return newTile;
    }
    public CrushSagaTile createTypeBottomTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileBottomSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_BOTTOM);
        return newTile;
    }
    public CrushSagaTile createTypeThirtyPointsTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileThirtyPointsSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_THIRTY_POINTS);
        return newTile;
    }
    public CrushSagaTile createTypeTwentyFivePointsTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileTwentyFivePointsSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_TWENTY_FIVE_POINTS);
        return newTile;
    }
    public CrushSagaTile createTypeTwentyPointsTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getTileTwentyPointsSpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_TWENTY_POINTS);
        return newTile;
    }
    public CrushSagaTile createTypeJellyTile(float initX,float initY,float initVx,float initVy,String initState){
        CrushSagaTile newTile = new CrushSagaTile(data.getJellySpriteType(),initX,initY,initVx,initVy,initState,TILE_TYPE_JELLY);
        return newTile;
    }
    public boolean movesRemaining()
    {
        for(int i = 0;i < gridColumns;i++){
            for(int j = 0;j < gridRows;j++){
                if(!tileGrid[i][j].getTileType().equals(TILE_TYPE_BLOCKED) || !tileGrid[i][j].getTileType().equals(TILE_TYPE_BOTTOM)){
                
                    if(i < gridColumns - 2 && j < gridRows - 1)
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j+1].getTileType()))
                            return true;
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType()))
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j+1].getTileType()))
                        {//8
                            return true;
                        }

                    }
                    if(i < gridColumns - 3)//hor
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+3][j].getTileType()))
                        {//2
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+3][j].getTileType()))
                        {//7
                            return true;
                        }
                    }
                    if(j > 0 && i < gridColumns - 2)//hor
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j-1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j-1].getTileType()))//3
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j-1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j].getTileType()))//5
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j-1].getTileType()))//6
                        {
                            return true;
                        }
                    }
                    if(i > 0 && j < gridRows - 2)//ver
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+2].getTileType()))//1
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType()))//5
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+2].getTileType()))//8
                        {
                            return true;
                        }
                    }
                    if(j < gridRows - 3)//ver
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+3].getTileType()))//2
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+3].getTileType()))//7
                        {
                            return true;
                        }
                    }
                    if(i < gridColumns - 1 && j < gridRows - 2)//ver
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+2].getTileType()))//3
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType()))//4
                        {
                            return true;
                        }
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+2].getTileType()))//6
                        {
                            return true;
                        }
                    }
                    if(i < gridColumns - 2 && j < gridRows - 3)//L
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+3].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+2].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j+2].getTileType()))//1
                        {
                            return true;
                        }
                    }
                    if(i > 0 && i < gridColumns - 2 && j < gridRows - 2)//L
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+2].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+2].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+2][j+2].getTileType()))//2
                        {
                            return true;
                        }
                    }
                    if(i > 0 && i < gridColumns - 1 && j < gridRows - 3)//T
                    {
                        if(tileGrid[i][j].getTileType().equals(tileGrid[i-1][j+1].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+2].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i][j+3].getTileType())
                                && tileGrid[i][j].getTileType().equals(tileGrid[i+1][j+1].getTileType()))//1
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public void moveInitialGridDown(){
        
            for(int j = gridRows-1;j >= 0;j--){
                for(int i = 0;i < gridColumns;i++){
                moveTileWithinGrid(tileGrid[i][j],i,j,TILE_GRID_VELOCITY);
                }
            }
        
    }
    public void replaceInitialGridTiles(){
        for(int i = 0;i < gridColumns;i++){
                for(int j = 0;j < gridRows;j++){
                    if(tileGrid[i][j] == null)
                    {
                        CrushSagaTile replacement = generateRandomTile(calculateTileXInGrid(i), unassignedTilesY+(TILE_IMAGE_HEIGHT*j),i,j);
                        tileGrid[i][j] = replacement;
                    }
                }
            }
    }
    
    public boolean jellyLeft(){
        for(int i = 0;i < gridColumns;i++){
            for(int j = 0;j < gridRows;j++){
                if(jellyGrid[i][j] != null)
                    return true;
            }
        }
        return false;
    }
}
