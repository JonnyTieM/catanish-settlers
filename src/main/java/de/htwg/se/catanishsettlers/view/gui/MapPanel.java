package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Stephan on 11.06.2015.
 */
public class MapPanel extends JPanel {

    private double scale = 20;
    private int padding = 20;
    private Map map;

    public MapPanel(Map map) {
        this.map = map;
    }

    public void paint(Graphics g){
        if (map == null) return;
        List<Vertex> vertices = map.getVertices();
        for (Vertex vertex : vertices) {
            int x = padding + (int)(vertex.getX() * scale);
            int y = padding + (int)(vertex.getY() * scale);
            g.drawOval(x, y, 2, 2);
        }

        List<Edge> edges = map.getEdges();
        for (Edge edge : edges) {
            int x1 = padding + (int)(edge.getX() * scale);
            int y1 = padding + (int)(edge.getY() * scale);
            //TODO: get x2, y2
        }
    }

}
