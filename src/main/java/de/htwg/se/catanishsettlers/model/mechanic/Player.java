package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Stephan on 31.03.2015.
 */
public class Player extends Observable {
    private String name = "unnamed";
    private List<Card> cards;
    private int knightCount, victoryCardsCount;
    private ResourceCollection resources;
    private Color color;
    private int settlements, cities, roads;

    private boolean hasLargestKnightArmy, hasLongestTradeRoute;

    public Player(String name) {
        this.name = name;
        settlements = Config.MAX_SETTLEMENTS;
        cities = Config.MAX_CITIES;
        roads = Config.MAX_ROADS;
        cards = new ArrayList<Card>();
        resources = new ResourceCollection(0, 0, 0, 0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean playCard(Card.Types cardType) {

        Card foundCard = findThisCardInPlayersInventory(cardType);

        if (foundCard == null) {
            return false;        // player didn't have such a card
        }

        cards.remove(foundCard);
        playerPlaysThisCard(cardType);
        return true;
    }

    private Card findThisCardInPlayersInventory(Card.Types cardType) {
        Card foundCard = null;
        for (Card card : cards) {
            if (card.getType() == cardType) {
                foundCard = card;
                break;
            }
        }
        return foundCard;
    }

    private void playerPlaysThisCard(Card.Types cardType) {
        switch (cardType) {
            case KNIGHT:
                knightCount++;
                //TO DO: reposition robber
                break;
            case VICTORYPOINT:
                victoryCardsCount++;
                break;
            case PROGRESS_DEVELOPMENT:          //TO DO: get 2 resources as requested
                break;
            case PROGRESS_MONOPOLY:             //TO DO: get all resources from other players of one requested type
                break;
            case PROGRESS_ROAD_CONSTRUCTION:    //TO DO: immediately legally place 2 roads without paying COST
                break;
            default:
                throw new IllegalStateException();   // this should never happen as there are only 3 card types
        }
    }

    public int getScore() {
        int score = scoreBuildings();
        if (hasLargestKnightArmy) score += Config.LARGEST_KNIGHT_ARMY;
        if (hasLongestTradeRoute) score += Config.LONGEST_TRADE_ROUTE;
        for (Card card : cards) {
            if (card.getType() == Card.Types.VICTORYPOINT) score++;     // victory cards count in hand ...
        }
        score += victoryCardsCount; // ... as well as on the table (there's actually no reason to play them but you can)
        return score;
    }

    private int scoreBuildings() {
        int score = 0;
        score += (Config.MAX_CITIES - cities) * Config.CITY_SCORE;
        score += (Config.MAX_SETTLEMENTS - settlements) * Config.SETTLEMENT_SCORE;
        return score;
    }

    public int getKnightCount() {
        // http://www.ludopedia.de/Gr%C3%B6%C3%9Fte_Rittermacht
        return knightCount;
    }

    public void addResources(ResourceCollection res) {
        resources.add(res);
    }

    public ResourceCollection getResources() {
        return resources;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean hasEnoughResources(ResourceCollection cost) {
        return resources.compareTo(cost) >= 0;
    }

    public boolean payCost(ResourceCollection cost) {
        if (hasEnoughResources(cost)) {
            resources.subtract(cost);
            return true;
        }
        return false;
    }

    private boolean tryToPay(ResourceCollection cost) {
        if (hasEnoughResources(cost)) {
            resources.subtract(cost);
            return true;
        }
        return false;
    }

    public int getAvailableRoads() {
        return roads;
    }

    public int getAvailableSettlements() {
        return settlements;
    }

    public int getAvailableCities() {
        return cities;
    }

    private void setChangedAndNotifyObservers() {
        setChanged();
        notifyObservers();
    }

    public void increaseRoads() {
        roads++;
        setChangedAndNotifyObservers();
    }

    public void decreaseRoads() {
        roads--;
        setChangedAndNotifyObservers();
    }

    public void increaseSettlements() {
        settlements++;
        setChangedAndNotifyObservers();
    }

    public void decreaseSettlements() {
        settlements--;
        setChangedAndNotifyObservers();
    }

    public void increaseCities() {
        cities++;
        setChangedAndNotifyObservers();
    }

    public void decreaseCities() {
        cities--;
        setChangedAndNotifyObservers();
    }
}
