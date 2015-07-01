package de.htwg.se.catanishsettlers.view.gui.playersPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 15.06.2015.
 */
public class PlayerPanelSwitchable extends JPanel {

    final static String compact = "COMPACT";
    final static String extended = "EXTENDED";

    private PlayerPanelCompact compactPanel;
    private PlayerPanelExtended extendedPanel;
    private final String name;

    public enum Status {
        COMPACT,
        EXTENDED
    }

    public PlayerPanelSwitchable(Player player) {
        name = player.getName();
        compactPanel = new PlayerPanelCompact(player);
        extendedPanel = new PlayerPanelExtended(player);

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
