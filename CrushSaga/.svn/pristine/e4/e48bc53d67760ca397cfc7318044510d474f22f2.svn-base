/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.ui.CrushSagaMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Kevin
 */
public class QuitLevelHandler implements ActionListener{
    private CrushSagaMiniGame game;
    public QuitLevelHandler(CrushSagaMiniGame miniGame)
    {
        game = miniGame;
    }
    public void actionPerformed(ActionEvent ae)
    {
        game.getDataModel().endGameAsLoss();
        game.switchToSagaScreen();
    }
}
