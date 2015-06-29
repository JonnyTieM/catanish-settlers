package de.htwg.se.catanishsettlers.model.mechanic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class RobberTest {
    Robber robber;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetInstance() throws Exception {
        robber = Robber.getInstance();
        assertTrue(robber instanceof Robber);
    }
}