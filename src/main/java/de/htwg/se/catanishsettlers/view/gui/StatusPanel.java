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

    public StatusPanel() {
        JButton switchButton = new JButton("Switch player (just a test)");
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CatanishSettlers.game.switchPlayer();
            }
        });
        add(switchButton);
    }

    public void paint(Graphics g) {

    }
}
