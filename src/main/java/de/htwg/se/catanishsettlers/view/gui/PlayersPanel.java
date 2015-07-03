package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.controller.PlayerContainer;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.gui.playerPanel.PlayerPanelSwitchable;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayersPanel extends JPanel implements Observer {

    private final java.util.List<PlayerPanelSwitchable> panels = new LinkedList<PlayerPanelSwitchable>();

    public PlayersPanel(List<Player> players) {
        GridLayout gridLayout = new GridLayout(0, 1);
        setLayout(gridLayout);

        for(Player player : players) {
            PlayerPanelSwitchable panel = new PlayerPanelSwitchable(player);
            panels.add(panel);
            add(panel);
        }
        if(panels.size() > 0) panels.get(0).set(PlayerPanelSwitchable.Status.EXTENDED);
    }

    public void update(Observable o, Object arg) {

        if (o instanceof PlayerContainer) {
            PlayerContainer playerContainer = (PlayerContainer) o;
            if (playerContainer.getPlayers().size() > panels.size()) {
                Player newPlayer = playerContainer.getPlayers().get(playerContainer.getPlayers().size() - 1);
                PlayerPanelSwitchable panel = new PlayerPanelSwitchable(newPlayer);
                panels.add(panel);
                add(panel);
                revalidate();
                repaint();
            } else if (playerContainer.getPlayers().size() < panels.size()) {

                for (PlayerPanelSwitchable panel : panels) {
                    boolean remove = true;
                    for (Player player : playerContainer.getPlayers()) {
                        if (player.getName().contentEquals(panel.getName())) {
                            remove = false;
                            break;
                        }
                    }
                    if (remove) {
                        remove(panel);
                        panels.remove(panel);
                        revalidate();
                        repaint();
                    }
                }
            }
            for (int i = 0; i < playerContainer.getPlayers().size(); i++) {
                Player player = playerContainer.getPlayers().get(i);
                if (player == playerContainer.getActivePlayer()) {
                    panels.get(i).set(PlayerPanelSwitchable.Status.EXTENDED);
                } else {
                    panels.get(i).set(PlayerPanelSwitchable.Status.COMPACT);
                }
            }
        }
    }
}
