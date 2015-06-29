package de.htwg.se.catanishsettlers.model.mechanic;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class UtilityTest {
    Utility utility;

    @Before
    public void setUp() throws Exception {
        utility = Utility.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        utility = Utility.getInstance();
        assertTrue(utility instanceof  Utility);
    }

    @Test
    public void testGetRandom() throws Exception {
        assertTrue(utility.getRandom() instanceof Random);
    }

    @Test
    public void testPlaceTextInBox() throws Exception {
        //TODO: write test for this one
    }
}