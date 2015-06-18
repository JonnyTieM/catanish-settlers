package de.htwg.se.catanishsettlers.view.gui;

import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stephan on 11.06.2015.
 */
public class MapPanel extends JPanel implements MouseMotionListener {

    //private final double scale = 20;
    private final int padding = 190;
    private Map map;
    private final int width = 2;
    private final int height = 2;
    private final int side = 1;
    private Label debugLabel = new Label();
    private List<ObjectWithPosition> objects = new LinkedList<ObjectWithPosition>();

    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
        ObjectWithPosition best = objects.get(0);
        int x = best.position.x - e.getX();
        int y = best.position.y - e.getY();
        double minDistance = Math.sqrt(x * x + y * y);

        for(ObjectWithPosition object : objects) {
            x = object.position.x - e.getX();
            y = object.position.y - e.getY();
            double distance = Math.sqrt(x * x + y * y);
            if (distance < minDistance) {
                best = object;
                minDistance = distance;
            }
        }

        String text = "";
        if (best.object.getClass().equals(Field.class)) text += "Feld";
        if (best.object.getClass().equals(EdgeWithCoordinates.class)) text += "Kante";
        if (best.object.getClass().equals(VertexWithCoordinates.class)) text += "Ecke";

        debugLabel.setText("Maus ist bei: " + text);
    }

    private class VertexWithCoordinates {
        public final int x, y;
        public final Vertex vertex;

        public VertexWithCoordinates(Vertex vertex, int x, int y) {
            this.x = x;
            this.y = y;
            this.vertex = vertex;
        }
    }

    private class EdgeWithCoordinates {
        public final int x1, y1, x2, y2;
        public final Edge edge;

        public EdgeWithCoordinates(Edge edge, int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.edge = edge;
        }
    }

    private class ObjectWithPosition {
        public final Object object;
        public final Point position;

        public ObjectWithPosition(Object object) {
            this.object = object;
            int x = 0;
            int y = 0;
            if (object.getClass().equals(EdgeWithCoordinates.class)) {
                EdgeWithCoordinates edge = (EdgeWithCoordinates)object;
                x = edge.x2 - edge.x1;
                y = edge.y2 - edge.y1;
            }
            if (object.getClass().equals(VertexWithCoordinates.class)) {
                VertexWithCoordinates vertex = (VertexWithCoordinates)object;
                x = vertex.x;
                y = vertex.y;
            }
            if (object.getClass().equals(Field.class)) {
                Field field = (Field)object;
                x = field.getX();
                y = field.getY();
            }
            this.position = new Point(x, y);
        }
    }

    public MapPanel(Map map) {
        this.map = map;
        debugLabel.setText("debug ready");
        add(debugLabel);
        addMouseMotionListener(this);
        for(Field field : map.getFields()) objects.add(new ObjectWithPosition(field));
        for(Vertex vertex : map.getVertices()) objects.add(new ObjectWithPosition(vertex));
        for(Edge edge : map.getEdges()) objects.add(new ObjectWithPosition(edge));
    }

    public void paint(Graphics g) {
        if (map == null) return;

        debugLabel.setText("paint() was called");

        List<VertexWithCoordinates> vertices = new LinkedList<VertexWithCoordinates>();
        List<EdgeWithCoordinates> edges = new LinkedList<EdgeWithCoordinates>();
        List<Field> fields = map.getFields();

        int scale = getScale(fields);

        for (Field field : fields) {
            int midX = padding + field.getX() * (width + side) * scale;
            int midY = padding + field.getY() * (height * 1) * scale;
            if (field.getX() % 2 == 1) midY += (height / 2) * scale;

            int[] vx = new int[6];
            int[] vy = new int[6];
            Point[] vectors = {new Point(-width, -height), new Point(+width, -height), new Point(+width +side * 2, 0),
                               new Point(+width, +height), new Point(-width, +height), new Point(-width -side * 2, 0)};

            VertexWithCoordinates lastVertex = new VertexWithCoordinates(null, 0, 0);
            VertexWithCoordinates firstVertex = new VertexWithCoordinates(null, 0, 0);
            for (int i = 0; i < 6; i++) {
                int x = midX + vectors[i].x * scale / 2;
                int y = midY + vectors[i].y * scale / 2;
                vx[i] = x;
                vy[i] = y;

                VertexWithCoordinates vertex = new VertexWithCoordinates(map.getVertices(field)[i], x, y);
                vertices.add(vertex);
                if (i == 0) {
                    firstVertex = vertex;
                } else {
                    edges.add(new EdgeWithCoordinates(map.getEdges(field)[i], lastVertex.x, lastVertex.y, x, y));
                }
                lastVertex = vertex;
            }
            edges.add(new EdgeWithCoordinates(map.getEdges(field)[0], lastVertex.x, lastVertex.y, firstVertex.x, firstVertex.y));

            g.setColor(getColor(field));
            g.fillPolygon(vx, vy, 6);
            g.setColor(Color.GRAY);
            g.drawString(String.valueOf(field.getTriggerNumber()), midX, midY);
        }

        Graphics2D g2 = (Graphics2D)g;
        for (EdgeWithCoordinates edge : edges) {

            if(edge.edge.hasRoad()) {
                g2.setStroke(new BasicStroke(4));
                g.setColor(edge.edge.getRoad().getPlayer().color);
            } else {
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.GRAY);
            }
            g.drawLine(edge.x1, edge.y1, edge.x2, edge.y2);
        }

        for (VertexWithCoordinates vertex : vertices) {
            if (vertex.vertex.hasBuilding()) g.setColor(vertex.vertex.getBuilding().getPlayer().color);
            if (vertex.vertex.hasSettlement()) g.fillOval(vertex.x - 5, vertex.y - 5, 10, 10);
            if (vertex.vertex.hasCity()) g.fillRect(vertex.x - 5, vertex.y - 5, 10, 10);
        }
    }

    private int getScale(List<Field> fields) {
        int maxX = 0;
        int maxY = 0;
        for (Field field : fields) {
            if (field.getX() > maxX) maxX = field.getX();
            if (field.getY() > maxY) maxY = field.getY();
        }
        int scaleX = getWidth() / maxX / (width + side);
        int scaleY = getHeight() / maxY / (3 * height / 2);
        return Math.min(scaleX, scaleY);
    }

    private Color getColor(Field field) {
        Color color = Color.MAGENTA;    // if magenta can be seen, an error occured.
        switch (field.getType()) {
            case BRICK:
                color = Color.ORANGE;
                break;
            case LUMBER:
                color = Color.GREEN;
                break;
            case WOOL:
                color = Color.LIGHT_GRAY;
                break;
            case GRAIN:
                color = Color.YELLOW;
                break;
            case ORE:
                color = Color.BLACK;
                break;
        }
        return color;
    }
}
