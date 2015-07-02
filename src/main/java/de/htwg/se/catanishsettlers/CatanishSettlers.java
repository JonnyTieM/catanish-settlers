package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.view.gui.GUIFrame;
import de.htwg.se.catanishsettlers.view.gui.MapAndCreateGamePanel;
import de.htwg.se.catanishsettlers.view.gui.mapPanel.MapPanel;
import de.htwg.se.catanishsettlers.view.gui.PlayersPanel;
import de.htwg.se.catanishsettlers.view.gui.statusPanel.MultiDicePanel;
import de.htwg.se.catanishsettlers.view.tui.Log;
import de.htwg.se.catanishsettlers.view.tui.Message;
import de.htwg.se.catanishsettlers.view.tui.MessageFactory;

/**
 * Created by sttrube on 27.03.2015.
 */
public class CatanishSettlers {
    public static Game game;

    private enum Mode {
        TUI,
        GUI
    }

    public static void main(String[] args) {
        game = new Game();

        Mode mode = Mode.GUI;

        if (mode == Mode.TUI) {
            Log.categories.add(Message.Category.MAP);
            Log.display(MessageFactory.map_overview(game.getMap()));
            Log.categories.add(Message.Category.DICE_ROLL);
            Dice dice = new Dice(3, 8);
            dice.roll();
            Log.display(MessageFactory.dice_roll(dice));
            Log.categories.add(Message.Category.PLAYER);
            //Log.display(MessageFactory.player(players.get(0)));
            Log.categories.add(Message.Category.CONSTRUCTION);
            //Log.display(MessageFactory.construction(new Road(players.get(1))));
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
