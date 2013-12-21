/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.data.CrushSagaDataModel;
import static crush_saga_main.CrushSagaConstants.*;
import crush_saga.ui.CrushSagaMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Kevin
 */
public class CloseLevelDialogHandler implements ActionListener{
    private CrushSagaMiniGame game;
    public CloseLevelDialogHandler(CrushSagaMiniGame miniGame)
    {
        game = miniGame;
    }
    public void actionPerformed(ActionEvent ae)
    {
        ((CrushSagaDataModel)game.getDataModel()).closeLevelDialog();
        game.getGUIButtons().get(SAGA_PLAY_LEVEL_BUTTON_TYPE).setState(INVISIBLE_STATE);
        game.getGUIButtons().get(SAGA_PLAY_LEVEL_BUTTON_TYPE).setEnabled(false);
        game.getGUIButtons().get(LEVEL_DIALOG_CLOSE_BUTTON_TYPE).setState(INVISIBLE_STATE);
        game.getGUIButtons().get(LEVEL_DIALOG_CLOSE_BUTTON_TYPE).setEnabled(false);
        game.getGUIDecor().get(STAR_ONE_TYPE).setState(INVISIBLE_STATE);
        game.getGUIDecor().get(STAR_TWO_TYPE).setState(INVISIBLE_STATE);
        game.getGUIDecor().get(STAR_THREE_TYPE).setState(INVISIBLE_STATE);
    }
}
