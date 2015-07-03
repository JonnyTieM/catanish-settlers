package de.htwg.se.catanishsettlers.model.resources;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 26.06.2015.
 */
public class ResourceCollectionTest {
    private ResourceCollection rc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetBrick() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertEquals(2, rc.getBrick());
    }

    @Test
    public void testGetLumber() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertEquals(3, rc.getLumber());
    }

    @Test
    public void testGetWool() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertEquals(4, rc.getWool());
    }

    @Test
    public void testGetGrain() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertEquals(5, rc.getGrain());
    }

    @Test
    public void testGetOre() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertEquals(6, rc.getOre());
    }

    @Test
    public void testAdd() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        rc.add(EResource.BRICK);
        rc.add(EResource.LUMBER);
        rc.add(EResource.LUMBER);
        rc.add(EResource.LUMBER);
        rc.add(EResource.WOOL);
        rc.add(EResource.WOOL);
        rc.add(EResource.WOOL);
        rc.add(EResource.WOOL);
        rc.add(EResource.GRAIN);
        rc.add(EResource.GRAIN);
        rc.add(EResource.ORE);
        rc.add(EResource.ORE);
        rc.add(EResource.ORE);
        rc.add(EResource.ORE);
        rc.add(EResource.ORE);

        assertEquals(3, rc.getBrick());
        assertEquals(6, rc.getLumber());
        assertEquals(8, rc.getWool());
        assertEquals(7, rc.getGrain());
        assertEquals(11, rc.getOre());
    }

    @Test
    public void testAdd1() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        rc.add(EResource.BRICK, 1);
        rc.add(EResource.LUMBER, 3);
        rc.add(EResource.WOOL, 4);
        rc.add(EResource.GRAIN, 2);
        rc.add(EResource.ORE, 5);

        assertEquals(3, rc.getBrick());
        assertEquals(6, rc.getLumber());
        assertEquals(8, rc.getWool());
        assertEquals(7, rc.getGrain());
        assertEquals(11, rc.getOre());
    }

    @Test
    public void testAdd2() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        ResourceCollection rc2 = new ResourceCollection(1, 3, 4, 2, 5);
        rc.add(rc2);

        assertEquals(3, rc.getBrick());
        assertEquals(6, rc.getLumber());
        assertEquals(8, rc.getWool());
        assertEquals(7, rc.getGrain());
        assertEquals(11, rc.getOre());
    }

    @Test
    public void testSubtract() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        rc.subtract(EResource.BRICK);
        rc.subtract(EResource.LUMBER);
        rc.subtract(EResource.WOOL);
        rc.subtract(EResource.WOOL);
        rc.subtract(EResource.WOOL);
        rc.subtract(EResource.GRAIN);
        rc.subtract(EResource.GRAIN);
        rc.subtract(EResource.ORE);
        rc.subtract(EResource.ORE);
        rc.subtract(EResource.ORE);

        assertEquals(1, rc.getBrick());
        assertEquals(2, rc.getLumber());
        assertEquals(1, rc.getWool());
        assertEquals(3, rc.getGrain());
        assertEquals(3, rc.getOre());
    }

    @Test
    public void testSubtract1() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        rc.subtract(EResource.BRICK, 1);
        rc.subtract(EResource.LUMBER, 1);
        rc.subtract(EResource.WOOL, 3);
        rc.subtract(EResource.GRAIN, 2);
        rc.subtract(EResource.ORE, 3);

        assertEquals(1, rc.getBrick());
        assertEquals(2, rc.getLumber());
        assertEquals(1, rc.getWool());
        assertEquals(3, rc.getGrain());
        assertEquals(3, rc.getOre());
    }

    @Test
    public void testSubtract2() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        ResourceCollection rc2 = new ResourceCollection(1, 1, 3, 2, 3);
        rc.subtract(rc2);

        assertEquals(1, rc.getBrick());
        assertEquals(2, rc.getLumber());
        assertEquals(1, rc.getWool());
        assertEquals(3, rc.getGrain());
        assertEquals(3, rc.getOre());
    }

    @Test
    public void testGetTotal() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);

        assertEquals(20, rc.getTotal());
    }

    @Test
    public void testIsNotNegative() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertTrue(rc.isNotNegative());

        rc = new ResourceCollection(2, 3, 4, -2, 6);
        assertFalse(rc.isNotNegative());
    }

    @Test
    public void testCompareTo() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        ResourceCollection rc2 = new ResourceCollection(4, 5, 5, 7, 7);
        ResourceCollection rc3 = new ResourceCollection(2, 3, 4, 5, 6);
        ResourceCollection rc4 = new ResourceCollection(2, 3, 4, 1, 6);

        assertEquals(-1, rc.compareTo(rc2));
        assertEquals(0, rc.compareTo(rc3));
        assertEquals(1, rc.compareTo(rc4));
    }

    @Test
    public void testToString() throws Exception {
        rc = new ResourceCollection(2, 3, 4, 5, 6);
        assertTrue(rc.toString().contentEquals("Brick: 2" + System.lineSeparator() + "Lumber: 3"
                + System.lineSeparator() + "Wool: 4" + System.lineSeparator()
                + "Grain: 5" + System.lineSeparator() + "Ore: 6" + System.lineSeparator()));
    }
}