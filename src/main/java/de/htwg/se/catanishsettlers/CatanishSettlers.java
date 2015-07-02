package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.impl.Game;
import de.htwg.se.catanishsettlers.view.gui.GUIFrame;
import de.htwg.se.catanishsettlers.view.gui.MapAndCreateGamePanel;
import de.htwg.se.catanishsettlers.view.gui.MapPanel;
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
        /*List<Player> players = new ArrayList<Player>();
        Player hans = new Player("Hans");
        Player susi = new Player("Susi");
        Player john = new Player("John");

        players.add(hans);
        players.add(susi);
        players.add(john);

        hans.setColor(Color.RED);
        susi.setColor(Color.BLUE);
        john.setColor(Color.MAGENTA);*/


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
