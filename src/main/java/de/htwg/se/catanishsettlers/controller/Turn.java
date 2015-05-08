package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.mechanic.Robber;
import de.htwg.se.catanishsettlers.model.map.Field;

/**
 * Created by Stephan on 03.04.2015.
 */
public class Turn {
    private Player activePlayer;
    private Game game;

    public Turn(Player player) {
        if (player == null) return;

        // set active player
        activePlayer = player;

        // offer to play cards
        // TODO: offer to play cards

        // roll dice
        DiceRoll roll = new DiceRoll(2);

        if (roll.getValue() == 7) {
            Field chosenField = null;
            // TODO: Make player choose field
            // TODO: resolve Field conflict
            Robber.getInstance().moveToField(chosenField);
        } else {
            game.distributeResources(roll.getValue());
        }
    }
}
