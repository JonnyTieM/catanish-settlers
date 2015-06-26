package de.htwg.se.catanishsettlers.model.resources;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 26.06.2015.
 */
public class ResourceCollectionTest {
    ResourceCollection rc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetBrick() throws Exception {
        rc = new ResourceCollection(2,3,4,5,6);
        assertEquals(2, rc.getBrick());
    }

    @Test
    public void testGetLumber() throws Exception {
        rc = new ResourceCollection(2,3,4,5,6);
        assertEquals(3, rc.getLumber());
    }

    @Test
    public void testGetWool() throws Exception {
        rc = new ResourceCollection(2,3,4,5,6);
        assertEquals(4, rc.getWool());
    }

    @Test
    public void testGetGrain() throws Exception {
        rc = new ResourceCollection(2,3,4,5,6);
        assertEquals(5, rc.getGrain());
    }

    @Test
    public void testGetOre() throws Exception {
        rc = new ResourceCollection(2,3,4,5,6);
        assertEquals(6, rc.getOre());
    }

    @Test
    public void testAdd() throws Exception {
        rc = new ResourceCollection(2,3,4,5,6);
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

    }

    @Test
    public void testAdd2() throws Exception {

    }

    @Test
    public void testSubtract() throws Exception {

    }

    @Test
    public void testSubtract1() throws Exception {

    }

    @Test
    public void testSubtract2() throws Exception {

    }

    @Test
    public void testGetTotal() throws Exception {

    }

    @Test
    public void testIsNotNegative() throws Exception {

    }

    @Test
    public void testCompareTo() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}