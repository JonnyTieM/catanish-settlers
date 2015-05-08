package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.mechanic.Card;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

import java.util.*;

/**
 * Created by Stephan on 31.03.2015.
 */
public final class Game {
    // config variables - alter these to set up different kinds of games
    private int winScore = 10;
    private int knightCards = 14;
    private int progressCardsMonopoly = 2;
    private int progressCardsDevelopment = 2;
    private int progressCardsRoadConstruction = 2;
    private int victoryCards = 5;

    private List<Player> players;
    private int activePlayerIndex;
    private Random rnd;
    private Stack<Card> cardStack;
    private List<Card> discardPile;
    private Map map;
    //private List<Field> fields;

    private Turn turn;

    public Game() {
        players = new ArrayList<Player>();
        //fields = new ArrayList<Field>();
        rnd = new Random();
        cardStack = new Stack<Card>();
        discardPile = new ArrayList<Card>();
        prepareStack();
    }

    public Turn getTurn() {
        return turn;
    }

    private void prepareStack() {
        for (int k = 0; k < knightCards; k++) {
            cardStack.push(new Card(Card.Types.KNIGHT));
        }
        for (int m = 0; m < progressCardsMonopoly; m++) {
            cardStack.push(new Card(Card.Types.PROGRESS_MONOPOLY));
        }
        for (int d = 0; d < progressCardsDevelopment; d++) {
            cardStack.push(new Card(Card.Types.PROGRESS_DEVELOPMENT));
        }
        for (int r = 0; r < progressCardsRoadConstruction; r++) {
            cardStack.push(new Card(Card.Types.PROGRESS_ROAD_CONSTRUCTION));
        }
        for (int v = 0; v < victoryCards; v++) {
            cardStack.push(new Card(Card.Types.VICTORYPOINT));
        }
        Collections.shuffle(cardStack);
    }

    public Player createPlayer(String name) {
        Player newPlayer = new Player(name, this);
        players.add(newPlayer);
        return newPlayer;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public Player getPlayer(String name) {
        for(Player p : players) {
            if (p.getName().equals((name))) return p;
        }
        return null;
    }

    public Player getActivePlayer() {
        return players.get(activePlayerIndex);
    }

    public Player switchPlayer() {
        checkVictory();

        if (++activePlayerIndex >= players.size()) activePlayerIndex = 0;

        turn = new Turn(getActivePlayer());
        return getActivePlayer();
    }

    public void distributeResources(int dieRoll) {
        List<Field> productiveFields = new ArrayList<Field>();
        for (Field field : map.getFields()) {
            if (field.getTriggerNumber() == dieRoll) productiveFields.add(field);
        }

        for(Field field : productiveFields) {
            for (Building building : map.getBuildings(field)) {
                ResourceCollection yield = new ResourceCollection();
                yield.add(field.getType(), building.getYield());
                building.getPlayer().addResources(yield);
            }
        }
    }

    private void checkVictory() {
        List<Player> winners = new ArrayList<Player>();
        for (Player player : players) {
            if (player.getScore() >= winScore) winners.add(player);
        }
        if (winners.size() > 0) {
            //TODO: game ends, one (or more) players won the game
        }
    }

    public Card getTopCard() {
        if (cardStack.size() > 0) {
            return cardStack.pop();
        } else {
            throw new ArrayIndexOutOfBoundsException(); // attempt to draw a card from empty stack
        }
    }
}
