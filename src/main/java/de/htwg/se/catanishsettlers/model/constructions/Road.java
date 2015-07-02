package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class Road extends Construction {
    public final static ResourceCollection COST = Config.ROAD_COST;

    public Road(Player player) {
        super(player);
        owner = player;
    }
    public static ResourceCollection getCOST() {
        return COST;
    }
}
