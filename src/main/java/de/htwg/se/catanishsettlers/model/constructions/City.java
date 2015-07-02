package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class City extends Building{
    public final static ResourceCollection COST = Config.CITY_COST;
    public City(Player player) {
        super(player);
        //SCORE = Config.CITY_SCORE; //this field not needed. look it up in Config
        yield = Config.CITY_YIELD;
        owner = player;
    }
    public static ResourceCollection getCOST() {
        return COST;
    }
}
