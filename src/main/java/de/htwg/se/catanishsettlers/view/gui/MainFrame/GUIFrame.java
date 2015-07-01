package de.htwg.se.catanishsettlers.view.gui.MainFrame;

import javax.swing.*;

/**
 * Created by Stephan on 11.06.2015.
 */
public class GUIFrame extends JFrame {

    private final StatusPanel statusPanel;
    private final JSplitPane sPaneStatusAndMap, sPaneStatusMapAndPlayers;

    public GUIFrame(PlayersPanel playersPanel, MapAndCreateGamePanel mapAndCreateGamePanel, MultiDicePanel multiDicePanel) {
        statusPanel = new StatusPanel(multiDicePanel, mapAndCreateGamePanel);

        mapAndCreateGamePanel.registerGUI(this);

        sPaneStatusAndMap = initSplitPaneDefaultSettings(JSplitPane.VERTICAL_SPLIT);
        sPaneStatusAndMap.setLeftComponent(mapAndCreateGamePanel);
        sPaneStatusAndMap.setRightComponent(statusPanel);

        sPaneStatusMapAndPlayers = initSplitPaneDefaultSettings(JSplitPane.HORIZONTAL_SPLIT);
        sPaneStatusMapAndPlayers.setLeftComponent(sPaneStatusAndMap);
        sPaneStatusMapAndPlayers.setRightComponent(playersPanel);

        add(sPaneStatusMapAndPlayers);

        setSize(800, 600);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Catanish Settlers");
        setVisible(true);
        repaint();
    }

    private JSplitPane initSplitPaneDefaultSettings(int orientation) {
        JSplitPane splitPane = new JSplitPane(orientation);
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.9);
        splitPane.setDividerSize(0);
        return splitPane;
    }
}
