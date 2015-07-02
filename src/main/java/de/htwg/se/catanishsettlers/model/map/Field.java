package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.resources.EResource;

/**
 * Created by JonnyTieM on 29.03.2015.
 */
public final class Field extends MapObject {

    private final EResource resourceType;
    private final int triggerNumber;

    public Field(int x, int y, EResource resourceType, int triggerNumber) {
        super(x, y);

        if (resourceType == null) {
            throw new IllegalArgumentException("Only Brick, Lumber, Wool, Grain or Ore allowed");
        }
        this.resourceType = resourceType;
        this.triggerNumber = triggerNumber;
    }

    public EResource getResourceType() {
        return resourceType;
    }

    public int getTriggerNumber() {
        return triggerNumber;
    }
}
