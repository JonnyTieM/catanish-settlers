package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.controller.Game;
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

    public TUIPreparationPhase() {
        input = Input.getInstance();
        preparePlayersAndCreateGame();
        mapTUI = new MapTUI(game.getMap());
        placeFirstBuildings();
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
            Player player = game.getPlayer(i);
            Point vertex = new Point(2, 4);
            Point edge = new Point(3, 3);
            String s = "";
            boolean successful = false;
            while (!successful) {
                System.out.println(player.getName() + ": Place your first Settlement at red cursor (\"up/down\" for up/down with red cursor, \"left/right\" for going left and right with red cursor, \"build\" for building at red cursor):");
                mapTUI.markVertex(vertex.x, vertex.y);
                mapTUI.printMap();
                s = input.next();
                moveCursor(s, vertex);
                if (s == "build") {
                    if (game.buildFirstSettlementWithRoad(player, vertex.x, vertex.y, edge.x, edge.y)){
                        successful = true;
                    }
                }
            }
        }
    }

    private void moveCursor(String s, Point cursor) {
        switch (s) {
            case "up":
                cursor.y -= 1;
                break;
            case "down":
                cursor.y += 1;
                break;
            case "left":
                cursor.x -= 1;
                break;
            case "right":
                cursor.x += 1;
                break;
        }
    }

}
