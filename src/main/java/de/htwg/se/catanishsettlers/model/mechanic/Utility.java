package de.htwg.se.catanishsettlers.model.mechanic;

import java.awt.*;
import java.util.Random;

/**
 * Utility class. Purpose is to provide general functionality which isn't specific for any other class. Singleton.
 *
 * Created by Stephan on 03.04.2015.
 */
public final class Utility {
    private static Utility singleton;

    private Random random;

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

    public static Point placeTextInBox(int width, int height, String text, FontMetrics fontMetrics) {

        int w = fontMetrics.stringWidth(text);
        int h = fontMetrics.getHeight();
        int x = width / 2 - w / 2;   // position text in center of cube
        int y = height / 2 + h / 4;   // position text in center of cube

        return new Point(x, y);
    }
}
