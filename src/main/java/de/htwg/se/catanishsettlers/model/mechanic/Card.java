package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public class Card {
    private final static ResourceCollection COST = Config.CARD_COST;

    public enum Types {
        KNIGHT,
        PROGRESS_MONOPOLY,
        PROGRESS_ROAD_CONSTRUCTION,
        PROGRESS_DEVELOPMENT,
        VICTORYPOINT
    }

    private final Types type;

    public Card(Types type) {
        this.type = type;
    }

    public Types getType() {
        return type;
    }

    public static ResourceCollection getCOST() {
        return COST;
    }
}
