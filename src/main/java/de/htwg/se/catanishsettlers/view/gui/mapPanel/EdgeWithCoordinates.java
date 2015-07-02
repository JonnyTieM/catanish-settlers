package de.htwg.se.catanishsettlers.view.gui.mapPanel;

import de.htwg.se.catanishsettlers.model.map.Edge;

/**
 * Created by Stephan on 02.07.2015.
 */
class EdgeWithCoordinates {
    public final int x1, y1, x2, y2;
    public final Edge edge;

    public EdgeWithCoordinates(Edge edge, int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.edge = edge;
    }
}