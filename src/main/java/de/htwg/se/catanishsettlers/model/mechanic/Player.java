package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.*;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import de.htwg.se.catanishsettlers.view.IGenerateMessages;
import de.htwg.se.catanishsettlers.view.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stephan on 31.03.2015.
 */
public class Player implements IGenerateMessages {
    private String name = "unnamed";
    private ArrayList<Card> cards;
    private int knightCount, victoryCardsCount;
    private ResourceCollection resources;

    private final static int MAX_SETTLEMENTS = 5;
    private final static int MAX_CITIES = 4;
    private final static int MAX_ROADS = 15;

    private List<Settlement> settlements;
    private List<City> cities;
    private List<Road> roads;
    private Game game;

    private boolean hasLargestKnightArmy, hasLongestTradeRoute;

    public Player(String name, Game game) {     // creates new Player and registers the game for him
        this(name);
        this.game = game;
        settlements = new LinkedList<Settlement>();
        cities = new LinkedList<City>();
        roads = new LinkedList<Road>();
    }

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<Card>();
        resources = new ResourceCollection();
    }

    public String getName() {
        return name;
    }

    public boolean playCard(Card.Types cardType) {

        Card foundCard = null;
        for (Card card : cards) {
            if (card.getType() == cardType) {
                foundCard = card;
                break;
            }
        }

        if (foundCard == null) return false;        // player didn't have such a card

        cards.remove(foundCard);
        switch (cardType) {
            case KNIGHT: knightCount++;
                //TODO: reposition robber
                break;
            case VICTORYPOINT: victoryCardsCount++;
                break;
            case PROGRESS_DEVELOPMENT:          //TODO: get 2 resources as requested
                break;
            case PROGRESS_MONOPOLY:             //TODO: get all resources from other players of one requested type
                break;
            case PROGRESS_ROAD_CONSTRUCTION:    //TODO: immediately legally place 2 roads without paying cost
                break;
            default: throw new IllegalStateException();   // this should never happen as there are only 3 card types
        }
        return true;
    }

    public int getScore() {
        int score = scoreBuildings(cities);
        score += scoreBuildings(settlements);
        if (hasLargestKnightArmy) score += 2;
        if (hasLongestTradeRoute) score += 2;
        for (Card card : cards) {
            if (card.getType() == Card.Types.VICTORYPOINT) score++;     // victory cards count in hand ...
        }
        score += victoryCardsCount; // ... as well as on the table (there's actually no reason to play them but you can)
        return score;
    }

    private int scoreBuildings(List<? extends Building> buildings) {
        int score = 0;
        for (Building building : buildings) {
            score += building.getScore();
        }
        return score;
    }

    public int getKnightCount() {
        // http://www.ludopedia.de/Gr%C3%B6%C3%9Fte_Rittermacht
        return knightCount;
    }

    public void addResources(ResourceCollection res) { resources.add(res); }

    public ResourceCollection getResources() {
        return resources;
    }

    public boolean buyCard() {
        if (tryToPay(Card.cost)) {
            Card card = game.getTopCard();
            cards.add(card);
            return true;
        }
        return false;
    }

    private boolean buildSettlement(Vertex vertex) {
        if (hasSettlementAvailable() && tryToPay(Settlement.cost)) {
            //TODO: Check if vertex is empty
            Settlement settlement = new Settlement(this); // TODO: add position
            settlements.add(settlement);
            return true;
        }
        return false;
    }

    public boolean buildCity(Vertex vertex) {
        if (hasCityAvailable() && tryToPay(City.cost)) {
            //TODO: Check if vertex has settlement owned by this player
            City city = new City(this); // TODO: add position
            cities.add(city);
            return true;
        }
        return false;
    }

    public boolean buildRoad(Edge edge) {
        if (hasRoadAvailable() && tryToPay(Road.cost)) {
            //TODO: Check if edge is empty and adjacent to settlement, city or road owned by player
            Road road = new Road(this); // TODO: add position
            roads.add(road);
            return true;
        }
        return false;
    }

    private boolean hasSettlementAvailable() {
        return settlements.size() < MAX_SETTLEMENTS;
    }

    private boolean hasCityAvailable() {
        return cities.size() < MAX_CITIES;
    }

    private boolean hasRoadAvailable() {
        return roads.size() < MAX_ROADS;
    }

    private boolean hasEnoughResources(ResourceCollection cost) {
        return resources.compareTo(cost) > 0;
    }

    private boolean tryToPay(ResourceCollection cost) {
        if (hasEnoughResources(cost)) {
            resources.subtract(cost);
            return true;
        }
        return false;
    }

    public Message[] getMessages() {
        List<Message> messages = new ArrayList<Message>();

        String text;
        Message.Detail detail;

        detail = Message.Detail.MEDIUM;
        text = getClass().getSimpleName();
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.LOW;
        text = name;
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.MEDIUM;
        text = "(" + getScore() + " points)";
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.HIGH;
        text = System.lineSeparator() + getResources().toString();
        messages.add(new Message(text, detail, categories));

        return messages.toArray(new Message[messages.size()]);
    }
}
