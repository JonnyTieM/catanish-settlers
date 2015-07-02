package de.htwg.se.catanishsettlers.controller.impl;

/**
 * Created by Stephan on 05.06.2015.
 */
public class PreparationState implements IGameState {
    public void nextState(Game game) {
        for (int i = 2; i <= 12; i++) {
            if (i != 7) {
                game.distributeResources(i);
            }
        }
        game.setState(new PreDiceRollState());
    }
}
