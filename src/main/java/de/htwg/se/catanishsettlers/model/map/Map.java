package de.htwg.se.catanishsettlers.model.map;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.constructions.Building;
import de.htwg.se.catanishsettlers.model.resources.EResource;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Map manages the fields, edges and vertices of the board. It knows to which field each vetix or edge belongs.
 * x coordinates start on the left with zero and go up towards the right.
 * y coordinates start on the top with zero and to up towards the bottom.
 * <p>
 * Some inspiration for this class is taken from following links:
 * http://www.redblobgames.com/grids/hexagons/
 * http://stackoverflow.com/questions/5040295/data-structure-for-settlers-of-catan-map
 * Created by JonnyTieM on 29.03.2015.
 */
public final class Map implements IMap {
    private Field[][] fields;
    private Edge[][] edges;
    private Vertex[][] vertices;

    public Map() {
        initStandardMap();
    }

    /**
     * standard Map for catanish Settlers
     */
    private void initStandardMap() {
        fields = new Field[Config.FIELDS_WIDTH][Config.FIELDS_HEIGHT];
        edges = new Edge[Config.EDGES_WIDTH][Config.EDGES_HEIGHT];
        vertices = new Vertex[Config.VERTICES_WIDTH][Config.VERTICES_HEIGHT];

        Deque<EResource> resources = EResource.getRandomResourceList(4, 4, 4, 3, 4);
        Deque<Integer> triggers = TriggerNumberCreator.getRandomTriggerNumbers(2, 2, 2, 2, 2, 2, 2, 2, 2, 1);

        createField(2, 0, resources.pop(), triggers.pop());
        createField(1, 0, resources.pop(), triggers.pop());
        createField(3, 0, resources.pop(), triggers.pop());
        for (int x = 0; x <= 4; x++) {
            for (int y = 1; y <= 3; y++) {
                createField(x, y, resources.pop(), triggers.pop());
            }
        }
        createField(2, 4, resources.pop(), triggers.pop());
    }

    /**
     * creates a Field at given Position.
     * Then it checks whether all the edges and vertices for this field already exist.
     * If not it will create the needed edges and vertices.
     *
     * @param x x-Position
     * @param y y-Position
     */
    private void createField(int x, int y, EResource resource, int trigger) {
        if (x < 0 || y < 0 || x >= Config.FIELDS_WIDTH || y >= Config.FIELDS_HEIGHT) {
            return;
        }
        fields[x][y] = new Field(x, y, resource, trigger);
        Field field = fields[x][y];

        Edge[] fieldEdges = getEdges(field);
        Vertex[] fieldVertices = getVertices(field);

        for (int i = 0; i < 6; i++) {
            if (fieldEdges[i] == null) {
                int xEdge = getEdgesCoordinateX(field)[i];
                int yEdge = getEdgesCoordinateY(field)[i];
                createEdge(xEdge, yEdge);
            }

            if (fieldVertices[i] == null) {
                int xVertex = getVerticesCoordinateX(field)[i];
                int yVertex = getVerticesCoordinateY(field)[i];
                createVertex(xVertex, yVertex);
            }
        }
    }

    private void createEdge(int x, int y) {
        if (x < 0 || y < 0 || x >= Config.EDGES_WIDTH || y >= Config.EDGES_HEIGHT) {
            return;
        }
        edges[x][y] = new Edge(x, y);
    }

    private void createVertex(int x, int y) {
        if (x < 0 || y < 0 || x >= Config.VERTICES_WIDTH || y >= Config.VERTICES_HEIGHT) {
            return;
        }
        vertices[x][y] = new Vertex(x, y);
    }

    /**
     * Returns Field of given position. If x or y are negative, this will return null.
     *
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @return Field at given position
     */
    public Field getField(int x, int y) {
        if (x < 0 || y < 0 || x >= Config.FIELDS_WIDTH || y >= Config.FIELDS_HEIGHT) {
            return null;
        }
        return fields[x][y];
    }

    public Edge getEdge(int x, int y) {
        if (x < 0 || y < 0 || x >= Config.EDGES_WIDTH || y >= Config.EDGES_HEIGHT) {
            return null;
        }
        return edges[x][y];
    }

    public Vertex getVertex(int x, int y) {
        if (x < 0 || y < 0 || x >= Config.VERTICES_WIDTH || y >= Config.VERTICES_HEIGHT) {
            return null;
        }
        return vertices[x][y];
    }

    public List<Field> getFields() {
        List<Field> returnFields = new LinkedList<Field>();
        for (Field[] fieldRow : fields) {
            for (Field field : fieldRow) {
                if (field != null) {
                    returnFields.add(field);
                }
            }
        }
        return returnFields;
    }

    public Deque<Edge> getEdges() {
        Deque<Edge> allEdges = new LinkedList<Edge>();

        for (int y = 0; y < Config.EDGES_HEIGHT; y++) {
            for (int x = 0; x < Config.EDGES_WIDTH; x++) {
                Edge edge = getEdge(x, y);
                if (edge != null) {
                    allEdges.add(edge);
                }
            }
        }

        return allEdges;
    }

    public Deque<Vertex> getVertices() {
        Deque<Vertex> allVertices = new LinkedList<Vertex>();

        for (int y = 0; y < Config.VERTICES_HEIGHT; y++) {
            for (int x = 0; x < Config.VERTICES_WIDTH; x++) {
                Vertex vertex = getVertex(x, y);
                if (vertex != null) {
                    allVertices.add(vertex);
                }
            }
        }

        return allVertices;
    }

    public Edge[] getEdges(Field field) {
        if (field == null) {
            return new Edge[0];
        }

        // Create edgesOfField array, where we put in all the edges of the field and then return it.
        Edge[] edgesOfField = new Edge[6];

        int[] x = getEdgesCoordinateX(field);
        int[] y = getEdgesCoordinateY(field);

        edgesOfField[0] = getEdge(x[0], y[0]); // top
        edgesOfField[1] = getEdge(x[1], y[1]); // top right
        edgesOfField[2] = getEdge(x[2], y[2]); // bottom right
        edgesOfField[3] = getEdge(x[3], y[3]); // bottom
        edgesOfField[4] = getEdge(x[4], y[4]); // bottom left
        edgesOfField[5] = getEdge(x[5], y[5]); // top left

        return edgesOfField;
    }

    /**
     * returns the x coordinates of each Edge of the field in following order: top, top right, bottom right, bottom, bottom left, top left.
     *
     * @param field
     * @return
     */
    private int[] getEdgesCoordinateX(Field field) {
        int x = field.getX();

        return new int[]{
                x, //top
                x + 1, // top right
                x + 1, // bottom right
                x, // bottom
                x, // bottom left
                x // top left
        };
    }

    /**
     * returns the y coordinates of each Edge of the field in following order: top, top right, bottom right, bottom, bottom left, top left.
     *
     * @param field
     * @return
     */
    private int[] getEdgesCoordinateY(Field field) {
        int x = field.getX();
        int y = field.getY();

        int[] yEdge;

        if (x % 2 == 0) {
            yEdge = new int[]{
                    3 * y, //top
                    3 * y + 1, // top right
                    3 * y + 2, // bottom right
                    3 * y + 3, // bottom
                    3 * y + 2, // bottom left
                    3 * y + 1 // top left
            };
        } else {
            yEdge = new int[]{
                    3 * y, //top
                    3 * y + 2, // top right
                    3 * y + 4, // bottom right
                    3 * y + 3, // bottom
                    3 * y + 4, // bottom left
                    3 * y + 2 // top left
            };
        }
        return yEdge;
    }

    public List<Building> getBuildings(Field field) {
        List<Building> returnBuildings = new LinkedList<Building>();
        Vertex[] verticesOfField = getVertices(field);
        for (Vertex vertex : verticesOfField) {
            if (vertex.hasBuilding()) {
                returnBuildings.add(vertex.getBuilding());
            }
        }
        return returnBuildings;
    }

    public Vertex[] getVertices(Field field) {
        if (field == null) {
            return new Vertex[0];
        }

        // Create vertex array, where we put in all the vertices of the field and then return it.
        Vertex[] verticesOfField = new Vertex[6];

        int[] x = getVerticesCoordinateX(field);
        int[] y = getVerticesCoordinateY(field);

        verticesOfField[0] = getVertex(x[0], y[0]); // top left
        verticesOfField[1] = getVertex(x[1], y[1]); // top right
        verticesOfField[2] = getVertex(x[2], y[2]); // middle right
        verticesOfField[3] = getVertex(x[3], y[3]); // bottom right
        verticesOfField[4] = getVertex(x[4], y[4]); // bottom left
        verticesOfField[5] = getVertex(x[5], y[5]); // middle left

        return verticesOfField;
    }

    /**
     * returns the x coordinates of each Vertex of the field in following order: top left, top right, middle right, bottom right, bottom left, middle left.
     *
     * @param field
     * @return
     */
    private int[] getVerticesCoordinateX(Field field) {
        int x = field.getX();

        return new int[]{
                x,      //top left
                x + 1,  // top right
                x + 1,  // middle right
                x + 1,  // bottom right
                x,      // bottom left
                x       // middle left
        };
    }

    /**
     * returns the y coordinates of each Vertex of the field in following order: top left, top right, middle right, bottom right, bottom left, middle left.
     *
     * @param field
     * @return
     */
    private int[] getVerticesCoordinateY(Field field) {
        int x = field.getX();
        int y = field.getY();

        int[] yVertex;
        if (x % 2 == 0) {
            yVertex = new int[]{
                    2 * y,          //top left
                    2 * y,          // top right
                    2 * y + 1,      // middle right
                    2 * y + 2,      // bottom right
                    2 * y + 2,      // bottom left
                    2 * y + 1       // middle left
            };
        } else {
            yVertex = new int[]{
                    2 * y + 1,      //top left
                    2 * y + 1,      // top right
                    2 * y + 2,      // middle right
                    2 * y + 3,      // bottom right
                    2 * y + 3,      // bottom left
                    2 * y + 2       // middle left
            };
        }
        return yVertex;
    }

    public Field[] getAdjacentFields(Vertex vertex) {
        if (vertex == null) {
            return new Field[0];
        }

        Field[] fieldsAroundVertex = new Field[3];

        int[] x = getAdjacentFieldsCoordinateX(vertex);
        int[] y = getAdjacentFieldsCoordinateY(vertex);

        fieldsAroundVertex[0] = getField(x[0], y[0]);
        fieldsAroundVertex[1] = getField(x[1], y[1]);
        fieldsAroundVertex[2] = getField(x[2], y[2]);

        return fieldsAroundVertex;
    }

    public List<Field> getFieldsWithTriggerNumber(int triggerNumber) {
        List<Field> matches = new LinkedList<Field>();
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                if (fields[x][y] != null && fields[x][y].getTriggerNumber() == triggerNumber) {
                    matches.add(fields[x][y]);
                }
            }
        }
        return matches;
    }

    public int getFieldsCount() {
        int count = 0;
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                if (fields[x][y] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * This returns the x Coordinates of the adjacent fields in following order: top left and then clockwise.
     * WARNING!!! This method might return negative values!!!
     *
     * @param vertex The Vertex you want to know the adjacent fields of.
     * @return x Coordinates of the adjacent fields
     */
    private int[] getAdjacentFieldsCoordinateX(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();

        int[] xFields = new int[3];

        xFields[0] = x - 1;
        xFields[1] = x;

        if (x % 2 == 0) {
            if (y % 2 == 0) {
                xFields[2] = x;
            } else {
                xFields[2] = x - 1;
            }

        } else {
            if (y % 2 == 0) {
                xFields[2] = x - 1;
            } else {
                xFields[2] = x;
            }
        }

        return xFields;
    }

    /**
     * This returns the y Coordinates of the adjacent fields in following order: top left and then clockwise.
     * WARNING!!! This method might return negative values!!!
     *
     * @param vertex vertex The Vertex you want to know the adjacent fields of.
     * @return y Coordinates of the adjacent fields
     */
    private int[] getAdjacentFieldsCoordinateY(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();

        int[] yFields = new int[3];

        if (x % 2 == 0) {
            if (y % 2 == 0) {
                yFields[0] = (y - 2) / 2;
                yFields[1] = (y - 2) / 2;
                yFields[2] = y / 2;
            } else {
                yFields[0] = (y - 3) / 2;
                yFields[1] = (y - 1) / 2;
                yFields[2] = (y - 1) / 2;
            }

        } else {
            if (y % 2 == 0) {
                yFields[0] = (y - 2) / 2;
                yFields[1] = (y - 2) / 2;
                yFields[2] = y / 2;
            } else {
                yFields[0] = (y - 1) / 2;
                yFields[1] = (y - 3) / 2;
                yFields[2] = (y - 1) / 2;
            }
        }

        return yFields;
    }

    public Vertex[] getNeighbouringVertices(Vertex vertex) {
        if (vertex == null) {
            return new Vertex[0];
        }

        Vertex[] neighbouringVertices = new Vertex[3];

        int[] x = getNeighbouringVerticesCoordinateX(vertex);
        int[] y = getNeighbouringVerticesCoordinateY(vertex);

        neighbouringVertices[0] = getVertex(x[0], y[0]);
        neighbouringVertices[1] = getVertex(x[1], y[1]);
        neighbouringVertices[2] = getVertex(x[2], y[2]);

        return neighbouringVertices;
    }

    /**
     * This returns the x Coordinates of the neighbouring vertices in following order: top left and then clockwise.
     * WARNING!!! This method might return negative values!!!
     *
     * @param vertex The Vertex you want to know the neighbouring vertices of.
     * @return x Coordinates of the neighbouring vertices
     */
    private int[] getNeighbouringVerticesCoordinateX(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();

        int[] xVertices = new int[3];

        //There are two different cases possible.
        //two vertices on left and one on right: x and y is even or both are uneven
        //one vertex on left and two on right: x or y is uneven and the other is even
        if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
            xVertices[0] = x;
            xVertices[1] = x + 1;
            xVertices[2] = x;
        } else {
            xVertices[0] = x - 1;
            xVertices[1] = x;
            xVertices[2] = x;
        }

        return xVertices;
    }

    /**
     * This returns the y Coordinates of the neighbouring vertices in following order: top left and then clockwise.
     * WARNING!!! This method might return negative values!!!
     *
     * @param vertex The Vertex you want to know the neighbouring vertices of.
     * @return y Coordinates of the neighbouring vertices
     */
    private int[] getNeighbouringVerticesCoordinateY(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();

        int[] yVertices = new int[3];

        //There are two different cases possible.
        //two vertices on left and one on right: x and y is even or both are uneven
        //one vertex on left and two on right: x or y is uneven and the other is even
        if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
            yVertices[0] = y - 1;
            yVertices[1] = y;
            yVertices[2] = y + 1;
        } else {
            yVertices[0] = y;
            yVertices[1] = y - 1;
            yVertices[2] = y + 1;
        }

        return yVertices;
    }

    public Edge[] getNeighbouringEdges(Vertex vertex) {
        if (vertex == null) {
            return new Edge[0];
        }

        Edge[] neighbouringEdges = new Edge[3];

        int[] x = getNeighbouringEdgesCoordinateX(vertex);
        int[] y = getNeighbouringEdgesCoordinateY(vertex);

        neighbouringEdges[0] = getEdge(x[0], y[0]);
        neighbouringEdges[1] = getEdge(x[1], y[1]);
        neighbouringEdges[2] = getEdge(x[2], y[2]);

        return neighbouringEdges;
    }

    /**
     * This returns the x Coordinates of the neighbouring edges in following order: top left and then clockwise.
     * WARNING!!! This method might return negative values!!!
     *
     * @param vertex The Vertex you want to know the neighbouring edges of.
     * @return x Coordinates of the neighbouring edges
     */
    private int[] getNeighbouringEdgesCoordinateX(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();

        int[] xEdges = new int[3];

        //There are two different cases possible.
        //two edges on left and one on right: x and y is even or both are uneven
        //one edge on left and two on right: x or y is uneven and the other is even
        if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
            xEdges[0] = x;
            xEdges[1] = x;
            xEdges[2] = x;
        } else {
            xEdges[0] = x - 1;
            xEdges[1] = x;
            xEdges[2] = x;
        }

        return xEdges;
    }

    /**
     * This returns the y Coordinates of the neighbouring edges in following order: top left and then clockwise.
     * WARNING!!! This method might return negative values!!!
     *
     * @param vertex The Vertex you want to know the neighbouring edges of.
     * @return y Coordinates of the neighbouring edges
     */
    private int[] getNeighbouringEdgesCoordinateY(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();

        int[] yEdges = new int[3];

        //There are two different cases possible.
        //two edges on left and one on right: x and y is even or both are uneven
        //one edge on left and two on right: x or y is uneven and the other is even
        if ((x % 2 == 0 && y % 2 == 0) || (x % 2 != 0 && y % 2 != 0)) {
            if (y % 2 == 0) {
                yEdges[0] = y + (y / 2) - 1;
                yEdges[1] = y + (y / 2);
                yEdges[2] = y + (y / 2) + 1;
            } else {
                yEdges[0] = y + ((y - 1) / 2);
                yEdges[1] = y + ((y - 1) / 2) - 1;
                yEdges[2] = y + ((y - 1) / 2) + 1;
            }
        } else {
            if (y % 2 == 0) {
                yEdges[0] = y + (y / 2);
                yEdges[1] = y + (y / 2) - 1;
                yEdges[2] = y + (y / 2) + 1;
            } else {
                yEdges[0] = y + ((y - 1) / 2) - 1;
                yEdges[1] = y + ((y - 1) / 2);
                yEdges[2] = y + ((y - 1) / 2) + 1;
            }
        }

        return yEdges;
    }

    /**
     * This gives you the two Vertices of the given edge. First the left one and then the right one.
     *
     * @param edge The edge you want to know the vertices of
     * @return Two Vertices, the first is the left one and the second the right one
     */
    public Vertex[] getVerticesOfEdge(Edge edge) {
        if (edge == null) {
            return new Vertex[0];
        }

        Vertex[] verticesOfEdge = new Vertex[2];

        int[] x = getVerticesOfEdgeCoordinateX(edge);
        int[] y = getVerticesOfEdgeCoordinateY(edge);

        verticesOfEdge[0] = getVertex(x[0], y[0]);
        verticesOfEdge[1] = getVertex(x[1], y[1]);

        return verticesOfEdge;
    }

    private int[] getVerticesOfEdgeCoordinateX(Edge edge) {
        int x = edge.getX();
        int y = edge.getY();

        int[] xVertices = new int[2];

        xVertices[0] = x;

        if (y % 3 == 0) {
            xVertices[1] = x + 1;
        } else {
            xVertices[1] = x;
        }

        return xVertices;
    }

    private int[] getVerticesOfEdgeCoordinateY(Edge edge) {
        int x = edge.getX();
        int y = edge.getY();

        int[] yVertices = new int[2];

        if (x % 2 == 0) {
            if (y % 3 == 0) {
                yVertices[0] = (y / 3) * 2;
                yVertices[1] = (y / 3) * 2;
            } else if ((y - 1) % 3 == 0) {
                yVertices[0] = (((y - 1) / 3) * 2) + 1;
                yVertices[1] = (((y - 1) / 3) * 2);
            } else {
                yVertices[0] = (((y - 2) / 3) * 2) + 1;
                yVertices[1] = (((y - 2) / 3) * 2) + 2;
            }
        } else {
            if (y % 3 == 0) {
                yVertices[0] = (y / 3) * 2 + 1;
                yVertices[1] = (y / 3) * 2 + 1;
            } else if ((y - 1) % 3 == 0) {
                yVertices[0] = (((y - 1) / 3) * 2);
                yVertices[1] = (((y - 1) / 3) * 2) + 1;
            } else {
                yVertices[0] = (((y - 2) / 3) * 2) + 2;
                yVertices[1] = (((y - 2) / 3) * 2) + 1;
            }
        }

        return yVertices;
    }
}
