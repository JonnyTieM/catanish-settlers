package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.model.map.Field;

/**
 * Created by Stephan on 17.04.2015.
 */
public class Robber {
    private static Robber singleton;
    private de.htwg.se.catanishsettlers.model.map.Field field;

    private Robber() { singleton = this; }

    public static Robber getInstance()
    {
        if (singleton == null) new Robber();
        return singleton;
    }

    public Field getField() { return field; }
    public void moveToField(Field newField) { field = newField; }
}
