package de.htwg.se.catanishsettlers.view.gui.playerPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import java.util.Observable;

/**
 * Created by Stephan on 13.06.2015.
 */
class PlayerPanelCompact extends PlayerPanelAbstract {

    private final JLabel resLabel = new JLabel();

    public PlayerPanelCompact(Player player) {
        super(player);

        add(resLabel);
    }

    public void update(Observable o, Object arg) {
        super.updateSettlementsLabel();
        super.updateCitiesLabel();
        super.updateVictoryPointsLabel();
        resLabel.setText("Resources: " + player.getResources().getTotal());
    }
}
