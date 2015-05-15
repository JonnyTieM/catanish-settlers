package de.htwg.se.catanishsettlers.model.mechanic;

/**
 * Created by Stephan on 17.04.2015.
 */
public class Robber {
    private static Robber singleton;

    private Robber() { singleton = this; }

    public static Robber getInstance()
    {
        if (singleton == null) new Robber();
        return singleton;
    }
}
