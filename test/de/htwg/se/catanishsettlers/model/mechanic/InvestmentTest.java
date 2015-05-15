package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.resources.Brick;
import de.htwg.se.catanishsettlers.model.resources.Lumber;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvestmentTest {
    Investment testInvestment;

    @Before
    public void setUp() throws Exception {
        testInvestment = new Road(null);
    }

    @Test
    public void testGetCost() throws Exception {
        assertEquals(testInvestment.getCOST().compareTo(new ResourceCollection(new Brick(2), new Lumber(1))), 0);
    }
}