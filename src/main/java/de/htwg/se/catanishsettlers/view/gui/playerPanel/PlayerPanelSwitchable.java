package de.htwg.se.catanishsettlers.view.gui.playerPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 15.06.2015.
 */
public class PlayerPanelSwitchable extends JPanel {

    private final static String compact = "COMPACT";
    private final static String extended = "EXTENDED";

    private final String name;

    public enum Status {
        COMPACT,
        EXTENDED
    }

    public PlayerPanelSwitchable(Player player) {
        name = player.getName();
        PlayerPanelCompact compactPanel = new PlayerPanelCompact(player);
        PlayerPanelExtended extendedPanel = new PlayerPanelExtended(player);

        player.addObserver(compactPanel);
        player.addObserver(extendedPanel);

        setLayout(new CardLayout());

        add(compactPanel, compact);
        add(extendedPanel, extended);
    }

    public void set(Status status) {
        CardLayout cl = (CardLayout)(getLayout());
        if (status == Status.COMPACT) {
            cl.show(this, compact);
        } else {
            cl.show(this, extended);
        }
    }

    public String getName() { return name; }
}
