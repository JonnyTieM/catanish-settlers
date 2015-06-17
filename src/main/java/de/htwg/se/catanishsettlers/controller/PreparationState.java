package de.htwg.se.catanishsettlers.controller;

/**
 * Created by Stephan on 05.06.2015.
 */
public class PreparationState implements IGameState {
    public void nextState(Game game) {
        game.setState(new PreDiceRollState());
    }
}
