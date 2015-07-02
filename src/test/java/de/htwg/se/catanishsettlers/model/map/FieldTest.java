package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.resources.EResource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    Field field;

    @Before
    public void setUp() throws Exception {
        field = new Field(2, 2, EResource.BRICK, 6);
    }

    @Test
    public void testGetResourceType() throws Exception {
        assertTrue(field.getResourceType() == EResource.BRICK);
    }

    @Test
    public void testGetTriggerNumber() throws Exception {
        assertTrue(field.getTriggerNumber() == 6);
    }
}