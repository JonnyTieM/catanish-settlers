package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.controller.PlayerContainer;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Stephan on 11.06.2015.
 */
public class StatusPanel extends JPanel implements Observer {

    private static String activePlayerName;
    public void update(Observable o, Object arg) {
        if (o instanceof PlayerContainer) activePlayerName = ((PlayerContainer)o).getActivePlayer().getName();
    }

    public enum States {
        SETUP,
        PREPARATION,
        ROLL,
        BUILD
    }
    private static States state = States.SETUP;

    public final static JButton switchButton = new JButton();
    private final static JLabel outputLabel = new JLabel();

    public StatusPanel(final Game game, MultiDicePanel multiDicePanel, final MapAndCreateGamePanel mapAndCreateGamePanel) {
        Game game1 = game;
        setLayout(new GridLayout(2, 1));

        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);
        outputPanel.setPreferredSize(new Dimension(300, 30));

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(switchButton);
        controlsPanel.add(multiDicePanel);
        controlsPanel.setLayout(new FlowLayout());

        controlsPanel.setPreferredSize(new Dimension(300, 120));
        setPreferredSize(new Dimension(300, 150));

        add(controlsPanel);
        add(outputPanel);

        setState(States.SETUP);

        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                game.nextPhase();
                switch (state) {
                    case SETUP: if (game.getPlayerContainer().getPlayers().size() == 0) return;
                        mapAndCreateGamePanel.next();
                        setState(States.PREPARATION);
                        break;
                    case ROLL: setState(States.BUILD);
                        break;
                    case BUILD: setState(States.ROLL);
                        break;
                }
            }
        });
    }

    public static void setState(States newState) {
        switch(newState) {
            case SETUP: switchButton.setText("End Setup");
                outputLabel.setText("Add players to the game, then click on 'End Setup'.");
                switchButton.setEnabled(false);
                break;
            case PREPARATION: switchButton.setVisible(false);
                outputLabel.setText(activePlayerName + ": place a settlement and an adjacent road.");
                break;
            case ROLL: switchButton.setText("Roll dice");
                switchButton.setVisible(true);
                outputLabel.setText(activePlayerName + ", roll the dice!");
                break;
            case BUILD: switchButton.setText("End Turn");
                outputLabel.setText(activePlayerName + ", when you're done spending resources, End your turn.");
        }
        state = newState;
    }
}
