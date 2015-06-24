package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Card;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

import java.util.*;

/**
 * Created by Stephan on 31.03.2015.
 */
public final class Game {
    private PlayerContainer playerContainer;
    private Stack<Card> cardStack;
    private List<Card> discardPile;
    private Map map;
    private boolean isThereAWinner;

    private final Dice dice;
    private IGameState state;

    public Game(List<Player> players) {
        dice = new Dice(2, 6);
        isThereAWinner = false;
        cardStack = new Stack<Card>();
        discardPile = new ArrayList<Card>();
        prepareStack();
        this.playerContainer = new PlayerContainer(players);
        state = new PreparationState();
        map = new Map();
    }

    protected void setState(IGameState state) {
        this.state = state;
    }

    public Map getMap() {
        return map;
    }

    public PlayerContainer getPlayerContainer() {
        return playerContainer;
    }

    public Player getActivePlayer() {
        return playerContainer.getActivePlayer();
    }

    public void nextPhase() {
        state.nextState(this);
    }

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

    public Player getPlayer(int i) {
        return playerContainer.getPlayer(i);
    }

    public Player switchPlayer() {
        checkVictory();
        playerContainer.next();
        return getActivePlayer();
    }

    public boolean buildFirstSettlementWithRoad(Player player, int xVertex, int yVertex, int xEdge, int yEdge) {
        if (!isPreparationPhase()) {
            return false;
        }
        Vertex vertex = map.getVertex(xVertex, yVertex);
        Edge edge = map.getEdge(xEdge, yEdge);
        return ConstructionRealizer.buildFirstSettlementWithRoad(player, vertex, edge, map);
    }

    public boolean buildSettlement(int x, int y) {
        if (!isBuildingPhase()) {
            return false;
        }
        Vertex vertex = map.getVertex(x, y);
        return ConstructionRealizer.buildSettlement(getActivePlayer(), vertex, map);
    }

    public boolean buildCity(int x, int y) {
        if (!isBuildingPhase()) {
            return false;
        }
        Vertex vertex = map.getVertex(x, y);
        return ConstructionRealizer.buildCity(getActivePlayer(), vertex, map);
    }

    public boolean buildRoad(int x, int y) {
        if (!isBuildingPhase()) {
            return false;
        }
        Edge edge = map.getEdge(x, y);
        return ConstructionRealizer.buildRoad(getActivePlayer(), edge, map);
    }

    public boolean isBuildingPhase() {
        return state instanceof PostDiceRollState;
    }

    private boolean isPreparationPhase() {
        return state instanceof PreparationState;
    }

    protected void distributeResources(int diceRoll) {
        List<Field> productiveFields = map.getFieldsWithTriggerNumber(diceRoll);

        for (Field field : productiveFields) {
            for (Building building : map.getBuildings(field)) {
                ResourceCollection yield = new ResourceCollection();
                yield.add(field.getType(), building.getYield());
                building.getPlayer().addResources(yield);
            }
        }
    }

    public List<Player> checkVictory() {
        List<Player> winners = new ArrayList<Player>();
        for (Player player : playerContainer.getPlayers()) {
            if (player.getScore() >= Config.SCORE_TO_VICTORY) winners.add(player);
        }
        if (winners.size() > 0) {
            isThereAWinner = true;
        }
        return winners;
    }

    public Card popTopCard() {
        if (cardStack.size() > 0) {
            return cardStack.pop();
        } else {
            throw new ArrayIndexOutOfBoundsException(); // attempt to draw a card from empty stack
        }
    }

    public Dice getDice() {
        return dice;
    }
    public boolean isThereAWinner() {
        return isThereAWinner;
    }
}
