package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.map.Map;

import javax.swing.*;
import java.awt.*;
import java.nio.MappedByteBuffer;

/**
 * Created by Stephan on 11.06.2015.
 */
public class GUIFrame extends JFrame {

    private MapPanel mapPanel;
    private PlayerPanel[] playerPanels;
    private StatusPanel statusPanel;
    private JSplitPane playersSplitPane, mapSplitPane, statusSplitPane;

    public GUIFrame() {
        mapPanel = new MapPanel(null);
        playerPanels = new PlayerPanel[2];
        for (int i = 0; i < 2; i++) playerPanels[i] = new PlayerPanel(null);
        statusPanel = new StatusPanel();

        playersSplitPane = initSplitPaneDefaultSettings(JSplitPane.VERTICAL_SPLIT);
        playersSplitPane.setResizeWeight(0.5);
        playersSplitPane.setLeftComponent(playerPanels[0]);
        playersSplitPane.setRightComponent(playerPanels[1]);

        mapSplitPane = initSplitPaneDefaultSettings(JSplitPane.HORIZONTAL_SPLIT);
        mapSplitPane.setLeftComponent(mapPanel);
        mapSplitPane.setRightComponent(playersSplitPane);

        statusSplitPane = initSplitPaneDefaultSettings(JSplitPane.VERTICAL_SPLIT);
        statusSplitPane.setLeftComponent(mapSplitPane);
        statusSplitPane.setRightComponent(statusPanel);
        add(statusSplitPane);

        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Catanish Settlers");
        setVisible(true);
    }

    private JSplitPane initSplitPaneDefaultSettings(int orientation) {
        JSplitPane splitPane = new JSplitPane(orientation);
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);
        return splitPane;
    }

    public void drawMap(Map map) {
        mapSplitPane.setLeftComponent(new MapPanel(map));
    }
}
