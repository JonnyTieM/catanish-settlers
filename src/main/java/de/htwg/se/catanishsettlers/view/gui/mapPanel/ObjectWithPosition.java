package de.htwg.se.catanishsettlers.view.gui.mapPanel;

import de.htwg.se.catanishsettlers.model.map.MapObject;

/**
 * Created by Stephan on 02.07.2015.
 */
class ObjectWithPosition {
    public final MapObject object;
    public final int x, y;

    public ObjectWithPosition(MapObject object, int x, int y) {
        this.object = object;
        this.x = x;
        this.y = y;
    }
}