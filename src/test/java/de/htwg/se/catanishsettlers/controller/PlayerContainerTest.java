package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 30.06.2015.
 */
public class PlayerContainerTest {
    PlayerContainer playerContainer;
    Player hans;
    Player peter;

    @Before
    public void setUp() throws Exception {
        LinkedList<Player> players = new LinkedList<Player>();
        hans = new Player("Hans");
        peter = new Player("Peter");
        players.add(hans);
        players.add(peter);

        playerContainer = new PlayerContainer(players);
    }

    @Test
    public void testNext() throws Exception {
        assertSame(peter, playerContainer.next());
        assertSame(hans, playerContainer.next());
    }

    @Test
    public void testGetActivePlayer() throws Exception {
        assertSame(hans, playerContainer.getActivePlayer());
        playerContainer.next();
        assertSame(peter, playerContainer.getActivePlayer());
    }

    @Test
    public void testGetPlayers() throws Exception {
        assertEquals(2, playerContainer.getPlayers().size());
    }

    @Test
    public void testGetPlayer() throws Exception {
        assertSame(hans, playerContainer.getPlayer(0));
        assertSame(peter, playerContainer.getPlayer(1));
    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(2, playerContainer.getPlayers().size());

        playerContainer.add(new Player("Max"));
        assertEquals(3, playerContainer.getPlayers().size());
    }

    @Test
    public void testRemove() throws Exception {
        assertEquals(2, playerContainer.getPlayers().size());

        playerContainer.remove(hans);
        assertEquals(1, playerContainer.getPlayers().size());
    }
}