package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class Road extends Construction {

    public Road(Player player) {
        super(player);
        COST = Config.ROAD_COST;
        owner = player;
    }
}
