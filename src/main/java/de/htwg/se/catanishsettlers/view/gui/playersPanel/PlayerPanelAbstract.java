package de.htwg.se.catanishsettlers.view.gui.playersPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observer;

/**
 * Created by Stephan on 13.06.2015.
 */
public abstract class PlayerPanelAbstract extends JPanel implements Observer {

    private JLabel settlementsLabel = new JLabel();
    private JLabel citiesLabel = new JLabel();
    private Player player;

    public PlayerPanelAbstract(Player player) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.player = player;

        TitledBorder border = BorderFactory.createTitledBorder(player.getName());
        border.setTitleColor(player.getColor());
        setBorder(border);

        setForeground(player.getColor());

        updateSettlementsLabel();
        updateCitiesLabel();

        add(settlementsLabel);
        add(citiesLabel);
        add(new JLabel("Victory Points: " + player.getScore()));
    }

    protected void updateSettlementsLabel() {
        settlementsLabel.setText("Settlements: " + player.getAvailableSettlements());
    }

    protected void updateCitiesLabel() {
        citiesLabel.setText("Cities: " + player.getAvailableCities());
    }
}
