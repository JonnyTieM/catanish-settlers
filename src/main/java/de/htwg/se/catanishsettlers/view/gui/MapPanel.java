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
    private ObjectWithPosition mouseHover;

    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
        ObjectWithPosition best = objects.get(0);
        int x = best.x - e.getX();
        int y = best.y - e.getY();
        double minDistance = Math.sqrt(x * x + y * y);

        for(ObjectWithPosition object : objects) {
            x = object.x - e.getX();
            y = object.y - e.getY();
            double distance = Math.sqrt(x * x + y * y);
            if (distance < minDistance) {
                best = object;
                minDistance = distance;
            }
        }

        if (minDistance > 100) best = null;

        String text = "Mouse @ ";
        if (best == null) {
            debugLabel.setText(text + "nothing.");
        } else {
            if (best.object.getClass().equals(Field.class)) text += "Field " + ((Field)best.object).getX() + ", " + ((Field)best.object).getY();
            if (best.object.getClass().equals(Edge.class)) text += "Edge " + ((Edge)best.object).getX() + ", " + ((Edge)best.object).getY();
            if (best.object.getClass().equals(Vertex.class)) text += "Vertex " + ((Vertex)best.object).getX() + ", " + ((Vertex)best.object).getY();
            debugLabel.setText(text);
        }
        debugLabel.revalidate();

        mouseHover = best;
        repaint();
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
        public final int x;
        public final int y;

        public ObjectWithPosition(Object object, int x, int y) {
            this.object = object;
            this.x = x;
            this.y = y;
        }
    }

    public MapPanel(Map map) {
        this.map = map;
        debugLabel.setText("debug ready");
        add(debugLabel);
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        if (map == null) return;

        List<VertexWithCoordinates> vertices = new LinkedList<VertexWithCoordinates>();
        List<EdgeWithCoordinates> edges = new LinkedList<EdgeWithCoordinates>();
        List<Field> fields = map.getFields();

        int scale = getScale(fields);

        for (Field field : fields) {
            int midX = padding + field.getX() * (width + side) * scale;
            int midY = padding + field.getY() * (height * 1) * scale;
            if (field.getX() % 2 == 1) midY += (height / 2) * scale;

            objects.add(new ObjectWithPosition(field, midX, midY));

            int[] vx = new int[6];
            int[] vy = new int[6];
            Point[] vectors = {new Point(-width, -height), new Point(+width, -height), new Point(+width +side * 2, 0),
                               new Point(+width, +height), new Point(-width, +height), new Point(-width -side * 2, 0)};

            VertexWithCoordinates lastVertex = new VertexWithCoordinates(null, 0, 0);
            VertexWithCoordinates firstVertex = new VertexWithCoordinates(null, 0, 0);
            for (int i = 0; i < 6; i++) {
                int x = midX + vectors[i].x * scale / 2;
                int y = midY + vectors[i].y * scale / 2;

                objects.add(new ObjectWithPosition(map.getVertices(field)[i], x, y));

                vx[i] = x;
                vy[i] = y;

                VertexWithCoordinates vertex = new VertexWithCoordinates(map.getVertices(field)[i], x, y);
                vertices.add(vertex);
                if (i == 0) {
                    firstVertex = vertex;
                } else {
                    edges.add(new EdgeWithCoordinates(map.getEdges(field)[i], lastVertex.x, lastVertex.y, x, y));
                    objects.add(new ObjectWithPosition(map.getEdges(field)[i], (lastVertex.x + x) / 2, (lastVertex.y + y) / 2));
                }
                lastVertex = vertex;
            }
            edges.add(new EdgeWithCoordinates(map.getEdges(field)[0], lastVertex.x, lastVertex.y, firstVertex.x, firstVertex.y));
            objects.add(new ObjectWithPosition(map.getEdges(field)[0], (lastVertex.x + firstVertex.x) / 2, (lastVertex.y + firstVertex.y) / 2));

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

        if (mouseHover != null) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(mouseHover.x - 5, mouseHover.y - 5, 10, 10);
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
