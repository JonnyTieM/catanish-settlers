package de.htwg.se.catanishsettlers.view.gui.statusPanel;

import de.htwg.se.catanishsettlers.model.mechanic.Utility;
import de.htwg.se.catanishsettlers.view.gui.GUIhelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 23.06.2015.
 */
public class SingleDicePanel extends JPanel {
    private int value;
    private final int cubeLength = 50;
    private final int padding = 5;
    private final int height3D = 20;

    public SingleDicePanel() {
        setPreferredSize(new Dimension(100, 100));
        setVisible(false);
    }

    public void setValue(int x) {
        value = x;
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        String text = String.valueOf(value);

        g.drawRect(padding, padding + height3D,
                cubeLength, cubeLength);
        g.drawLine(padding, padding + height3D,
                padding + height3D, padding);
        g.drawLine(cubeLength + padding, padding + height3D,
                cubeLength + padding + height3D, padding);
        g.drawLine(cubeLength + padding, cubeLength + padding + height3D,
                cubeLength + padding + height3D, cubeLength + padding);
        g.drawLine(padding + height3D, padding,
                cubeLength + padding + height3D, padding);
        g.drawLine(cubeLength + padding + height3D, padding,
                cubeLength + padding + height3D, cubeLength + padding);

        Point textPosition = GUIhelper.placeTextInBox(cubeLength, cubeLength, text, g.getFontMetrics());
        g.drawString(text, padding + textPosition.x, padding + height3D + textPosition.y);
    }
}
