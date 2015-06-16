package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.Log;
import de.htwg.se.catanishsettlers.view.Message;
import de.htwg.se.catanishsettlers.view.MessageFactory;
import de.htwg.se.catanishsettlers.view.gui.GUIFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sttrube on 27.03.2015.
 */
public class CatanishSettlers {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Hans"));
        players.add(new Player("Susi"));
        players.add(new Player("John"));

        players.get(0).color = Color.RED;
        players.get(1).color = Color.BLUE;
        players.get(2).color = Color.MAGENTA;

        Game game = new Game(players);

        Log.categories.add(Message.Category.MAP);
        Log.display(MessageFactory.map_overview(game.getMap()));
        Log.categories.add(Message.Category.DICE_ROLL);
        Log.display(MessageFactory.dice_roll(new DiceRoll(3)));
        Log.categories.add(Message.Category.PLAYER);
        Log.display(MessageFactory.player(players.get(0)));
        Log.categories.add(Message.Category.CONSTRUCTION);
        Log.display(MessageFactory.construction(new Road(players.get(1))));

        List<Vertex> vertices = game.getMap().getVertices();

        vertices.get(2).placeBuilding(new Settlement(players.get(0)));
        vertices.get(5).placeBuilding(new Settlement(players.get(0)));
        vertices.get(11).placeBuilding(new Settlement(players.get(1)));
        vertices.get(13).placeBuilding(new Settlement(players.get(1)));
        vertices.get(14).placeBuilding(new Settlement(players.get(2)));
        vertices.get(21).placeBuilding(new Settlement(players.get(2)));

        vertices.get(21).placeBuilding(new City(players.get(2)));


        List<Edge> edges = game.getMap().getEdges();

        edges.get(1).buildRoad(new Road(players.get(0)));
        edges.get(7).buildRoad(new Road(players.get(0)));
        edges.get(8).buildRoad(new Road(players.get(1)));
        edges.get(11).buildRoad(new Road(players.get(1)));
        edges.get(12).buildRoad(new Road(players.get(2)));


        GUIFrame guiFrame = new GUIFrame();
        guiFrame.drawMap(game.getMap());
    }
}
