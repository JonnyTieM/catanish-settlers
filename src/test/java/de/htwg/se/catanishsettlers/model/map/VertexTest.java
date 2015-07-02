package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VertexTest {
    Vertex vertex;

    @Before
    public void setUp() throws Exception {
        vertex = new Vertex(2,3);
    }

    @Test
    public void testPlaceBuilding() throws Exception {
        assertFalse(vertex.hasBuilding());
        vertex.placeBuilding(new Settlement(new Player("Hans")));
        assertTrue(vertex.hasBuilding());
    }

    @Test
    public void testGetBuilding() throws Exception {
        assertNull(vertex.getBuilding());
        vertex.placeBuilding(new Settlement(new Player("Hans")));
        assertTrue(vertex.getBuilding() instanceof Building);
    }

    @Test
    public void testHasBuilding() throws Exception {
        assertFalse(vertex.hasBuilding());
        vertex.placeBuilding(new Settlement(new Player("Hans")));
        assertTrue(vertex.hasBuilding());
        vertex.placeBuilding(new City(new Player("Hans")));
        assertTrue(vertex.hasBuilding());
    }

    @Test
    public void testHasSettlement() throws Exception {
        assertFalse(vertex.hasSettlement());
        vertex.placeBuilding(new Settlement(new Player("Hans")));
        assertTrue(vertex.hasSettlement());
        vertex.placeBuilding(new City(new Player("Hans")));
        assertFalse(vertex.hasSettlement());
    }

    @Test
    public void testHasCity() throws Exception {
        assertFalse(vertex.hasCity());
        vertex.placeBuilding(new City(new Player("Hans")));
        assertTrue(vertex.hasCity());
        vertex.placeBuilding(new Settlement(new Player("Hans")));
        assertFalse(vertex.hasCity());
    }
}