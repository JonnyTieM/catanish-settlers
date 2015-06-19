package de.htwg.se.catanishsettlers.view.gui;

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
    public enum Status {
        COMPACT,
        EXTENDED
    }

    public PlayerPanelSwitchable(Player player) {
        compactPanel = new PlayerPanelCompact(player);
        extendedPanel = new PlayerPanelExtended(player);
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
}
