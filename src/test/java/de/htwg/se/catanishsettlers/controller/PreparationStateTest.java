package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.controller.PreparationState;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 02.07.2015.
 */
public class PreparationStateTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        LinkedList<Player> players = new LinkedList<Player>();
        players.add(new Player("Hans"));
        players.add(new Player("Peter"));
        game = new Game(players);
    }

    @Test
    public void testNextState() throws Exception {
        game.nextPhase();
        assertTrue(game.isPreparationPhase());
        PreparationState state = new PreparationState();
        state.nextState(game);
        assertFalse(game.isPreparationPhase());
    }
}