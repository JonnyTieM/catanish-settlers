package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;

/**
 * Created by JonnyTieM on 29.03.2015.
 */
public class Vertex extends MapObject {

    private Building building = null;

    public Vertex(int x, int y) {
        super(x, y);
    }

    public void placeBuilding(Building newBuilding) { building = newBuilding; }
    public Building getBuilding() { return  building; }
    public boolean hasBuilding() { return building != null; }
    public boolean hasSettlement() {
        if (building != null && building instanceof Settlement) {
            return true;
        }
        return false;
    }
    public boolean hasCity() {
        if (building != null && building instanceof City) {
            return true;
        }
        return false;
    }
}