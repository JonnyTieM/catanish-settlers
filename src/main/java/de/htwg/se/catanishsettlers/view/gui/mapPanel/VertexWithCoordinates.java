package de.htwg.se.catanishsettlers.view.gui.mapPanel;

import de.htwg.se.catanishsettlers.model.map.Vertex;

/**
 * Created by Stephan on 02.07.2015.
 */
class VertexWithCoordinates {
    public final Vertex vertex;
    public final int x, y;

    public VertexWithCoordinates(Vertex vertex, int x, int y) {
        this.vertex = vertex;
        this.x = x;
        this.y = y;
    }
}