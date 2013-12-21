/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.data.CrushSagaDataModel;
import crush_saga.ui.CrushSagaMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kevin
 */
public class ScrollDownHandler implements ActionListener{
    
private CrushSagaMiniGame game;
private CrushSagaDataModel data;
    public ScrollDownHandler(CrushSagaMiniGame miniGame)
    {
        game = miniGame;
        data = (CrushSagaDataModel)game.getDataModel();
    }
    public void actionPerformed(ActionEvent ae)
    {
        data.scrollDown();
    }    

}
