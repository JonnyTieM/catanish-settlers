package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.Config;
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
    private List<Player> players;
    private int activePlayerIndex;
    private Random rnd;
    private Stack<Card> cardStack;
    private List<Card> discardPile;
    private Map map;

    private Turn turn;

    public Game(List<Player> players) {
        rnd = new Random();
        cardStack = new Stack<Card>();
        discardPile = new ArrayList<Card>();
        prepareStack();
        turn = new Turn(players.get(activePlayerIndex));
        map = new Map();
    }

    public Turn getTurn() {
        return turn;
    }
    public Map getMap() {return map;}
    public List<Player> getPlayers() {return players;}
    public Player getActivePlayer() {return players.get(activePlayerIndex);}

    private void prepareStack() {
        for (int k = 0; k < Config.KNIGHTS_AMOUNT; k++) {
            cardStack.push(new Card(Card.Types.KNIGHT));
        }
        for (int m = 0; m < Config.MONOPOLY_AMOUNT; m++) {
            cardStack.push(new Card(Card.Types.PROGRESS_MONOPOLY));
        }
        for (int d = 0; d < Config.DEVELOPMENT_AMOUNT; d++) {
            cardStack.push(new Card(Card.Types.PROGRESS_DEVELOPMENT));
        }
        for (int r = 0; r < Config.CONSTRUCTION_AMOUNT; r++) {
            cardStack.push(new Card(Card.Types.PROGRESS_ROAD_CONSTRUCTION));
        }
        for (int v = 0; v < Config.VICTORY_CARD_AMOUNT; v++) {
            cardStack.push(new Card(Card.Types.VICTORYPOINT));
        }
        Collections.shuffle(cardStack);
    }

    public Player createPlayer(String name) {
        Player newPlayer = new Player(name);
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

    public Player switchPlayer() {
        checkVictory();

        if (++activePlayerIndex >= players.size()) activePlayerIndex = 0;

        turn = new Turn(getActivePlayer());
        return getActivePlayer();
    }

    public void distributeResources(int dieRoll) {
        List<Field> productiveFields = map.getFieldsWithTriggerNumber(dieRoll);

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
            if (player.getScore() >= Config.SCORE_TO_VICTORY) winners.add(player);
        }
        if (winners.size() > 0) {
            //TODO: game ends, one (or more) players won the game
        }
    }

    public Card popTopCard() {
        if (cardStack.size() > 0) {
            return cardStack.pop();
        } else {
            throw new ArrayIndexOutOfBoundsException(); // attempt to draw a card from empty stack
        }
    }
}
