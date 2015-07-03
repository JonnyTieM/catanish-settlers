package de.htwg.se.catanishsettlers.view.gui.playerPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observer;

/**
 * Created by Stephan on 13.06.2015.
 */
abstract class PlayerPanelAbstract extends JPanel implements Observer {

    private final JLabel settlementsLabel = new JLabel();
    private final JLabel citiesLabel = new JLabel();
    private final JLabel victoryPointsLabel = new JLabel();
    final Player player;

    PlayerPanelAbstract(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player = player;

        TitledBorder border = BorderFactory.createTitledBorder(player.getName());
        border.setTitleColor(player.getColor());
        setBorder(border);

        setForeground(player.getColor());

        updateSettlementsLabel();
        updateCitiesLabel();
        updateVictoryPointsLabel();

        add(settlementsLabel);
        add(citiesLabel);
        add(victoryPointsLabel);
    }

    void updateSettlementsLabel() {
        settlementsLabel.setText("Settlements: " + player.getAvailableSettlements());
    }

    void updateCitiesLabel() {
        citiesLabel.setText("Cities: " + player.getAvailableCities());
    }

    void updateVictoryPointsLabel() {
        victoryPointsLabel.setText("Victory Points: " + player.getScore());
    }
}
