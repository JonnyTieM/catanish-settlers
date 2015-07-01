package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.Config;
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

        player.addResources(new ResourceCollection(3, 3, 3, 3, 3));
        assertTrue(PlayerInspector.canBuildSettlement(player));

        for (int i = 0; i < Config.MAX_SETTLEMENTS; i++) {
            player.decreaseSettlements();
        }
        assertFalse(PlayerInspector.canBuildSettlement(player));
    }

    @Test
    public void testCanBuildCity() throws Exception {
        assertFalse(PlayerInspector.canBuildCity(player));

        player.addResources(new ResourceCollection(3, 3, 3, 3, 3));
        assertTrue(PlayerInspector.canBuildCity(player));

        for (int i = 0; i < Config.MAX_CITIES; i++) {
            player.decreaseCities();
        }
        assertFalse(PlayerInspector.canBuildCity(player));
    }

    @Test
    public void testCanBuildRoad() throws Exception {
        assertFalse(PlayerInspector.canBuildRoad(player));

        player.addResources(new ResourceCollection(3, 3, 3, 3, 3));
        assertTrue(PlayerInspector.canBuildRoad(player));

        for (int i = 0; i < Config.MAX_ROADS; i++) {
            player.decreaseRoads();
        }
        assertFalse(PlayerInspector.canBuildRoad(player));
    }
}