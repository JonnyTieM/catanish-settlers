package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 26.06.2015.
 */
public class ConstructionTest {
    Player player;
    Construction construction;

    @Before
    public void setUp() throws Exception {
        player = new Player("Hans");
        construction = new Road(player);
    }

    @Test
    public void testGetPlayer() throws Exception {
        assertSame(player, construction.getPlayer());
    }
}