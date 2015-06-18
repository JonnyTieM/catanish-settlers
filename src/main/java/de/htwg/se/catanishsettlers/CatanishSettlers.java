package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.ConstructionRealizer;
import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import de.htwg.se.catanishsettlers.view.Log;
import de.htwg.se.catanishsettlers.view.Message;
import de.htwg.se.catanishsettlers.view.MessageFactory;
import de.htwg.se.catanishsettlers.view.gui.GUIFrame;
import de.htwg.se.catanishsettlers.view.gui.MapPanel;
import de.htwg.se.catanishsettlers.view.gui.PlayersPanel;

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
        List<Player> players = new ArrayList<Player>();
        Player hans = new Player("Hans");
        Player susi = new Player("Susi");
        Player john = new Player("John");

        players.add(hans);
        players.add(susi);
        players.add(john);

        hans.color = Color.RED;
        susi.color = Color.BLUE;
        john.color = Color.MAGENTA;

        game = new Game(players);

        Mode mode = Mode.GUI;

        if (mode == Mode.TUI) {
            Log.categories.add(Message.Category.MAP);
            Log.display(MessageFactory.map_overview(game.getMap()));
            Log.categories.add(Message.Category.DICE_ROLL);
            Log.display(MessageFactory.dice_roll(new DiceRoll(3)));
            Log.categories.add(Message.Category.PLAYER);
            Log.display(MessageFactory.player(players.get(0)));
            Log.categories.add(Message.Category.CONSTRUCTION);
            Log.display(MessageFactory.construction(new Road(players.get(1))));
        }

        for (Player player : players) {
            player.addResources(new ResourceCollection(10));
        }

        List<Vertex> vertices = game.getMap().getVertices();
        List<Edge> edges = game.getMap().getEdges();

        edges.get(16).buildRoad(new Road(hans));
        ConstructionRealizer.buildSettlement(hans, vertices.get(20), game.getMap());

        if (mode == Mode.GUI) {
            PlayersPanel playersPanel = new PlayersPanel(game.getPlayers().getPlayers());
            MapPanel mapPanel = new MapPanel(game.getMap());
            game.getPlayers().addObserver(playersPanel);
            new GUIFrame(playersPanel, mapPanel);
        }
    }
}
