package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.CatanishSettlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephan on 11.06.2015.
 */
public class StatusPanel extends JPanel {

    public StatusPanel(MultiDicePanel multiDicePanel) {
        setLayout(new FlowLayout());

        final JButton switchButton = new JButton("End Preparation");
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CatanishSettlers.game.nextPhase();
                if (CatanishSettlers.game.isBuildingPhase()) {
                    switchButton.setText("End turn");
                } else {
                    switchButton.setText("Roll dice");
                }
            }
        });
        add(switchButton);
        add(multiDicePanel);
    }
}
