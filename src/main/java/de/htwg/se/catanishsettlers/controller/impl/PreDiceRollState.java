package de.htwg.se.catanishsettlers.controller.impl;

import de.htwg.se.catanishsettlers.model.mechanic.Dice;

/**
 * Created by Stephan on 05.06.2015.
 */
public class PreDiceRollState implements IGameState {

    public PreDiceRollState() {
    }

    public void nextState(Game game) {
        Dice dice = game.getDice();
        dice.roll();
        distributeResources(dice.getValue(), game);

        game.setState(new PostDiceRollState());
    }

    private void distributeResources(int rolledNumber, Game game) {
        if (rolledNumber == 7) {
            return;
        }
        game.distributeResources(rolledNumber);
    }
}
