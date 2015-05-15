package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class Road extends Construction {

    public Road(Player player) {
        super(player);
        COST = new ResourceCollection(2, 1, 0, 0, 0);
    }
}
