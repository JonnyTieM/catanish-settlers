package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelCompact extends PlayerPanelAbstract {

    public PlayerPanelCompact(Player player) {
        super(player);

        add(new Label("Resources: " + player.getResources().getTotal()));
    }
}
