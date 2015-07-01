package de.htwg.se.catanishsettlers.view.gui.temp;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Observable;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelExtended extends PlayerPanelAbstract {

    public PlayerPanelExtended(Player player) {
        super(player);

        JPanel resPanel = new JPanel();
        resPanel.setLayout(new BoxLayout(resPanel, BoxLayout.Y_AXIS));
        resPanel.setBorder(new TitledBorder("Res:"));

        resPanel.add(new JLabel("Brick: " + player.getResources().getBrick()));
        resPanel.add(new JLabel("Lumber: " + player.getResources().getLumber()));
        resPanel.add(new JLabel("Wool: " + player.getResources().getWool()));
        resPanel.add(new JLabel("Grain: " + player.getResources().getGrain()));
        resPanel.add(new JLabel("Ore: " + player.getResources().getOre()));

        add(resPanel);
    }

    public void update(Observable o, Object arg) {
        super.updateSettlementsLabel();
        super.updateCitiesLabel();
    }
}
