package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.mechanic.Dice;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Stephan on 23.06.2015.
 */
public class DicePanel extends JPanel implements Observer {
    private final Label[] labels;

    public DicePanel(Dice dice) {
        labels = new Label[dice.getDiceCount()];
        for(int i = 0; i < labels.length; i++) {
            labels[i] = new Label();
            add(labels[i]);
        }
    }

    public void update(Observable o, Object arg) {
        Dice dice = (Dice)o;
        int[] values = dice.getSingleValues();
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(String.valueOf(values[i]));
        }
        System.out.println("shown");
    }
}
