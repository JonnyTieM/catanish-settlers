package de.htwg.se.catanishsettlers.view.gui.playersPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observable;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelExtended extends PlayerPanelAbstract {

    private JLabel brickLabel, lumberLabel, woolLabel, grainLabel, oreLabel;

    public PlayerPanelExtended(Player player) {
        super(player);

        JPanel resPanel = new JPanel();
        resPanel.setLayout(new BoxLayout(resPanel, BoxLayout.Y_AXIS));
        resPanel.setBorder(new TitledBorder("Res:"));

        brickLabel = new JLabel();
        lumberLabel = new JLabel();
        woolLabel = new JLabel();
        grainLabel = new JLabel();
        oreLabel = new JLabel();

        resPanel.add(brickLabel);
        resPanel.add(lumberLabel);
        resPanel.add(woolLabel);
        resPanel.add(grainLabel);
        resPanel.add(oreLabel);

        add(resPanel);
    }

    public void update(Observable o, Object arg) {
        super.updateSettlementsLabel();
        super.updateCitiesLabel();
        super.updateVictoryPointsLabel();
        updateResLabels();
    }

    private void updateResLabels() {
        brickLabel.setText("Brick: " + player.getResources().getBrick());
        lumberLabel.setText("Lumber: " + player.getResources().getLumber());
        woolLabel.setText("Wool: " + player.getResources().getWool());
        grainLabel.setText("Grain: " + player.getResources().getGrain());
        oreLabel.setText("Ore: " + player.getResources().getOre());
    }
}
