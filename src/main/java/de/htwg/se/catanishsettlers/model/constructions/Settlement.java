package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class Settlement extends Building{
    public final static ResourceCollection COST = Config.SETTLEMENT_COST;
    public Settlement(Player player) {
        super(player);
        //SCORE = Config.SETTLEMENT_SCORE; //this field not needed. look it up in Config
        yield = Config.SETTLEMENT_YIELD;
        owner = player;
    }
    public static ResourceCollection getCOST() {
        return COST;
    }
}
