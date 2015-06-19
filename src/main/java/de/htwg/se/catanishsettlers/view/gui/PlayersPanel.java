package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.controller.Players;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayersPanel extends JPanel implements Observer {

    private GridLayout gridLayout = new GridLayout(0, 1);
    private java.util.List<PlayerPanelSwitchable> panels = new LinkedList<PlayerPanelSwitchable>();

    public PlayersPanel(List<Player> players) {
        setLayout(gridLayout);
        for(Player player : players) {
            PlayerPanelSwitchable panel = new PlayerPanelSwitchable(player);
            panels.add(panel);
            add(panel);
        }
        panels.get(0).set(PlayerPanelSwitchable.Status.EXTENDED);
    }

    public void update(Observable o, Object arg) {
        Players players = (Players)o;
        for(int i = 0; i < players.getPlayers().size(); i++) {
            Player player = players.getPlayers().get(i);
            if (player == players.getActivePlayer()) {
                panels.get(i).set(PlayerPanelSwitchable.Status.EXTENDED);
            } else {
                panels.get(i).set(PlayerPanelSwitchable.Status.COMPACT);
            }
        }
    }
}
