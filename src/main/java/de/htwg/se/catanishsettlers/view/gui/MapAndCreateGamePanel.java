package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.view.gui.createGame.CreateGamePanel;
import de.htwg.se.catanishsettlers.view.gui.statusPanel.StatusPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 26.06.2015.
 */
public class MapAndCreateGamePanel extends JPanel {

    private GUIFrame guiFrame;

    public MapAndCreateGamePanel(MapPanel mapPanel) {
        setLayout(new CardLayout());
        add(new CreateGamePanel());
        add(mapPanel);
    }

    public void next() {
        CardLayout cardLayout = (CardLayout)getLayout();
        cardLayout.next(this);

        guiFrame.repaint();
    }

    public void registerGUI(GUIFrame guiFrame) {
        this.guiFrame = guiFrame;
    }
}
