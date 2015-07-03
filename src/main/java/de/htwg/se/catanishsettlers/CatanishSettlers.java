package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.view.gui.GUIFrame;
import de.htwg.se.catanishsettlers.view.gui.MapAndCreateGamePanel;
import de.htwg.se.catanishsettlers.view.gui.MapPanel;
import de.htwg.se.catanishsettlers.view.gui.PlayersPanel;
import de.htwg.se.catanishsettlers.view.gui.MultiDicePanel;
import de.htwg.se.catanishsettlers.view.tui.TUI;

/**
 * Created by sttrube on 27.03.2015.
 */
public class CatanishSettlers {

    private enum Mode {
        TUI,
        GUI
    }

    public static void main(String[] args) {
        final Game game = new Game();
        Mode mode = Mode.GUI;

        if (mode == Mode.TUI) {
            new TUI();
        }

        if (mode == Mode.GUI) {
            PlayersPanel playersPanel = new PlayersPanel(game.getPlayerContainer().getPlayers());
            MapPanel mapPanel = new MapPanel(game);
            MapAndCreateGamePanel mapAndCreateGamePanel = new MapAndCreateGamePanel(game, mapPanel);
            MultiDicePanel multiDicePanel = new MultiDicePanel(game.getDice());
            game.getDice().addObserver(multiDicePanel);
            game.getDice().addObserver(mapPanel);
            game.getPlayerContainer().addObserver(playersPanel);
            new GUIFrame(game, playersPanel, mapAndCreateGamePanel, multiDicePanel);
        }
    }
}
