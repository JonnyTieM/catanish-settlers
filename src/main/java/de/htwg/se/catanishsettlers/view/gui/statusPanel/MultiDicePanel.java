package de.htwg.se.catanishsettlers.view.gui.statusPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Dice;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Stephan on 23.06.2015.
 */
public class MultiDicePanel extends JPanel implements Observer {
    private final SingleDicePanel[] singleDicePanels;

    public MultiDicePanel(Dice dice) {
        singleDicePanels = new SingleDicePanel[dice.getDiceCount()];
        for(int i = 0; i < singleDicePanels.length; i++) {
            singleDicePanels[i] = new SingleDicePanel();
            add(singleDicePanels[i]);
        }
    }

    public void update(Observable o, Object arg) {
        Dice dice = (Dice)o;
        int[] values = dice.getSingleValues();
        for (int i = 0; i < singleDicePanels.length; i++) {
            singleDicePanels[i].setValue(values[i]);
        }
    }
}
