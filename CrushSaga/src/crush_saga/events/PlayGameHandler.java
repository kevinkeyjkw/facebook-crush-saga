/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.data.CrushSagaDataModel;
import crush_saga.ui.CrushSagaMiniGame;
import static crush_saga_main.CrushSagaConstants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Kevin
 */
public class PlayGameHandler implements ActionListener{
    private CrushSagaMiniGame game;
    public PlayGameHandler(CrushSagaMiniGame miniGame)
    {
        game = miniGame;
    }
    public void actionPerformed(ActionEvent ae)
    {
        
        
        if(game.isCurrentScreenState(SPLASH_SCREEN_STATE))
        {
            game.switchToSagaScreen();
        }
    }
    
}
