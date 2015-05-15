package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.constructions.Road;

/**
 * Created by JonnyTieM on 30.03.2015.
 */
public class Edge extends MapObject {

    private Road road = null;

    public Edge(int x, int y) {
        super(x, y);
    }

    public Road getRoad() { return road; }
    public boolean hasRoad() { return road != null; }
    public void buildRoad(Road newRoad) { road = newRoad; }
}