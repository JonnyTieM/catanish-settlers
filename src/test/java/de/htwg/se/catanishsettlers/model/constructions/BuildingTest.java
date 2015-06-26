package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 26.06.2015.
 */
public class BuildingTest {
    Building building;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetYield() throws Exception {
        building = new Settlement(new Player("Hans"));
        assertEquals(Config.SETTLEMENT_YIELD, building.getYield());
        building = new City(new Player("Peter"));
        assertEquals(Config.CITY_YIELD, building.getYield());
    }
}