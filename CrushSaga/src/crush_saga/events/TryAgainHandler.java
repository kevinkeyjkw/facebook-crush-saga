/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.data.CrushSagaDataModel;
import crush_saga.ui.CrushSagaMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static crush_saga_main.CrushSagaConstants.*;

/**
 *
 * @author Kevin
 */
public class TryAgainHandler implements ActionListener{
    private CrushSagaMiniGame game;
    public TryAgainHandler(CrushSagaMiniGame miniGame){
        game = miniGame;
        
    }
    public void actionPerformed(ActionEvent ae){
        game.switchToGameScreen();
        ((CrushSagaDataModel)game.getDataModel()).setGameScore(0);
        
    }
    
}
