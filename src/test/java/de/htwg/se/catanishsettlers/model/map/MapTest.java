package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by JonnyTieM on 20.05.2015.
 */
public class MapTest {
    Map map;
    Player hans;
    Player kurt;

    @Before
    public void setUp() throws Exception {
        map = new Map();
        hans = new Player("Hans");
        kurt = new Player("Kurt");

        map.getVertex(2, 3).placeBuilding(new Settlement(hans));
        map.getVertex(3, 2).placeBuilding(new Settlement(kurt));
        map.getVertex(2, 5).placeBuilding(new Settlement(hans));
    }

    @Test
    public void testGetField() throws Exception {
        Field field = map.getField(2, 3);
        assertEquals(2, field.getX());
        assertEquals(3, field.getY());

        field = map.getField(-1, -2);
        assertNull(field);

        field = map.getField(100000, 100000);
        assertNull(field);
    }

    @Test
    public void testGetEdge() throws Exception {
        Edge edge = map.getEdge(2, 6);
        assertEquals(2, edge.getX());
        assertEquals(6, edge.getY());

        edge = map.getEdge(-1, -3);
        assertNull(edge);

        edge = map.getEdge(100000, 100000);
        assertNull(edge);
    }

    @Test
    public void testGetVertex() throws Exception {
        Vertex vertex = map.getVertex(3, 4);
        assertEquals(3, vertex.getX());
        assertEquals(4, vertex.getY());

        vertex = map.getVertex(-1, -2);
        assertNull(vertex);

        vertex = map.getVertex(100000, 100000);
        assertNull(vertex);
    }

    @Test
    public void testGetFields() throws Exception {

    }

    @Test
    public void testGetEdges() throws Exception {

    }

    @Test
    public void testGetVertices() throws Exception {

    }

    @Test
    public void testGetEdges1() throws Exception {
        Edge[] edges = map.getEdges(map.getField(2, 1));
        assertSame(map.getEdge(2, 3), edges[0]);
        assertSame(map.getEdge(3, 4), edges[1]);
        assertSame(map.getEdge(3, 5), edges[2]);
        assertSame(map.getEdge(2, 6), edges[3]);
        assertSame(map.getEdge(2, 5), edges[4]);
        assertSame(map.getEdge(2, 4), edges[5]);

        edges = map.getEdges(map.getField(3, 3));
        assertSame(map.getEdge(3, 9), edges[0]);
        assertSame(map.getEdge(4, 11), edges[1]);
        assertSame(map.getEdge(4, 13), edges[2]);
        assertSame(map.getEdge(3, 12), edges[3]);
        assertSame(map.getEdge(3, 13), edges[4]);
        assertSame(map.getEdge(3, 11), edges[5]);
    }

    @Test
    public void testGetBuildings() throws Exception {
        List<Building> buildings = map.getBuildings(map.getField(2, 1));
        assertTrue(buildings.contains(map.getVertex(3,2).getBuilding()));
        assertTrue(buildings.contains(map.getVertex(2,3).getBuilding()));
        assertEquals(2, buildings.size());
    }

    @Test
    public void testGetVertices1() throws Exception {

    }

    @Test
    public void testGetAdjacentFields() throws Exception {

    }

    @Test
    public void testGetNeighbouringVertices() throws Exception {

    }

    @Test
    public void testGetNeighbouringEdges() throws Exception {

    }

    @Test
    public void testGetVerticesOfEdge() throws Exception {

    }
}