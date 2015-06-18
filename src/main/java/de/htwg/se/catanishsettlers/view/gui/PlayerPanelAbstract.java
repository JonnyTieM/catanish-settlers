package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 13.06.2015.
 */
public abstract class PlayerPanelAbstract extends JPanel {

    private Player player;

    public PlayerPanelAbstract(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player = player;
        setBorder(BorderFactory.createTitledBorder(player.getName()));
    }
}
