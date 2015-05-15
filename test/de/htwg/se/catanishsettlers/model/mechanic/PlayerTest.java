package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player testPlayer;
    String testName = "test name";
    City testCity;
    int initialResourceAmount = 99;

    @Before
    public void setUp() throws Exception {
        testPlayer = new Player(testName, new Game());
        testPlayer.addResources(new ResourceCollection(initialResourceAmount));
        testCity = new City(testPlayer);
        //Game game = new Game();     // to have a card stack to draw from
        assertEquals(testPlayer.getScore(), 0);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(testPlayer.getName(), testName);
    }

    @Test
    public void testPlayCard() throws Exception {
        setUp();
        assertEquals(testPlayer.playCard(Card.Types.VICTORYPOINT), false);     // doesn't have a card yet
        testPlayer.makeInvestment(Card.class);
        boolean playedSomeCard = (testPlayer.playCard(Card.Types.VICTORYPOINT) ||
                                    testPlayer.playCard(Card.Types.KNIGHT) ||
                                    testPlayer.playCard(Card.Types.PROGRESS_DEVELOPMENT) ||
                                    testPlayer.playCard(Card.Types.PROGRESS_MONOPOLY) ||
                                    testPlayer.playCard(Card.Types.PROGRESS_ROAD_CONSTRUCTION));
        assertEquals(playedSomeCard, true);
    }

    @Test
    public void testGetScore() throws Exception {
        setUp();
        testPlayer.makeInvestment(Road.class);
        assertEquals(testPlayer.getScore(), 0);

        while (!testPlayer.playCard(Card.Types.VICTORYPOINT)) {             // draw until 1 victory card has been drawn & played
            testPlayer.addResources(new Card(null).getCOST());    // compensate
            Game game = new Game();                             // replenish card stack
            testPlayer.makeInvestment(Card.class);
        }
        assertEquals(testPlayer.getScore(), 1);
        testPlayer.makeInvestment(Settlement.class);
        assertEquals(testPlayer.getScore(), 2);
        testPlayer.makeInvestment(City.class);
        assertEquals(testPlayer.getScore(), 4);

        setUp();
        testPlayer.makeInvestment(Road.class);
        assertEquals(testPlayer.getScore(), 0);
        testPlayer.makeInvestment(Settlement.class);
        assertEquals(testPlayer.getScore(), 1);
        testPlayer.makeInvestment(City.class);
        assertEquals(testPlayer.getScore(), 3);
    }

    @Test
    public void testGetKnightCount() throws Exception {
        setUp();
        while (!testPlayer.playCard(Card.Types.KNIGHT)) {             // draw until 1 victory card has been drawn & played
            testPlayer.addResources(new Card(null).getCOST());   // compensate
            testPlayer.makeInvestment(Card.class);
        }

        assertEquals(testPlayer.getKnightCount(), 1);
    }

    @Test
    public void testAddResource() throws Exception {
        setUp();
        testPlayer.addResources(new ResourceCollection(1));                                    // 99 + 1
        assertEquals(testPlayer.getResources().compareTo(new ResourceCollection(100)), 0);    // == 100
    }

    @Test
    public void testGetResources() throws Exception {
        setUp();
        assertEquals(testPlayer.getResources().compareTo(new ResourceCollection(initialResourceAmount)), 0);
    }

    @Test
    public void testMakeInvestment() throws Exception {
        setUp();
        ResourceCollection initialResources = testPlayer.getResources();

        assertEquals(testPlayer.getResources().compareTo(new ResourceCollection(initialResourceAmount)), 0);

        //  road
        testPlayer.makeInvestment(Road.class);
        ResourceCollection roadCost = (new Road(null)).getCOST();
        ResourceCollection afterInvestment = initialResources;
        afterInvestment.subtract(roadCost);
        assertEquals(testPlayer.getResources().compareTo(afterInvestment), 0);

        // settlement
        testPlayer.makeInvestment(Settlement.class);
        ResourceCollection settlementCost = (new Settlement(null)).getCOST();
        afterInvestment = initialResources;
        afterInvestment.subtract(settlementCost);
        assertEquals(testPlayer.getResources().compareTo(afterInvestment), 0);

        // city
        testPlayer.makeInvestment(City.class);
        ResourceCollection cityCost = (new City(null)).getCOST();
        afterInvestment = initialResources;
        afterInvestment.subtract(cityCost);
        assertEquals(testPlayer.getResources().compareTo(afterInvestment), 0);

        // card
        testPlayer.makeInvestment(Card.class);
        ResourceCollection cardCost = (new Card(null)).getCOST();
        afterInvestment = initialResources;
        afterInvestment.subtract(cardCost);
        assertEquals(testPlayer.getResources().compareTo(afterInvestment), 0);
    }
}