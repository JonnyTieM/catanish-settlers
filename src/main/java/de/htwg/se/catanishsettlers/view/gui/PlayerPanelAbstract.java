package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Stephan on 13.06.2015.
 */
public abstract class PlayerPanelAbstract extends JPanel {

    private Player player;

    public PlayerPanelAbstract(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player = player;

        TitledBorder border =BorderFactory.createTitledBorder(player.getName());
        border.setTitleColor(player.color);
        setBorder(border);

        setForeground(player.color);

        add(new Label("Settlements: " + player.settlements));
        add(new Label("Cities: " + player.cities));
        add(new Label("Victory Points: " + player.getScore()));
    }
}
