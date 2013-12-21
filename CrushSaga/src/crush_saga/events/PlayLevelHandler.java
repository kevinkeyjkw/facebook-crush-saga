/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crush_saga.events;

import crush_saga.data.CrushSagaDataModel;
import crush_saga.file.CrushSagaFileManager;
import crush_saga.ui.CrushSagaMiniGame;
import crush_saga_main.CrushSaga.CrushSagaPropertyType;
import static crush_saga_main.CrushSagaConstants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kevin
 */
public class PlayLevelHandler implements ActionListener{
    private CrushSagaMiniGame game;
    public PlayLevelHandler(CrushSagaMiniGame miniGame)
    {
        game = miniGame;
    }
    public void actionPerformed(ActionEvent ae)
    {
        game.switchToGameScreen();
    }
    
}
