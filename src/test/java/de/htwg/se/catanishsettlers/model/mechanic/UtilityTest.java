package de.htwg.se.catanishsettlers.model.mechanic;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class UtilityTest {
    private Utility utility;

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
        assertTrue(Utility.getRandom() instanceof Random);
    }
}