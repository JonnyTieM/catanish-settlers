package de.htwg.se.catanishsettlers.view.gui;

import com.sun.javafx.css.Size;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.resources.EResource;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Stephan on 11.06.2015.
 */
public class MapPanel extends JPanel {

    //private final double scale = 20;
    private final int padding = 200;
    private final Map map;
    private final int width = 21;
    private final int height = 10;

    public MapPanel(Map map) {
        this.map = map;
    }

    public void paint(Graphics g){
        if (map == null) return;

        List<Field> fields = map.getFields();
        int maxX = 0;
        int maxY = 0;
        for(Field field : fields) {
            if (field.getX() > maxX) maxX = field.getX();
            if (field.getY() > maxY) maxY = field.getY();
        }
        int scaleX = getWidth() / maxX;
        int scaleY = getHeight() / maxY;
        int scale = Math.min(scaleX, scaleY) / fields.size();

        for(Field field : fields) {
            int midX = padding + field.getX() * width * scale;
            int midY = padding + field.getY() * height * scale;

            int[] vx = new int[6];
            int[] vy = new int[6];
            Point[] vectors = {new Point(-width, 0), new Point(-height, -height), new Point(+height, -height),
                               new Point(+width, 0), new Point(+height, +height), new Point(-height, +height)};
            Point[] vertices = new Point[6];
            for (int i = 0; i < 6; i++) {
                int x = midX + vectors[i].x;
                int y = midY + vectors[i].y;
                vx[i] = x;
                vy[i] = y;
                vertices[i] = new Point(x, y);
            }

            Color color = Color.MAGENTA;
            switch(field.getType()) {
                case BRICK: color = Color.ORANGE;
                    break;
                case LUMBER: color = Color.GREEN;
                    break;
                case WOOL: color = Color.LIGHT_GRAY;
                    break;
                case GRAIN: color = Color.YELLOW;
                    break;
                case ORE: color = Color.BLACK;
                    break;
            }
            g.setColor(color);
            g.fillPolygon(vx, vy, 6);
            g.setColor(Color.GRAY);
            g.drawString(String.valueOf(field.getTriggerNumber()), midX, midY);
        }
    }

}
