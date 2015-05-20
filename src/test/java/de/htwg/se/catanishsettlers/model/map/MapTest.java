package de.htwg.se.catanishsettlers.model.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JonnyTieM on 20.05.2015.
 */
public class MapTest {
    Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map();
    }

    @Test
    public void testGetField() throws Exception {
        Field field = map.getField(2, 3);
        assertEquals(2, field.getX());
        assertEquals(3, field.getY());
    }

    @Test
    public void testGetEdge() throws Exception {
        Edge edge = map.getEdge(2,6);
        assertEquals(2, edge.getX());
        assertEquals(6, edge.getY());
    }

    @Test
    public void testGetVertex() throws Exception {

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

    }

    @Test
    public void testGetBuildings() throws Exception {

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