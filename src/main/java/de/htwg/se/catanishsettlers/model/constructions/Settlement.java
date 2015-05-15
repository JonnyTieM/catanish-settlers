package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class Settlement extends Building{
    public Settlement(Player player) {
        super(player);
        COST = Config.SETTLEMENT_COST;
        SCORE = Config.SETTLEMENT_SCORE;
        yield = Config.SETTLEMENT_YIELD;
        owner = player;
    }
}
