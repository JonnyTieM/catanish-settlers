package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.Grain;
import de.htwg.se.catanishsettlers.model.resources.Ore;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityTest {

    City testCity;
    Player testPlayer = new Player("test name");

    @Test
    public void setUp() throws Exception {
        testCity = new City(testPlayer);

        assertEquals(testCity.getCOST().compareTo(new ResourceCollection(new Grain(3), new Ore(2))), 0);
        assertEquals(testCity.getScore(), 2);
    }
}