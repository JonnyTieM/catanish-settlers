package de.htwg.se.catanishsettlers.controller.states;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.controller.IGameState;
import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;

/**
 * Created by Stephan on 05.06.2015.
 */
public class DiceRollState implements IGameState {
    boolean rolledDiceThisTurn;

    public DiceRollState() {
        rolledDiceThisTurn = false;
    }

    public void nextState(Game game) {
        if (!rolledDiceThisTurn) {
            DiceRoll dice = new DiceRoll(2);
            int rolledNumber = dice.getValue();
            game.setLastRolledDiceNumber(rolledNumber);
            distributeResources(rolledNumber, game);
            rolledDiceThisTurn = true;
        }
        game.setState(new PostDiceRollState());
    }

    private void distributeResources(int rolledNumber, Game game) {
        if (rolledNumber == 7) {
            return;
        }
        game.distributeResources(rolledNumber);
    }
}
