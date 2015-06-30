package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.ConstructionRealizer;
import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import de.htwg.se.catanishsettlers.view.gui.MainFrame.*;
import de.htwg.se.catanishsettlers.view.tui.Log;
import de.htwg.se.catanishsettlers.view.tui.Message;
import de.htwg.se.catanishsettlers.view.tui.MessageFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
