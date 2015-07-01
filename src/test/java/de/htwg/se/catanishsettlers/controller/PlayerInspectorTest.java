package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 01.07.2015.
 */
public class PlayerInspectorTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("Hans");
    }

    @Test
    public void testCanBuildSettlement() throws Exception {
        assertFalse(PlayerInspector.canBuildSettlement(player));

        player.addResources(new ResourceCollection(2,2,2,2,2));
        assertTrue(PlayerInspector.canBuildSettlement(player));
    }

    @Test
    public void testCanBuildCity() throws Exception {

    }

    @Test
    public void testCanBuildRoad() throws Exception {

    }
}