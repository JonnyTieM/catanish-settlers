package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 01.07.2015.
 */
public class GameTest {
    Game game;
    Player hans;
    Player peter;

    @Before
    public void setUp() throws Exception {
        hans = new Player("Hans");
        peter = new Player("Peter");
        LinkedList<Player> players = new LinkedList<Player>();
        players.add(hans);
        players.add(peter);
        game = new Game(players);
    }

    @Test
    public void testSwitchPlayer() throws Exception {
        assertSame(hans, game.getActivePlayer());

        game.switchPlayer();
        assertSame(peter, game.getActivePlayer());
    }

    @Test
    public void testBuildFirstSettlementWithRoad() throws Exception {
        game.nextPhase();
        assertTrue(game.buildFirstSettlementWithRoad(hans,2,3,2,5));
        game.nextPhase();
        assertFalse(game.buildFirstSettlementWithRoad(peter,2,6,2,9));
    }

    @Test
    public void testBuildFirstSettlement() throws Exception {

    }

    @Test
    public void testBuildFirstRoad() throws Exception {

    }

    @Test
    public void testBuildSettlement() throws Exception {

    }

    @Test
    public void testBuildCity() throws Exception {

    }

    @Test
    public void testBuildRoad() throws Exception {

    }

    @Test
    public void testDistributeResources() throws Exception {

    }

    @Test
    public void testCheckVictory() throws Exception {

    }

    @Test
    public void testBuyCard() throws Exception {

    }

    @Test
    public void testGetLastRolledDiceNumber() throws Exception {

    }

    @Test
    public void testGetDice() throws Exception {

    }

    @Test
    public void testIsThereAWinner() throws Exception {

    }

    @Test
    public void testIsBuildingPhase() throws Exception {

    }

    @Test
    public void testIsPreparationPhase() throws Exception {

    }

    @Test
    public void testGetPlayer() throws Exception {

    }

    @Test
    public void testSetState() throws Exception {

    }

    @Test
    public void testGetMap() throws Exception {

    }

    @Test
    public void testGetPlayerContainer() throws Exception {

    }

    @Test
    public void testGetActivePlayer() throws Exception {

    }

    @Test
    public void testNextPhase() throws Exception {

    }
}