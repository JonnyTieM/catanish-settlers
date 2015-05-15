package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.Brick;
import de.htwg.se.catanishsettlers.model.resources.Lumber;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoadTest {

    Road testRoad;
    Player testPlayer = new Player("test name");

    @Test
    public void setUp() throws Exception {
        testRoad = new Road(testPlayer);

        ResourceCollection roadCost = new ResourceCollection(new Brick(2), new Lumber(1));
        assertEquals(testRoad.getCOST().compareTo(roadCost), 0);
    }
}