package de.htwg.se.catanishsettlers.modules.map;

/**
 * Created by JonnyTieM on 29.03.2015.
 */
public abstract class MapObject {
    private final int x;
    private final int y;

    public MapObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() {
        return y;
    }
}
