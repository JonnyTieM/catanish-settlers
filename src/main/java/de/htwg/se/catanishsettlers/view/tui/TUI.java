package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.controller.ConstructionInspector;
import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jonathan on 19.06.2015.
 */
public class TUI {
    private final Game game;
    private final Scanner input;
    private final MapTUI mapTUI;

    public TUI() {
        input = Input.getInstance();

        TUIPreparationPhase preparationPhase = new TUIPreparationPhase();
        game = preparationPhase.getGame();
        mapTUI = new MapTUI(game.getMap());
        while (!game.isThereAWinner()) {
            Player player = game.getActivePlayer();
            System.out.println("\n\n" + player.getName() + ", it's your turn.");
            preDiceRoll(player);
            postDiceRoll(player);
        }
        announceWinner();
    }

    private void preDiceRoll(Player player) {
        boolean nextPhase = false;
        while (!nextPhase) {
            System.out.println(player.getName() + ": Type \"roll\" to roll the dice."); //\"devcard\" for playing a development card.
            String s = input.next();
            if (s.contentEquals("roll")) {
                game.nextPhase();
                nextPhase = true;
            }
        }
    }

    private void postDiceRoll(Player player) {
        if (game.getLastRolledDiceNumber() != 7) {
            System.out.println(player.getName() + ": You rolled a " + game.getLastRolledDiceNumber() + ". Resources were distributed.");
        } else {
            System.out.println(player.getName() + ": You rolled a " + game.getLastRolledDiceNumber() + ". Nothing happens, because there is no robber yet.");
        }
        playerActions(player);
        game.nextPhase();
    }

    private void playerActions(Player player) {
        String s = "";
        mapTUI.setActivePlayer(player);
        while (!s.contentEquals("end")) {
            System.out.println("\nResources of " + player.getName() + " (" + player.getScore() + " points)");
            System.out.println("Lumber: " + player.getResources().getLumber());
            System.out.println("Brick: " + player.getResources().getBrick());
            System.out.println("Wool: " + player.getResources().getWool());
            System.out.println("Grain: " + player.getResources().getGrain());
            System.out.println("Ore: " + player.getResources().getOre());
            System.out.println("\n" + player.getName() + ": Choose what to do next:");
            System.out.println("\"road\" for building a road");
            System.out.println("\"settlement\" for building a settlement");
            System.out.println("\"city\" for building a city");
            System.out.println("\"end\" for ending your turn");
            s = input.next();
            buildConstructions(s, player);
        }
        mapTUI.setActivePlayer(null);
    }

    private void buildConstructions(String s, Player player) {
        if (s.contentEquals("road")) {
            buildRoad(player);
        } else if (s.contentEquals("settlement")) {
            buildSettlement(player);
        } else if (s.contentEquals("city")) {
            buildCity(player);
        }
    }

    private void buildRoad(Player player) {
        int cnt = 0;
        LinkedList<Edge> possibleRoads = ConstructionInspector.possibleStreets(player, game.getMap());
        if (possibleRoads.isEmpty()) {
            System.out.println(player.getName() + ": You can't build a road anywhere right now.");
            return;
        }
        String s = "";
        Edge selectedEdge = possibleRoads.get(cnt);
        mapTUI.markEdge(selectedEdge.getX(), selectedEdge.getY());
        while (!s.contentEquals("build")) {
            System.out.println(player.getName() + ": Choose location where to build the road. Type \"next\" for next possible position and \"build\" for building the road at the red marked position.");
            mapTUI.printMap();
            s = input.next();
            if (s.contentEquals("next")) {
                cnt = (cnt + 1) % possibleRoads.size();
                selectedEdge = possibleRoads.get(cnt);
                mapTUI.markEdge(selectedEdge.getX(), selectedEdge.getY());
            }
        }
        if (game.buildRoad(selectedEdge.getX(), selectedEdge.getY())) {
            System.out.println(player.getName() + ": Building road at desired position was successful.");
        } else {
            System.out.println(player.getName() + ": Couldn't build road at desired position. You didn't have enough resources.");
        }
        mapTUI.unmarkEdge();
    }

    private void buildSettlement(Player player) {
        int cnt = 0;
        LinkedList<Vertex> possibleSettlements = ConstructionInspector.possibleSettlements(player, game.getMap());
        if (possibleSettlements.isEmpty()) {
            System.out.println(player.getName() + ": There are no available positions right now, where it's possible for you to build a settlement.");
            return;
        }
        String s = "";
        Vertex selectedVertex = possibleSettlements.get(cnt);
        mapTUI.markVertex(selectedVertex.getX(), selectedVertex.getY());
        while (!s.contentEquals("build")) {
            System.out.println(player.getName() + ": Choose location where to build the settlement. Type \"next\" for next possible position and \"build\" for building the settlement at the red marked position.");
            mapTUI.printMap();
            s = input.next();
            if (s.contentEquals("next")) {
                cnt = (cnt + 1) % possibleSettlements.size();
                selectedVertex = possibleSettlements.get(cnt);
                mapTUI.markVertex(selectedVertex.getX(), selectedVertex.getY());
            }
        }
        if (game.buildSettlement(selectedVertex.getX(), selectedVertex.getY())) {
            System.out.println(player.getName() + ": Building settlement at desired position was successful.");
        } else {
            System.out.println(player.getName() + ": Couldn't build settlement at desired position. You didn't have enough resources.");
        }
        mapTUI.unmarkVertex();
    }

    private void buildCity(Player player) {
        int cnt = 0;
        LinkedList<Vertex> possibleCities = ConstructionInspector.possibleCities(player, game.getMap());
        if (possibleCities.isEmpty()) {
            System.out.println(player.getName() + ": There are no available positions right now, where it's possible for you to build a city.");
            return;
        }
        String s = "";
        Vertex selectedVertex = possibleCities.get(cnt);
        mapTUI.markVertex(selectedVertex.getX(), selectedVertex.getY());
        while (!s.contentEquals("build")) {
            System.out.println(player.getName() + ": Choose location where to build the city. Type \"next\" for next possible position and \"build\" for building the city at the red marked position.");
            mapTUI.printMap();
            s = input.next();
            if (s.contentEquals("next")) {
                cnt = (cnt + 1) % possibleCities.size();
                selectedVertex = possibleCities.get(cnt);
                mapTUI.markVertex(selectedVertex.getX(), selectedVertex.getY());
            }
        }
        if (game.buildCity(selectedVertex.getX(), selectedVertex.getY())) {
            System.out.println(player.getName() + ": Building city at desired position was successful.");
        } else {
            System.out.println(player.getName() + ": Couldn't build city at desired position. You didn't have enough resources.");
        }
        mapTUI.unmarkVertex();
    }

    private void announceWinner() {
        System.out.println("The game has come to an end. There is a winner.");
        List<Player> winners = game.checkVictory();
        for (Player winner : winners) {
            System.out.println(winner.getName() + " has won the game with " + winner.getScore() + " points");
        }
    }

    public static void main(String[] args) {
        new TUI();
    }
}
