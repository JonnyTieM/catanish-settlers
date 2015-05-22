package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.resources.EResource;

/**
 * Created by JonnyTieM on 29.03.2015.
 */
public class Field extends MapObject {
    //TODO: add enum for different field types

    private EResource sort;
    private int triggerNumber;

    public Field(int x, int y, EResource sort, int trigger) {
        super(x, y);

        if (sort == null) throw new IllegalArgumentException("Only Brick, Lumber, Wool, Grain or Ore allowed");
        this.sort = sort;
        triggerNumber = trigger;
    }

    public EResource getType() {
        return sort;
    }

    public int getTriggerNumber() {
        return triggerNumber;
    }

//    public List<Building> getSurroundingBuildings() {
//        List<Building> surroundingBuildings = new LinkedList<Building>();
//        for(Vertex vertex : getSurroundingVertices()) {
//            Building building = vertex.getBuilding();
//            if (building != null) surroundingBuildings.add(building);
//        }
//        return surroundingBuildings;
//    }
}
