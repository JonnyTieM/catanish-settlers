package de.htwg.se.catanishsettlers.view.gui.MainFrame;

import de.htwg.se.catanishsettlers.CatanishSettlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephan on 11.06.2015.
 */
public class StatusPanel extends JPanel {

    private final JButton switchButton;

    public StatusPanel(MultiDicePanel multiDicePanel, final MapAndCreateGamePanel mapAndCreateGamePanel) {
        setLayout(new FlowLayout());

        switchButton = new JButton("End Preparation");
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (switchButton.getText() == "End Preparation") {
                    mapAndCreateGamePanel.next();
                }
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

    public JButton getSwitchButton() { return switchButton; }
}
