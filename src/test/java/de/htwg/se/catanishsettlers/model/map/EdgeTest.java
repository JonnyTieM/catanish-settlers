package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {
    private Edge edge;

    @Before
    public void setUp() throws Exception {
        edge = new Edge(2,3);
    }

    @Test
    public void testGetRoad() throws Exception {
        assertNull(edge.getRoad());
        edge.buildRoad(new Road(new Player("Hans")));
        assertTrue(edge.getRoad() instanceof Road);
    }

    @Test
    public void testHasRoad() throws Exception {
        assertFalse(edge.hasRoad());
        edge.buildRoad(new Road(new Player("Hans")));
        assertTrue(edge.hasRoad());
    }

    @Test
    public void testBuildRoad() throws Exception {
        assertFalse(edge.hasRoad());
        edge.buildRoad(new Road(new Player("Hans")));
        assertTrue(edge.hasRoad());
    }
}