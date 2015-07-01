package de.htwg.se.catanishsettlers.view.gui.temp;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.util.Observable;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelCompact extends PlayerPanelAbstract {

    public PlayerPanelCompact(Player player) {
        super(player);

        add(new JLabel("Resources: " + player.getResources().getTotal()));
    }

    public void update(Observable o, Object arg) {
        super.updateSettlementsLabel();
        super.updateCitiesLabel();
    }
}
