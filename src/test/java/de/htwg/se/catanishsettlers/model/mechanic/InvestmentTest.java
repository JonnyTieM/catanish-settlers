package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class InvestmentTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetCOST() throws Exception {
        Investment investment = new Card(Card.Types.KNIGHT);
        assertTrue(investment.getCOST().compareTo(new ResourceCollection(0,0,1,1,1)) == 0);
    }
}