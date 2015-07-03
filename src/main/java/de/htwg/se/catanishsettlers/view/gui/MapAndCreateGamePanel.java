package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.controller.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 26.06.2015.
 */
public class MapAndCreateGamePanel extends JPanel {

    public MapAndCreateGamePanel(Game game, MapPanel mapPanel) {
        setLayout(new CardLayout());
        add(new CreateGamePanel(game));
        add(mapPanel);
    }

    public void next() {
        CardLayout cardLayout = (CardLayout)getLayout();
        cardLayout.next(this);
        repaint();
    }
}
