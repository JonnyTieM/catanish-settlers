package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.controller.ConstructionRealizer;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 30.06.2015.
 */
public class ConstructionRealizerTest {
    private Map map;
    private Player player;

    @Before
    public void setUp() throws Exception {
        map = new Map();
        player = new Player("Hans");
    }

    @Test
    public void testBuildSettlement() throws Exception {
        assertFalse(ConstructionRealizer.buildSettlement(player, map.getVertex(2, 4), map));

        player.addResources(new ResourceCollection(5, 5, 5, 5, 5));
        assertFalse(ConstructionRealizer.buildSettlement(player, map.getVertex(2, 4), map));

        map.getEdge(2, 6).buildRoad(new Road(player));
        assertTrue(ConstructionRealizer.buildSettlement(player, map.getVertex(2, 4), map));
        System.out.println(player.getResources().toString());
        assertTrue(player.getResources().compareTo(new ResourceCollection(4, 4, 4, 4, 5)) == 0);
    }

    @Test
    public void testBuildCity() throws Exception {
        assertFalse(ConstructionRealizer.buildCity(player, map.getVertex(2, 4), map));

        player.addResources(new ResourceCollection(5, 5, 5, 5, 5));
        assertFalse(ConstructionRealizer.buildCity(player, map.getVertex(2, 4), map));

        map.getEdge(2, 6).buildRoad(new Road(player));
        map.getVertex(2, 4).placeBuilding(new Settlement(player));
        assertTrue(ConstructionRealizer.buildCity(player, map.getVertex(2, 4), map));
        assertFalse(ConstructionRealizer.buildCity(player, map.getVertex(2, 4), map));
        assertTrue(player.getResources().compareTo(new ResourceCollection(5, 5, 5, 3, 2)) == 0);
    }

    @Test
    public void testBuildRoad() throws Exception {
        assertFalse(ConstructionRealizer.buildRoad(player, map.getEdge(2, 5), map));

        map.getEdge(2, 6).buildRoad(new Road(player));
        assertFalse(ConstructionRealizer.buildRoad(player, map.getEdge(2, 5), map));

        player.addResources(new ResourceCollection(5, 5, 5, 5, 5));
        assertTrue(ConstructionRealizer.buildRoad(player, map.getEdge(2, 5), map));
        assertTrue(player.getResources().compareTo(new ResourceCollection(4, 4, 5, 5, 5)) == 0);
    }

    @Test
    public void testBuildFirstSettlementWithRoad() throws Exception {
        assertTrue(ConstructionRealizer.buildFirstSettlementWithRoad(player, map.getVertex(2, 4), map.getEdge(2, 6), map));
        assertFalse(ConstructionRealizer.buildFirstSettlementWithRoad(player, map.getVertex(2, 4), map.getEdge(2, 6), map));
        assertFalse(ConstructionRealizer.buildFirstSettlementWithRoad(player, map.getVertex(3, 4), map.getEdge(2, 6), map));
        assertFalse(ConstructionRealizer.buildFirstSettlementWithRoad(player, map.getVertex(2, 4), map.getEdge(2, 7), map));
    }
}