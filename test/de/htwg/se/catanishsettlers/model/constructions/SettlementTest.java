package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettlementTest {

    Settlement testSettlement;
    Player testPlayer = new Player("test name");

    @Test
    public void setUp() throws Exception {
        testSettlement = new Settlement(testPlayer);

        ResourceCollection settlementCosts = new ResourceCollection(new Brick(1), new Lumber(1), new Wool(1), new Grain(1));
        assertEquals(testSettlement.getCOST().compareTo(settlementCosts), 0);
        assertEquals(testSettlement.getScore(), 1);
    }
}