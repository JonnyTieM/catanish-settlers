package de.htwg.se.catanishsettlers.model.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by JonnyTieM on 29.03.2015.
 */
public class MapObjectTest {
    MapObject mapObj;

    @Before
    public void setUp() throws Exception {
        mapObj = new Edge(2, 5);
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals(2, mapObj.getX());
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals(5, mapObj.getY());
    }
}