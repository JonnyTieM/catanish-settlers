package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 02.04.2015.
 */
public abstract class Investment {
    public static ResourceCollection COST;

    public static ResourceCollection getCOST() {
        return COST;
    }
}
