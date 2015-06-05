package de.htwg.se.catanishsettlers.controller.states;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.controller.IGameState;

/**
 * Created by Stephan on 05.06.2015.
 */
public class PreparationState implements IGameState {
    public void nextState(Game game) {
        game.setState(new PreDiceRollState());
    }
}
