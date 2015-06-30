package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 30.06.2015.
 */
public class ConstructionInspectorTest {
    Player player;
    Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map();
        player = new Player("Hans");
    }

    @Test
    public void testPossibleSettlements() throws Exception {
        assertEquals(0, ConstructionInspector.possibleSettlements(player, map).size());

        map.getVertex(2, 4).placeBuilding(new Settlement(player));
        map.getEdge(2, 5).buildRoad(new Road(player));
        map.getEdge(2, 4).buildRoad(new Road(player));
        assertEquals(1, ConstructionInspector.possibleSettlements(player, map).size());

        map.getEdge(2, 6).buildRoad(new Road(player));
        map.getEdge(3, 7).buildRoad(new Road(player));
        assertEquals(2, ConstructionInspector.possibleSettlements(player, map).size());
    }

    @Test
    public void testPossibleCities() throws Exception {
        assertEquals(0, ConstructionInspector.possibleCities(player, map).size());

        map.getVertex(2, 4).placeBuilding(new Settlement(player));
        assertEquals(1, ConstructionInspector.possibleCities(player, map).size());

        map.getEdge(2, 5).buildRoad(new Road(player));
        map.getEdge(2, 4).buildRoad(new Road(player));
        map.getVertex(2, 2).placeBuilding(new Settlement(player));
        assertEquals(2, ConstructionInspector.possibleCities(player, map).size());

        map.getVertex(2, 4).placeBuilding(new City(player));
        assertEquals(1, ConstructionInspector.possibleCities(player, map).size());
    }

    @Test
    public void testPossibleStreets() throws Exception {
        assertEquals(0, ConstructionInspector.possibleStreets(player, map).size());

        map.getEdge(2, 5).buildRoad(new Road(player));
        assertEquals(4, ConstructionInspector.possibleStreets(player, map).size());

        map.getEdge(2, 4).buildRoad(new Road(player));
        assertEquals(5, ConstructionInspector.possibleStreets(player, map).size());
    }

    @Test
    public void testCanBuildSettlement() throws Exception {
        assertFalse(ConstructionInspector.canBuildSettlement(player, map.getVertex(2, 4), map));

        map.getEdge(2, 5).buildRoad(new Road(player));
        assertTrue(ConstructionInspector.canBuildSettlement(player, map.getVertex(2, 4), map));
        assertTrue(ConstructionInspector.canBuildSettlement(player, map.getVertex(2, 3), map));
        assertFalse(ConstructionInspector.canBuildSettlement(player, map.getVertex(1, 3), map));

        map.getEdge(1, 3).buildRoad(new Road(player));
        assertTrue(ConstructionInspector.canBuildSettlement(player, map.getVertex(1, 3), map));

        map.getVertex(2, 4).placeBuilding(new Settlement(player));
        assertFalse(ConstructionInspector.canBuildSettlement(player, map.getVertex(2, 4), map));
        assertFalse(ConstructionInspector.canBuildSettlement(player, map.getVertex(2, 3), map));
        assertTrue(ConstructionInspector.canBuildSettlement(player, map.getVertex(1, 3), map));
    }

    @Test
    public void testCanBuildCity() throws Exception {
        assertFalse(ConstructionInspector.canBuildCity(player, map.getVertex(2, 4), map));

        map.getVertex(2, 4).placeBuilding(new Settlement(player));
        assertTrue(ConstructionInspector.canBuildCity(player, map.getVertex(2, 4), map));
    }

    @Test
    public void testCanBuildRoad() throws Exception {
        assertFalse(ConstructionInspector.canBuildRoad(player, map.getEdge(2, 5), map));

        assertFalse(ConstructionInspector.canBuildRoad(player, map.getEdge(3, 10), map));

        map.getEdge(3,8).buildRoad(new Road(player));
        assertTrue(ConstructionInspector.canBuildRoad(player, map.getEdge(3, 10), map));
    }

    @Test
    public void testCanBuildFirstSettlementWithRoad() throws Exception {
        assertTrue(ConstructionInspector.canBuildFirstSettlementWithRoad(player, map.getVertex(2,4), map.getEdge(2,6),map));
        assertFalse(ConstructionInspector.canBuildFirstSettlementWithRoad(player, map.getVertex(2, 4), map.getEdge(3, 7), map));

        map.getVertex(2,4).placeBuilding(new Settlement(player));
        assertFalse(ConstructionInspector.canBuildFirstSettlementWithRoad(player, map.getVertex(2, 4), map.getEdge(2, 6), map));
    }
}