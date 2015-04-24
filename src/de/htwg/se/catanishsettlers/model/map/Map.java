package de.htwg.se.catanishsettlers.model.map;

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
        fields = new Field[5][5];
        edges = new Edge[6][17];
        vertices = new Vertex[6][12];

        createField(2, 0);
        createField(1, 0);
        createField(3, 0);
        for (int x = 0; x <= 4; x++) {
            for (int y = 1; y <= 3; y++) {
                createField(x, y);
            }
        }
        createField(2, 4);
    }

    /**
     * creates a Field at given Position.
     * Then it checks whether all the edges and vertices for this field already exist.
     * If not it will create the needed edges and vertices.
     *
     * @param x x-Position
     * @param y y-Position
     */
    private void createField(int x, int y) {
        fields[x][y] = new Field(x, y);
        Field field = fields[x][y];

        Edge[] fieldEdges = getEdges(field);
        Vertex[] fieldVertices = getVertices(field);

        for (int i = 0; i < 6; i++) {
            if (fieldEdges[i] == null) {
                int xEdge = getEdgesCoordinateX(field)[i];
                int yEdge = getEdgesCoordinateY(field)[i];
                edges[xEdge][yEdge] = new Edge(xEdge, yEdge);
            }

            if (fieldVertices[i] == null) {
                int xVertex = getVerticesCoordinateX(field)[i];
                int yVertex = getVerticesCoordinateY(field)[i];
                vertices[xVertex][yVertex] = new Vertex(xVertex, yVertex);
            }
        }
    }

    /**
     * Returns Field of given position.
     *
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @return Field at given position
     */
    public Field getField(int x, int y) {
        return fields[x][y];
    }

    @Override
    public Edge[] getEdges(Field field) {
        if (field == null) {
            return null;
        }

        // Create edges array, where we put in all the edges of the field and then return it.
        Edge[] edges = new Edge[6];

        int[] x = getEdgesCoordinateX(field);
        int[] y = getEdgesCoordinateY(field);

        edges[1] = this.edges[x[0]][y[0]]; // top
        edges[2] = this.edges[x[1]][y[1]]; // top right
        edges[3] = this.edges[x[2]][y[2]]; // bottom right
        edges[4] = this.edges[x[3]][y[3]]; // bottom
        edges[5] = this.edges[x[4]][y[4]]; // bottom left
        edges[6] = this.edges[x[5]][y[5]]; // top left

        return edges;
    }

    /**
     * returns the x coordinates of each Edge of the field in following order: top, top right, bottom right, bottom, bottom left, top left.
     *
     * @param field
     * @return
     */
    private int[] getEdgesCoordinateX(Field field) {
        int x = field.getX();

        int[] xEdge = new int[]{
                x, //top
                x + 1, // top right
                x + 1, // bottom right
                x, // bottom
                x, // bottom left
                x // top left
        };
        return xEdge;
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

    @Override
    public Vertex[] getVertices(Field field) {
        if (field == null) {
            return null;
        }

        // Create vertex array, where we put in all the vertices of the field and then return it.
        Vertex[] vertices = new Vertex[6];

        int[] x = getVerticesCoordinateX(field);
        int[] y = getVerticesCoordinateY(field);

        vertices[0] = this.vertices[x[0]][y[0]]; // top left
        vertices[1] = this.vertices[x[1]][y[1]]; // top right
        vertices[2] = this.vertices[x[2]][y[2]]; // middle right
        vertices[3] = this.vertices[x[3]][y[3]]; // bottom right
        vertices[4] = this.vertices[x[4]][y[4]]; // bottom left
        vertices[5] = this.vertices[x[5]][y[5]]; // middle left

        return vertices;
    }

    /**
     * returns the x coordinates of each Vertex of the field in following order: top left, top right, middle right, bottom right, bottom left, middle left.
     *
     * @param field
     * @return
     */
    private int[] getVerticesCoordinateX(Field field) {
        int x = field.getX();

        int[] xVertex = new int[]{
                x,      //top left
                x + 1,  // top right
                x + 1,  // middle right
                x + 1,  // bottom right
                x,      // bottom left
                x       // middle left
        };
        return xVertex;
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

    @Override
    public Field[] getAdjacentFields(Vertex vertex) {
        return new Field[0];
    }

    @Override
    public Vertex[] getNeighbouringVertices(Vertex vertex) {
        return new Vertex[0];
    }
}
