package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.map.Field;

/**
 * Created by Stephan on 02.07.2015.
 */
class FieldWithCoordinates {
    public final Field field;
    public final int[] vx, vy;

    public FieldWithCoordinates(Field field, int[] vx, int[] vy) {
        this.field = field;
        this.vx = vx;
        this.vy = vy;
    }
}
