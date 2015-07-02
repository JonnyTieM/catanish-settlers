package de.htwg.se.catanishsettlers.view.gui.preparationStateMachine;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

/**
 * Created by Stephan on 02.07.2015.
 */
public class StateMachine {

    private final Player[] players;
    private int activePlayerIndex = 0;
    private int direction = 1;

    public enum CurrentState {
        SETTLEMENT,
        ROAD
    }

    private CurrentState currentState = CurrentState.SETTLEMENT;

    public StateMachine(Player[] players) {
        this.players = players;
    }

    public Player getActivePlayer() {
        return players[activePlayerIndex];
    }

    public CurrentState getCurrentState() { return currentState; }

    public void next() {
        if (currentState == CurrentState.SETTLEMENT) {

            currentState = CurrentState.ROAD;
        } else {

            if ((activePlayerIndex == 0 && direction == -1)
                    || (activePlayerIndex == players.length - 1 && direction == 1))
                direction *= -1;

            activePlayerIndex += direction;

            currentState = currentState.SETTLEMENT;
        }
    }
}
