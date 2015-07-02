package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.impl.Game;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.view.gui.GUIFrame;
import de.htwg.se.catanishsettlers.view.gui.MapAndCreateGamePanel;
import de.htwg.se.catanishsettlers.view.gui.mapPanel.MapPanel;
import de.htwg.se.catanishsettlers.view.gui.PlayersPanel;
import de.htwg.se.catanishsettlers.view.gui.statusPanel.MultiDicePanel;
import de.htwg.se.catanishsettlers.view.tui.TUI;

/**
 * Created by sttrube on 27.03.2015.
 */
public class CatanishSettlers {
    public static final Game game = new Game();

    private enum Mode {
        TUI,
        GUI
    }

    public static void main(String[] args) {

        Mode mode = Mode.GUI;

        if (mode == Mode.TUI) {
            TUI tui = new TUI();
        }

        if (mode == Mode.GUI) {
            PlayersPanel playersPanel = new PlayersPanel(game.getPlayerContainer().getPlayers());
            MapPanel mapPanel = new MapPanel(game.getMap());
            MapAndCreateGamePanel mapAndCreateGamePanel = new MapAndCreateGamePanel(mapPanel);
            MultiDicePanel multiDicePanel = new MultiDicePanel(game.getDice());
            game.getDice().addObserver(multiDicePanel);
            game.getDice().addObserver(mapPanel);
            game.getPlayerContainer().addObserver(playersPanel);
            new GUIFrame(playersPanel, mapAndCreateGamePanel, multiDicePanel);
        }
    }
}
