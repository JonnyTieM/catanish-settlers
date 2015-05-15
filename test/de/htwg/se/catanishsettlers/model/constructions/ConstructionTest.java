package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class ConstructionTest {

    Construction testRoad, testSettlement, testCity;
    Player testPlayer;

    String testName;
    int testStartResources;

    @Before
    public void setUp() throws Exception {
        testName = "test name";
        testStartResources = 5;
        testPlayer = new Player(testName);
        testPlayer.addResources(new ResourceCollection(testStartResources));
        testRoad = new Road(testPlayer);
        testSettlement = new Settlement(testPlayer);
        testCity = new City(testPlayer);
    }

    @Test
    public void testGetCost() throws Exception {
        ResourceCollection roadPrice, settlementPrice, cityPrice;
        roadPrice = new ResourceCollection(new Brick(2), new Lumber(1));
        settlementPrice = new ResourceCollection(new Brick(1), new Lumber(1), new Wool(1), new Grain(1));
        cityPrice = new ResourceCollection(new Grain(3), new Ore(2));
        assertEquals(testRoad.getCOST().compareTo(roadPrice), 0);
        assertEquals(testSettlement.getCOST().compareTo(settlementPrice), 0);
        assertEquals(testCity.getCOST().compareTo(cityPrice), 0);
    }

    @Test
    public void testGetPlayer() throws Exception {
        ResourceCollection startResources = new ResourceCollection(testStartResources);
        assertEquals(testRoad.getPlayer().getName(), testName);
        assertEquals(testCity.getPlayer().getResources().compareTo(startResources), 0);
    }
}