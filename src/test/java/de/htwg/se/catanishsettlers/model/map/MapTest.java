package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
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
        List<Field> fields = map.getFields();
        assertEquals(19, fields.size());
    }

    @Test
    public void testGetEdges() throws Exception {
        LinkedList<Edge> edges = map.getEdges();
        assertEquals(72, edges.size());
    }

    @Test
    public void testGetVertices() throws Exception {
        LinkedList<Vertex> vertices = map.getVertices();
        assertEquals(54, vertices.size());
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
        assertTrue(buildings.contains(map.getVertex(3, 2).getBuilding()));
        assertTrue(buildings.contains(map.getVertex(2, 3).getBuilding()));
        assertEquals(2, buildings.size());
    }

    @Test
    public void testGetVertices1() throws Exception {
        Vertex[] vertices = map.getVertices(map.getField(2, 0));
        assertSame(map.getVertex(2, 0), vertices[0]);
        assertSame(map.getVertex(3, 0), vertices[1]);
        assertSame(map.getVertex(3, 1), vertices[2]);
        assertSame(map.getVertex(3, 2), vertices[3]);
        assertSame(map.getVertex(2, 2), vertices[4]);
        assertSame(map.getVertex(2, 1), vertices[5]);

        vertices = map.getVertices(map.getField(3, 3));
        assertSame(map.getVertex(3, 7), vertices[0]);
        assertSame(map.getVertex(4, 7), vertices[1]);
        assertSame(map.getVertex(4, 8), vertices[2]);
        assertSame(map.getVertex(4, 9), vertices[3]);
        assertSame(map.getVertex(3, 9), vertices[4]);
        assertSame(map.getVertex(3, 8), vertices[5]);
    }

    @Test
    public void testGetAdjacentFields() throws Exception {
        Field[] fields = map.getAdjacentFields(map.getVertex(2, 2));
        assertSame(map.getField(1, 0), fields[0]);
        assertSame(map.getField(2, 0), fields[1]);
        assertSame(map.getField(2, 1), fields[2]);

        fields = map.getAdjacentFields(map.getVertex(2, 5));
        assertSame(map.getField(1, 1), fields[0]);
        assertSame(map.getField(2, 2), fields[1]);
        assertSame(map.getField(1, 2), fields[2]);

        fields = map.getAdjacentFields(map.getVertex(3, 4));
        assertSame(map.getField(2, 1), fields[0]);
        assertSame(map.getField(3, 1), fields[1]);
        assertSame(map.getField(2, 2), fields[2]);

        fields = map.getAdjacentFields(map.getVertex(3, 3));
        assertSame(map.getField(2, 1), fields[0]);
        assertSame(map.getField(3, 0), fields[1]);
        assertSame(map.getField(3, 1), fields[2]);

        fields = map.getAdjacentFields(map.getVertex(3, 1));
        assertSame(map.getField(2, 0), fields[0]);
        assertNull(fields[1]);
        assertSame(map.getField(3, 0), fields[2]);
    }

    @Test
    public void testGetNeighbouringVertices() throws Exception {
        Vertex[] vertices = map.getNeighbouringVertices(map.getVertex(2, 2));
        assertSame(map.getVertex(2, 1), vertices[0]);
        assertSame(map.getVertex(3, 2), vertices[1]);
        assertSame(map.getVertex(2, 3), vertices[2]);

        vertices = map.getNeighbouringVertices(map.getVertex(2, 7));
        assertSame(map.getVertex(1, 7), vertices[0]);
        assertSame(map.getVertex(2, 6), vertices[1]);
        assertSame(map.getVertex(2, 8), vertices[2]);
    }

    @Test
    public void testGetNeighbouringEdges() throws Exception {
        Edge[] edges = map.getNeighbouringEdges(map.getVertex(2, 2));
        assertSame(map.getEdge(2, 2), edges[0]);
        assertSame(map.getEdge(2, 3), edges[1]);
        assertSame(map.getEdge(2, 4), edges[2]);

        edges = map.getNeighbouringEdges(map.getVertex(3, 3));
        assertSame(map.getEdge(3, 4), edges[0]);
        assertSame(map.getEdge(3, 3), edges[1]);
        assertSame(map.getEdge(3, 5), edges[2]);

        edges = map.getNeighbouringEdges(map.getVertex(3, 4));
        assertSame(map.getEdge(2, 6), edges[0]);
        assertSame(map.getEdge(3, 5), edges[1]);
        assertSame(map.getEdge(3, 7), edges[2]);

        edges = map.getNeighbouringEdges(map.getVertex(2, 5));
        assertSame(map.getEdge(1, 6), edges[0]);
        assertSame(map.getEdge(2, 7), edges[1]);
        assertSame(map.getEdge(2, 8), edges[2]);
    }

    @Test
    public void testGetVerticesOfEdge() throws Exception {
        Vertex[] vertices = map.getVerticesOfEdge(map.getEdge(2, 6));
        assertSame(map.getVertex(2, 4), vertices[0]);
        assertSame(map.getVertex(3, 4), vertices[1]);

        vertices = map.getVerticesOfEdge(map.getEdge(2, 7));
        assertSame(map.getVertex(2, 5), vertices[0]);
        assertSame(map.getVertex(2, 4), vertices[1]);

        vertices = map.getVerticesOfEdge(map.getEdge(2, 8));
        assertSame(map.getVertex(2, 5), vertices[0]);
        assertSame(map.getVertex(2, 6), vertices[1]);

        vertices = map.getVerticesOfEdge(map.getEdge(3, 3));
        assertSame(map.getVertex(3, 3), vertices[0]);
        assertSame(map.getVertex(4, 3), vertices[1]);

        vertices = map.getVerticesOfEdge(map.getEdge(3, 5));
        assertSame(map.getVertex(3, 4), vertices[0]);
        assertSame(map.getVertex(3, 3), vertices[1]);

        vertices = map.getVerticesOfEdge(map.getEdge(3, 7));
        assertSame(map.getVertex(3, 4), vertices[0]);
        assertSame(map.getVertex(3, 5), vertices[1]);
    }

    @Test
    public void testGetFieldsWithTriggerNumber() throws Exception {
        List<Field> productiveFields = map.getFieldsWithTriggerNumber(6);
        for (Field field : productiveFields) {
            assertTrue(field.getTriggerNumber() == 6);
        }
        assertSame(2, productiveFields.size());
    }

    @Test
    public void testGetFieldsCount() throws Exception {

    }
}