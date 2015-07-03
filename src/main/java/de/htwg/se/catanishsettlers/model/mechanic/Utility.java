package de.htwg.se.catanishsettlers.model.mechanic;

import java.util.Random;

/**
 * Utility class. Purpose is to provide general functionality which isn't specific for any other class. Singleton.
 *
 * Created by Stephan on 03.04.2015.
 */
public final class Utility {
    private static Utility singleton;

    private final Random random;

    private Utility() {
        singleton = this;
        random = new Random();
    }

    public static Utility getInstance() {
        if (singleton == null) new Utility();
        return singleton;
    }

    public static Random getRandom() {
        return getInstance().random;
    }
}
