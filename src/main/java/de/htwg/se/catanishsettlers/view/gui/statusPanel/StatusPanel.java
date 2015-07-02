package de.htwg.se.catanishsettlers.view.gui.statusPanel;

import de.htwg.se.catanishsettlers.CatanishSettlers;
import de.htwg.se.catanishsettlers.view.gui.MapAndCreateGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephan on 11.06.2015.
 */
public class StatusPanel extends JPanel {

    public final static JButton switchButton = new JButton("End Setup");

    public StatusPanel(MultiDicePanel multiDicePanel, final MapAndCreateGamePanel mapAndCreateGamePanel) {
        GridLayout gridLayout = new GridLayout(2, 1);
        setLayout(gridLayout);
        JPanel outputPanel = new JPanel();
        JPanel controlsPanel = new JPanel();
        add(controlsPanel);
        add(outputPanel);

        final JLabel outputLabel = new JLabel("Add players to the game, End Setup when you're finished");
        outputPanel.add(outputLabel);
        outputPanel.setPreferredSize(new Dimension(300, 30));
        controlsPanel.setPreferredSize(new Dimension(300, 120));
        setPreferredSize(new Dimension(300, 150));

        controlsPanel.setLayout(new FlowLayout());
        switchButton.setEnabled(false);

        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (switchButton.getText() == "End Setup") {
                    if (CatanishSettlers.game.getPlayerContainer().getPlayers().size() == 0) return;
                    mapAndCreateGamePanel.next();
                }
                CatanishSettlers.game.nextPhase();
                if (CatanishSettlers.game.isPreparationPhase()) {

                    switchButton.setText("End Preparation");
                    switchButton.setEnabled(false);
                    outputLabel.setText("For each player, place two settlements and two roads, then End Preparation");
                } else if (CatanishSettlers.game.isBuildingPhase()) {
                    switchButton.setText("End turn");
                    outputLabel.setText("When you're done spending resources, End your turn.");
                } else {
                    switchButton.setText("Roll dice");
                    outputLabel.setText("Roll the dice!");
                }
            }
        });
        controlsPanel.add(switchButton);
        controlsPanel.add(multiDicePanel);
    }
}
