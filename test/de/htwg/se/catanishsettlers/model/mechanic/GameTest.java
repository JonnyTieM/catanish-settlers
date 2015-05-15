package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.controller.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Game testGame;
    String testName = "test player";
    Player testPlayer;

    @Before
    public void setUp() throws Exception {
        testGame = new Game();
    }

    @Test
    public void testCreatePlayer() throws Exception {
        setUp();
        Player newPlayer = testGame.createPlayer(testName);
        assertEquals(testGame.getPlayer(0), newPlayer);
    }

    @Test
    public void testGetPlayer() throws Exception {      // index access
        setUp();
        testGame.createPlayer("a");     // 0
        testGame.createPlayer("b");     // 1
        testGame.createPlayer("c");     // 2    <--
        testGame.createPlayer("d");     // 3
        assertEquals(testGame.getPlayer(2).getName(), "c");
    }

    @Test
    public void testGetPlayer1() throws Exception {     // name access
        setUp();
        assertEquals(testGame.getPlayer("test player"), null);

        Player newPlayer = testGame.createPlayer("new player");
        assertEquals(testGame.getPlayer("new player"), newPlayer);
    }

    @Test
    public void testGetActivePlayer() throws Exception {
        setUp();
        Player one = testGame.createPlayer("one");
        Player two = testGame.createPlayer("two");
        assertEquals(testGame.getActivePlayer(), one);

        testGame.switchPlayer();
        assertEquals(testGame.getActivePlayer(), two);

        testGame.switchPlayer();
        assertEquals(testGame.getActivePlayer(), one);
    }

    @Test
    public void testSwitchPlayer() throws Exception {
        setUp();
        Player one = testGame.createPlayer("one");
        Player two = testGame.createPlayer("two");
        Player three = testGame.createPlayer("three");

        testGame.switchPlayer();    // now active: two
        testGame.switchPlayer();    // now active: three
        testGame.switchPlayer();    // now active: one again
        testGame.switchPlayer();    // now active: two again
        assertEquals(testGame.getActivePlayer(), two);
    }

    @Test
    public void testGetTopCard() throws Exception {
        assertEquals(testGame.popTopCard().getClass(), Card.class);
    }
}