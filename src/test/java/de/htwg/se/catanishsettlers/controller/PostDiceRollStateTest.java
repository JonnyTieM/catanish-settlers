package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.controller.IGameState;
import de.htwg.se.catanishsettlers.controller.PostDiceRollState;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PostDiceRollStateTest {
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
        Player player = game.getActivePlayer();
        IGameState state = new PostDiceRollState();
        state.nextState(game);
        assertTrue(player != game.getActivePlayer());
    }
}