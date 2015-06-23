package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jonathan on 19.06.2015.
 */
public class TUIPreparationPhase {
    Game game;
    Scanner input;
    int amountPlayers;
    MapTUI mapTUI;
    Map map;

    public TUIPreparationPhase() {
        input = Input.getInstance();
        preparePlayersAndCreateGame();
        mapTUI = new MapTUI(game.getMap());
        map = game.getMap();
        placeFirstBuildings();
        game.nextPhase();
        System.out.println("Game setup is ready. First resources got distributed. Game starts now.");
    }

    public Game getGame() {
        return game;
    }

    private void preparePlayersAndCreateGame() {
        System.out.println("Welcome to the Catanish Settlers!\n\n");

        getAmountPlayers();
        List<Player> players = createPlayers(amountPlayers);
        game = new Game(players);
    }

    private int getAmountPlayers() {
        boolean rightInput = false;
        amountPlayers = 0;
        while (!rightInput) {
            System.out.println("Amount of Players(max 4): ");
            while (!input.hasNextInt()) {
                input.next();
            }
            amountPlayers = input.nextInt();
            if (amountPlayers >= 2 && amountPlayers <= 4) {
                rightInput = true;
            }
        }
        return amountPlayers;
    }

    private List<Player> createPlayers(int amountPlayers) {
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < amountPlayers; i++) {
            System.out.println("Name Player " + (i + 1) + ":");
            String name = input.next();
            players.add(new Player(name));
        }
        return players;
    }

    private void placeFirstBuildings() {
        for (int i = 0; i < amountPlayers; i++) {
            placeSettlementAndRoadForPlayer(i);
        }
        for (int i = amountPlayers - 1; i >= 0; i--) {
            placeSettlementAndRoadForPlayer(i);
        }
    }

    private void placeSettlementAndRoadForPlayer(int i) {
        Player player = game.getPlayer(i);
        mapTUI.setActivePlayer(player);
        Point vertex = new Point(2, 4);
        Point edge;
        String s;
        boolean successful = false;
        while (!successful) {
            System.out.println(player.getName() + ": Place your first Settlement at red cursor (\"up/down\" for up/down with red cursor, \"left/right\" for going left and right with red cursor, \"build\" for building at red cursor):");
            mapTUI.markVertex(vertex.x, vertex.y);
            mapTUI.printMap();
            s = input.next();
            moveCursor(s, vertex);
            if (s.contentEquals("build")) {
                edge = chooseRoad(vertex,player);
                if (game.buildFirstSettlementWithRoad(player, vertex.x, vertex.y, edge.x, edge.y)) {
                    successful = true;
                    System.out.println(player.getName() + ": Building Settlement and Road at desired position was successful.\n");
                    mapTUI.unmarkEdge();
                    mapTUI.unmarkVertex();
                } else {
                    System.out.println(MapTUI.ANSI_RED + "Couldn't build settlement and road at desired position." + MapTUI.ANSI_RESET);
                    mapTUI.unmarkEdge();
                }
            }
        }
        mapTUI.setActivePlayer(null);
    }

    private Point chooseRoad(Point vertex, Player player) {
        Point edge = new Point(2,2);
        String s = "";
        boolean choseStreet = false;
        Edge[] edges = map.getNeighbouringEdges(map.getVertex(vertex.x, vertex.y));
        int cnt = nextEdge(edges, 0);
        while(!choseStreet) {
            mapTUI.markEdge(edges[cnt].getX(),edges[cnt].getY());
            System.out.println(player.getName() + ": Choose the position of the road (\"next\" for next possible position, \"accept\" for building the road):");
            mapTUI.printMap();
            s = input.next();
            if (s.contentEquals("next")) {
                cnt = nextEdge(edges, cnt);
            } else if (s.contentEquals("accept")) {
                edge.x = edges[cnt].getX();
                edge.y = edges[cnt].getY();
                choseStreet = true;
            }
        }
        return edge;
    }

    private int nextEdge(Edge[] edges, int cnt) {
        int next = cnt;
        boolean validEdge = false;
        while (!validEdge) {
            next = (next + 1) % edges.length;
            if (edges[next] != null) {
                validEdge = true;
            }
        }
        return next;
    }

    private void moveCursor(String s, Point cursor) {
        if (s.equals("up")) {
            cursor.y -= 1;
        } else if (s.equals("down")) {
            cursor.y += 1;
        } else if (s.equals("left")) {
            cursor.x -= 1;
        } else if (s.equals("right")) {
            cursor.x += 1;
        }
    }

}
