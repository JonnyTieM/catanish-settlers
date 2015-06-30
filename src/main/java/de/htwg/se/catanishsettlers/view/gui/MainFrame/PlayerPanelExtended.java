package de.htwg.se.catanishsettlers.view.gui.MainFrame;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Stephan on 13.06.2015.
 */
public class PlayerPanelExtended extends PlayerPanelAbstract {

    public PlayerPanelExtended(Player player) {
        super(player);

        JPanel resPanel = new JPanel();
        resPanel.setLayout(new BoxLayout(resPanel, BoxLayout.Y_AXIS));
        resPanel.setBorder(new TitledBorder("Resources:"));

        resPanel.add(new Label("Brick: " + player.getResources().getBrick()));
        resPanel.add(new Label("Lumber: " + player.getResources().getLumber()));
        resPanel.add(new Label("Wool: " + player.getResources().getWool()));
        resPanel.add(new Label("Grain: " + player.getResources().getGrain()));
        resPanel.add(new Label("Ore: " + player.getResources().getOre()));

        add(resPanel);

    }
}
