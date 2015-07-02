package de.htwg.se.catanishsettlers.controller.impl;

/**
 * Created by Stephan on 01.07.2015.
 */
public class GameSetupState implements IGameState {
    public void nextState(Game game) {
        game.setState(new PreparationState());
    }
}
