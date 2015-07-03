package de.htwg.se.catanishsettlers.view.gui;

import java.awt.*;

/**
 * Created by Stephan on 01.07.2015.
 */
final class GUIhelper {

    public static Point placeTextInBox(int width, int height, String text, FontMetrics fontMetrics) {

        int w = fontMetrics.stringWidth(text);
        int h = fontMetrics.getHeight();
        int x = width / 2 - w / 2;   // position text in center of cube
        int y = height / 2 + h / 4;   // position text in center of cube

        return new Point(x, y);
    }
}
