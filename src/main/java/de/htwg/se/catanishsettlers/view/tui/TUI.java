package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jonathan on 19.06.2015.
 */
public class TUI {
    Game game;

    boolean end;
    Scanner input;

    public TUI() {
        end = false;
        input = Input.getInstance();

        prepareGame();
        while (!end) {
            end = true;
        }
    }

    private void prepareGame() {
        System.out.println("Welcome to the Catanish Settlers!\n\n");

        int amountPlayers = getAmountPlayers();
        List <Player> players = createPlayers(amountPlayers);
        game = new Game(players);
    }

    private int getAmountPlayers() {
        boolean rightInput = false;
        int amountPlayers = 0;
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
        for(int i = 0; i < amountPlayers; i++) {
            System.out.println("Name Player " + (i + 1) + ":");
            String name = input.next();
            players.add(new Player(name));
        }
        return players;
    }

    public static void main(String[] args) {
        TUI tui = new TUI();
    }
}
