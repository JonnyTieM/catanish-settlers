package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 01.04.2015.
 */
public class Card extends Investment {

    public enum Types {
        KNIGHT,
        PROGRESS_MONOPOLY,
        PROGRESS_ROAD_CONSTRUCTION,
        PROGRESS_DEVELOPMENT,
        VICTORYPOINT
    }

    private Types type;

    public Card(Types type) {
        this.type = type;
        COST = new ResourceCollection(0, 0, 1, 1, 1);
    }

    public Types getType() {
        return type;
    }
}
