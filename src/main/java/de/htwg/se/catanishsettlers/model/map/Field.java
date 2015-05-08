package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.resources.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JonnyTieM on 29.03.2015.
 */
public class Field extends MapObject {
    //TODO: add enum for different field types

    private EResource sort;
    private int triggerNumber;

    public Field(int x, int y) {
        super(x, y);
        //TODO: remove this constructer. Ensure that every field has a proper resource type.
    }

    public Field(EResource sort, int x, int y) {
        this(x,y);

        if (sort == null) throw new IllegalArgumentException("Only Brick, Lumber, Wool, Grain or Ore allowed");
        this.sort = sort;
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
