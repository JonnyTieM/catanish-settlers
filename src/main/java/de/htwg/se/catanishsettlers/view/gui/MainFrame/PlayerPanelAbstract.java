package de.htwg.se.catanishsettlers.view.gui.MainFrame;

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
        border.setTitleColor(player.getColor());
        setBorder(border);

        setForeground(player.getColor());

        add(new Label("Settlements: " + player.settlements));
        add(new Label("Cities: " + player.cities));
        add(new Label("Victory Points: " + player.getScore()));
    }
}
