package de.htwg.se.catanishsettlers.view.gui.mapPanel;

import de.htwg.se.catanishsettlers.CatanishSettlers;
import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.map.*;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.gui.GUIhelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Stephan on 11.06.2015.
 */
public class MapPanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener, Observer {

    //private final double scale = 20;
    private final int padding = 190;
    private Map map;
    private final int width = 2;
    private final int height = 2;
    private final int side = 1;
    private Label debugLabel = new Label();
    private ObjectWithPosition mouseHover;
    private int rolledNumber;
    private final Font smallFont = new Font("Arial", Font.PLAIN, 14);
    private final Font bigFont = new Font("Arial", Font.BOLD, 20);

    private List<ObjectWithPosition> objects = new LinkedList<ObjectWithPosition>();
    private List<VertexWithCoordinates> verticesForPainting = new LinkedList<VertexWithCoordinates>();
    private List<EdgeWithCoordinates> edgesForPainting = new LinkedList<EdgeWithCoordinates>();
    private List<FieldWithCoordinates> fieldsForPainting = new LinkedList<FieldWithCoordinates>();

    private Vertex settlementWithoutRoad;
    private enum WhatToPlace {
        SETTLEMENT,
        ROAD
    }
    private WhatToPlace whatToPlace = WhatToPlace.SETTLEMENT;
    private Player[] players;
    private int activePlayerIndex = 0;
    private int direction = 1;
    private boolean lastPlayerHasBuiltTwice = false;

    public MapPanel(Map map) {
        this.map = map;
        add(debugLabel);
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
        recalculate();
    }

    public void update(Observable o, Object arg) {
        if (o.getClass() == Dice.class) {
            Dice dice = (Dice) o;
            rolledNumber = dice.getValue();
        }
        paint(this.getGraphics());
    }

    public void componentResized(ComponentEvent e) {
        recalculate();
    }
    public void componentMoved(ComponentEvent e) {
        recalculate();
    }
    public void componentShown(ComponentEvent e) { recalculate(); }
    public void componentHidden(ComponentEvent e) { recalculate(); }

    private void recalculate() {
        players = CatanishSettlers.game.getPlayerContainer().getPlayers().toArray(new Player[CatanishSettlers.game.getPlayerContainer().getPlayers().size()]);
        objects.clear();
        verticesForPainting.clear();
        edgesForPainting.clear();
        fieldsForPainting.clear();
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
                    Edge edge = map.getEdges(field)[i - 1];

                    edgesForPainting.add(new EdgeWithCoordinates(edge, lastVertex.x, lastVertex.y, x, y));
                    objects.add(new ObjectWithPosition(edge, (lastVertex.x + x) / 2, (lastVertex.y + y) / 2));
                }
                lastVertex = vertex;
            }
            Edge lastEdge = map.getEdges(field)[5];
            edgesForPainting.add(new EdgeWithCoordinates(lastEdge, firstVertex.x, firstVertex.y, lastVertex.x, lastVertex.y));
            objects.add(new ObjectWithPosition(lastEdge, (lastVertex.x + firstVertex.x) / 2, (lastVertex.y + firstVertex.y) / 2));

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

        if (CatanishSettlers.game.isPreparationPhase()) {
            if (whatToPlace == WhatToPlace.ROAD) {
                debugLabel.setText(players[activePlayerIndex].getName() + ": place road");
                debugLabel.revalidate();
            } else {
                debugLabel.setText(players[activePlayerIndex].getName() + ": place settlement");
                debugLabel.revalidate();
            }
        }
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
            Point textPosition = GUIhelper.placeTextInBox(w, h, text, g2.getFontMetrics());
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
                g2.setStroke(new BasicStroke(7));
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
            if (vertex.hasSettlement()) drawCircle(g2, x, y, 16, true);
            if (vertex.hasCity()) drawSquare(g2, x, y, 16);
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
        g2.setColor(players[activePlayerIndex].getColor());
        g2.setStroke(new BasicStroke(3));
        drawCircle(g2, mouseHover.x, mouseHover.y, 25, false);
    }

    public void mouseClicked(MouseEvent e) {
        Game game = CatanishSettlers.game;
        MapObject mapObject = mouseHover.object;

        if (mouseHover.object.getClass().equals(Edge.class) && whatToPlace == WhatToPlace.ROAD) {
            if (game.buildFirstRoad(players[activePlayerIndex], settlementWithoutRoad, (Edge) mapObject)) {
                whatToPlace = WhatToPlace.SETTLEMENT;
                repaint();


                if (activePlayerIndex == players.length - 1) {
                    if (lastPlayerHasBuiltTwice) {
                        direction = -1;
                    } else {
                        direction = 0;
                        lastPlayerHasBuiltTwice = true;
                    }
                }
                activePlayerIndex += direction;
                if (activePlayerIndex == -1) {
                    activePlayerIndex = 0;
                    game.nextPhase();
                }
            }
        }
        if (mouseHover.object.getClass().equals(Vertex.class) && whatToPlace == WhatToPlace.SETTLEMENT) {
            if (game.buildFirstSettlement(players[activePlayerIndex], (Vertex)mapObject)) {
                settlementWithoutRoad = (Vertex) mapObject;
                whatToPlace = WhatToPlace.ROAD;
                repaint();
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

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