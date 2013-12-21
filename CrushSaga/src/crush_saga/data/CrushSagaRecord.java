/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.data;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Kevin
 */
public class CrushSagaRecord {
   private HashMap<String, CrushSagaLevelRecord> levelRecords; 
   
   public CrushSagaRecord()
   {
       levelRecords = new HashMap();
   }
   public int getHighScore(String levelName){
       CrushSagaLevelRecord rec = levelRecords.get(levelName);
       if(rec == null){
           return 0;
       }else{
           return rec.highScore;
       }
   }
   public HashMap<String,CrushSagaLevelRecord> getLevelRecords(){
       return levelRecords;
   }
   public int getStarsEarned(String levelName){
       CrushSagaLevelRecord rec = levelRecords.get(levelName);
       if(rec == null){
           return 0;
       }else{
           return rec.starsEarned;
       }
   }
   public long getFastestTime(String levelName){
       CrushSagaLevelRecord rec = levelRecords.get(levelName);
       if(rec == null){
           return 0;
       }else{
           return rec.fastestTime;
       }
   }
   public void addWin(String levelName,int score,int starsEarned,long time){
       CrushSagaLevelRecord rec = levelRecords.get(levelName);
       if(rec == null){
           rec = new CrushSagaLevelRecord();
           rec.highScore = score;
           rec.starsEarned = starsEarned;
           rec.fastestTime = time;
           
       }else{
           if(rec.highScore < score)
               rec.highScore = score;
           if(rec.fastestTime < time)
               rec.fastestTime = time;
           if(rec.starsEarned < starsEarned)
               rec.starsEarned = starsEarned;
       }
       addCrushSagaLevelRecord(levelName,rec);
       
   }
   public void addCrushSagaLevelRecord(String levelName,CrushSagaLevelRecord rec){
       levelRecords.put(levelName, rec);
   }
   public byte[] toByteArray() throws IOException{
       Iterator<String> keysIt = levelRecords.keySet().iterator();
       int numLevels = levelRecords.keySet().size();
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       DataOutputStream dos = new DataOutputStream(baos);
       dos.writeInt(numLevels);
       while(keysIt.hasNext())
       {//LEVEL NAME,HIGH SCORE,STARS EARNED,FASTEST TIME
           String key = keysIt.next();
           dos.writeUTF(key);
           CrushSagaLevelRecord rec = levelRecords.get(key);
           dos.writeInt(rec.highScore);
           dos.writeInt(rec.starsEarned);
           dos.writeLong(rec.fastestTime);
       }
       return baos.toByteArray();
   }
}
