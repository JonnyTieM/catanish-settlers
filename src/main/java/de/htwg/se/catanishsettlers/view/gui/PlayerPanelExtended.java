package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelExtended extends PlayerPanelAbstract {

    public PlayerPanelExtended(Player player) {
        super(player);

        add(new Label("Settlements: " + player.settlements));
        add(new Label("Cities: " + player.cities));
        add(new Label("Victory Points: " + player.getScore()));

    }
}
