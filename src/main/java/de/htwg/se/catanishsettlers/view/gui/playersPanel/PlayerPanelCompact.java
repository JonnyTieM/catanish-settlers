package de.htwg.se.catanishsettlers.view.gui.playersPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.util.Observable;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelCompact extends PlayerPanelAbstract {

    private JLabel resLabel = new JLabel();

    public PlayerPanelCompact(Player player) {
        super(player);

        add(resLabel);
    }

    public void update(Observable o, Object arg) {
        super.updateSettlementsLabel();
        super.updateCitiesLabel();
        resLabel.setText("Resources: " + player.getResources().getTotal());
    }
}
