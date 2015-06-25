package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.controller.ConstructionInspector;
import de.htwg.se.catanishsettlers.controller.ConstructionRealizer;
import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jonathan on 19.06.2015.
 */
public class TUI {
    Game game;
    Scanner input;
    MapTUI mapTUI;

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
            game.nextPhase();
        }
        announceWinner();
    }

    private void preDiceRoll(Player player) {
        System.out.println(player.getName() + ": Type \"roll\" to roll the dice."); //\"devcard\" for playing a development card.
        String s = input.next();
        if (s.contentEquals("roll")) {
            game.nextPhase();
        }
    }

    private void postDiceRoll(Player player) {
        if (game.getLastRolledDiceNumber() != 7) {
            System.out.println(player.getName() + ": You rolled a " + game.getLastRolledDiceNumber() + ". Resources were distributed.");
        } else {
            System.out.println(player.getName() + ": You rolled a " + game.getLastRolledDiceNumber() + ". Nothing happens, because there is no robber yet.");
        }
        playerActions(player);
    }

    private void playerActions(Player player) {
        String s = "";
        mapTUI.setActivePlayer(player);
        while (!s.contentEquals("end")) {
            System.out.println(player.getName() + ": Choose what to do next:");
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
        LinkedList<Edge> possibleRoads = ConstructionInspector.possibleStreets(player,game.getMap());
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
        if(ConstructionRealizer.buildRoad(player, selectedEdge, game.getMap())) {
            System.out.println(player.getName() + ": Building road at desired position was successful.");
        } else {
            System.out.println(player.getName() + ": Couldn't build road at desired position. You didn't have enough resources.");
        }
        mapTUI.unmarkEdge();
    }

    private void buildSettlement(Player player) {

    }

    private void buildCity(Player player) {

    }

    private void announceWinner() {

    }

    public static void main(String[] args) {
        TUI tui = new TUI();
    }
}
