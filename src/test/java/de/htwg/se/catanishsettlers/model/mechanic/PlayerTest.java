package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class PlayerTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("Peter");
    }

    @Test
    public void testGetName() throws Exception {
        assertTrue(player.getName().contentEquals("Peter"));
    }

    @Test
    public void testPlayCard() throws Exception {
        assertFalse(player.playCard(Card.Types.KNIGHT));

        player.addCard(new Card(Card.Types.KNIGHT));
        assertTrue(player.playCard(Card.Types.KNIGHT));
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(0, player.getScore());

        player.settlements -= 2;
        assertEquals(2, player.getScore());
    }

    @Test
    public void testGetKnightCount() throws Exception {
        assertEquals(0, player.getKnightCount());

        player.addCard(new Card(Card.Types.KNIGHT));
        player.playCard(Card.Types.KNIGHT);
        assertEquals(1, player.getKnightCount());
    }

    @Test
    public void testAddResources() throws Exception {
        player.addResources(new ResourceCollection(2, 3, 4, 5, 6));
        assertTrue(player.getResources().compareTo(new ResourceCollection(2, 3, 4, 5, 6)) == 0);
    }

    @Test
    public void testGetResources() throws Exception {
        player.addResources(new ResourceCollection(2, 3, 4, 5, 6));
        assertTrue(player.getResources().compareTo(new ResourceCollection(2, 3, 4, 5, 6)) == 0);
    }

    @Test
    public void testAddCard() throws Exception {
        assertFalse(player.playCard(Card.Types.KNIGHT));

        player.addCard(new Card(Card.Types.KNIGHT));
        assertTrue(player.playCard(Card.Types.KNIGHT));
    }

    @Test
    public void testHasEnoughResources() throws Exception {
        assertFalse(player.hasEnoughResources(new ResourceCollection(2, 3, 4, 5, 6)));

        player.addResources(new ResourceCollection(2, 3, 3, 5, 6));
        assertFalse(player.hasEnoughResources(new ResourceCollection(2, 3, 4, 5, 6)));

        player.addResources(new ResourceCollection(0, 0, 1, 0, 0));
        assertTrue(player.hasEnoughResources(new ResourceCollection(2, 3, 4, 5, 6)));

        player.addResources(new ResourceCollection(1, 1, 1, 1, 1));
        assertTrue(player.hasEnoughResources(new ResourceCollection(2, 3, 4, 5, 6)));
    }

    @Test
    public void testPayCost() throws Exception {
        assertFalse(player.payCost(new ResourceCollection(2, 3, 4, 5, 6)));

        player.addResources(new ResourceCollection(2, 3, 4, 5, 6));
        assertTrue(player.payCost(new ResourceCollection(2, 3, 4, 5, 6)));
    }
}