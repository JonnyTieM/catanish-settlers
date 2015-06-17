package de.htwg.se.catanishsettlers.controller;

/**
 * Created by Stephan on 05.06.2015.
 */
public class PostDiceRollState implements IGameState {
    public void nextState(Game game) {
        game.switchPlayer();
        game.setState(new PreDiceRollState());
    }
}
