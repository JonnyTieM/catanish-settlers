package de.htwg.se.catanishsettlers.view.gui.MainFrame;

import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.model.mechanic.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Stephan on 11.06.2015.
 */
public class MapPanel extends JPanel implements MouseMotionListener, ComponentListener, Observer {

    //private final double scale = 20;
    private final int padding = 190;
    private Map map;
    private final int width = 2;
    private final int height = 2;
    private final int side = 1;
    private Label debugLabel = new Label();
    private List<ObjectWithPosition> objects;
    private ObjectWithPosition mouseHover;
    private int rolledNumber;
    private final Font smallFont = new Font("Arial", Font.PLAIN, 14);
    private final Font bigFont = new Font("Arial", Font.BOLD, 20);

    private List<VertexWithCoordinates> verticesForPainting;
    private List<EdgeWithCoordinates> edgesForPainting;
    private List<FieldWithCoordinates> fieldsForPainting;

    public void update(Observable o, Object arg) {
        Dice dice = (Dice)o;
        rolledNumber = dice.getValue();
        paint(this.getGraphics());
    }

    private class VertexWithCoordinates {
        public final Vertex vertex;
        public final int x, y;

        public VertexWithCoordinates(Vertex vertex, int x, int y) {
            this.vertex = vertex;
            this.x = x;
            this.y = y;
        }
    }

    private class FieldWithCoordinates {
        public final Field field;
        public final int x, y;
        public final int[] vx, vy;

        public FieldWithCoordinates(Field field, int x, int y, int[] vx, int[] vy) {
            this.field = field;
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
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
        public final int x, y;

        public ObjectWithPosition(Object object, int x, int y) {
            this.object = object;
            this.x = x;
            this.y = y;
        }
    }

    public MapPanel(Map map) {
        this.map = map;
        add(debugLabel);
        addMouseMotionListener(this);
        addComponentListener(this);
        recalculate();
    }

    public void componentResized(ComponentEvent e) {
        recalculate();
    }

    public void componentMoved(ComponentEvent e) {
        recalculate();
    }

    public void componentShown(ComponentEvent e) {
        recalculate();
    }

    public void componentHidden(ComponentEvent e) {
        recalculate();
    }

    private void recalculate() {
        objects = new LinkedList<ObjectWithPosition>();
        verticesForPainting = new LinkedList<VertexWithCoordinates>();
        edgesForPainting = new LinkedList<EdgeWithCoordinates>();
        fieldsForPainting = new LinkedList<FieldWithCoordinates>();
        List<Field> fields = map.getFields();

        int scale = getScale(fields);

        for (Field field : fields) {
            int midX = padding + field.getX() * (width + side) * scale;
            int midY = padding + field.getY() * (height * 1) * scale;
            if (field.getX() % 2 == 1) midY += (height / 2) * scale;

            int[] vx = new int[6];
            int[] vy = new int[6];
            Point[] vectors = {new Point(-width, -height), new Point(+width, -height), new Point(+width + side * 2, 0),
                    new Point(+width, +height), new Point(-width, +height), new Point(-width - side * 2, 0)};

            VertexWithCoordinates lastVertex = new VertexWithCoordinates(null, 0, 0);
            VertexWithCoordinates firstVertex = new VertexWithCoordinates(null, 0, 0);
            for (int i = 0; i < 6; i++) {
                Vertex mapVertex = map.getVertices(field)[i];

                int x = midX + vectors[i].x * scale / 2;
                int y = midY + vectors[i].y * scale / 2;

                objects.add(new ObjectWithPosition(mapVertex, x, y));

                vx[i] = x;
                vy[i] = y;

                VertexWithCoordinates vertex = new VertexWithCoordinates(mapVertex, x, y);
                verticesForPainting.add(vertex);
                if (i == 0) {
                    firstVertex = vertex;
                } else {
                    Edge mapEdge = map.getEdges(field)[i - 1];

                    edgesForPainting.add(new EdgeWithCoordinates(mapEdge, lastVertex.x, lastVertex.y, x, y));
                    objects.add(new ObjectWithPosition(mapEdge, (lastVertex.x + x) / 2, (lastVertex.y + y) / 2));
                }
                lastVertex = vertex;
            }
            Edge lastMapEdge = map.getEdges(field)[0];
            edgesForPainting.add(new EdgeWithCoordinates(lastMapEdge, lastVertex.x, lastVertex.y, firstVertex.x, firstVertex.y));
            objects.add(new ObjectWithPosition(lastMapEdge, (lastVertex.x + firstVertex.x) / 2, (lastVertex.y + firstVertex.y) / 2));

            fieldsForPainting.add(new FieldWithCoordinates(field, midX, midY, vx, vy));
            objects.add(new ObjectWithPosition(field, midX, midY));
        }
    }

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
            if (best.object.getClass().equals(Field.class)) text += "Field "
                    + ((Field)best.object).getX() + ", " + ((Field)best.object).getY();
            if (best.object.getClass().equals(Edge.class)) text += "Edge "
                    + ((Edge)best.object).getX() + ", " + ((Edge)best.object).getY();
            if (best.object.getClass().equals(Vertex.class)) text += "Vertex "
                    + ((Vertex)best.object).getX() + ", " + ((Vertex)best.object).getY();
            debugLabel.setText(text);
        }
        debugLabel.revalidate();

        mouseHover = best;
        repaint();
    }

    public void paint(Graphics g) {
        if (map == null) return;
        Graphics2D g2 = (Graphics2D)g;

        drawFields(g2, fieldsForPainting);
        drawEdges(g2, edgesForPainting);
        drawBuildings(g2, verticesForPainting);
        indicateMouseHover(g2);
    }

    private void drawFields(Graphics2D g2, List<FieldWithCoordinates> fields) {
        for(FieldWithCoordinates fieldWithCoords : fields) {
            Field field = fieldWithCoords.field;
            int[] vx = fieldWithCoords.vx;
            int[] vy = fieldWithCoords.vy;
            g2.setColor(getColor(field));
            g2.fillPolygon(vx, vy, 6);
            g2.setColor(Color.GRAY);

            int number = field.getTriggerNumber();

            Font font = smallFont;
            String text = String.valueOf(number);
            if (number == rolledNumber) {
                font = bigFont;
                text = "< " + text + " >";
            }
            g2.setFont(font);
            int w = vx[1] - vx[0];
            int h = vy[4] - vy[1];
            Point textPosition = Utility.placeTextInBox(w, h, text, g2.getFontMetrics());
            g2.drawString(text, vx[0] + textPosition.x, vy[0] + textPosition.y);
        }
    }

    private void drawEdges(Graphics2D g2, List<EdgeWithCoordinates> edges) {
        for (EdgeWithCoordinates edgeWithCoord : edges) {
            Edge edge = edgeWithCoord.edge;
            int x1 = edgeWithCoord.x1;
            int x2 = edgeWithCoord.x2;
            int y1 = edgeWithCoord.y1;
            int y2 = edgeWithCoord.y2;

            if(edge.hasRoad()) {
                g2.setStroke(new BasicStroke(4));
                g2.setColor(edge.getRoad().getPlayer().getColor());
            } else {
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.GRAY);
            }
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private void drawBuildings(Graphics2D g2, List<VertexWithCoordinates> vertices) {
        for (VertexWithCoordinates vertexWithCoord : vertices) {
            Vertex vertex = vertexWithCoord.vertex;
            int x = vertexWithCoord.x;
            int y = vertexWithCoord.y;
            if (vertex.hasBuilding()) g2.setColor(vertex.getBuilding().getPlayer().getColor());
            if (vertex.hasSettlement()) drawCircle(g2, x, y, 10, true);
            if (vertex.hasCity()) drawSquare(g2, x, y, 10);
        }
    }

    private void drawCircle(Graphics2D g2, int x, int y, int d, boolean fill) {
        if (fill) {
            g2.fillOval(x - d / 2, y - d / 2, d, d);
        } else {
            g2.drawOval(x - d / 2, y - d / 2, d, d);
        }
    }

    private void drawSquare(Graphics2D g2, int x, int y, int a) {
        g2.fillRect(x - a / 2, y - a / 2, a, a);
    }

    private void indicateMouseHover(Graphics2D g2) {
        if (mouseHover == null) return;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3));
        drawCircle(g2, mouseHover.x , mouseHover.y, 10, false);
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
        switch (field.getResourceType()) {
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