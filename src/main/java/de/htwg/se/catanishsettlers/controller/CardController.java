package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

/**
 * Created by Stephan on 15.05.2015.
 */
public final class CardController {

    private CardController() {}

    public void drawCard(Player player, Game game) {
        player.addCard(game.popTopCard());
    }
}
