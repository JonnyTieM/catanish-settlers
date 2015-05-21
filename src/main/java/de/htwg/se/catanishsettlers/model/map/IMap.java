package de.htwg.se.catanishsettlers.model.map;

/**
 * Interface for class Map.
 * Created by JonnyTieM on 31.03.2015.
 */
public interface IMap {
    /**
     * This returns all edges of a Field in following order: top, top right, bottom right, bottom, bottom left, top left
     *
     * @param field Field you want to know the edges of
     * @return all edges of the "field"
     */
    public Edge[] getEdges(Field field);

    /**
     * This returns all vertices of a Field in following order: top left, top right, middle right, bottom right, bottom left, middle left.
     *
     * @param field Field you want to know the vertices of
     * @return all vertices of the "field"
     */
    public Vertex[] getVertices(Field field);

    /**
     * This returns all adjacent fields to a vertex. In following order: top left and then clockwise.
     * This is for example useful for finding out the resources a settlement gets.
     *
     * @param vertex Vertex you want to know the adjacent fields of
     * @return adjacent fields to the vertex
     */
    public Field[] getAdjacentFields(Vertex vertex);

    /**
     * This returns all neighbouring vertices of the given vertex. In following order: top left and then clockwise.
     * This might be useful for example for checking whether you are allowed to build a settlement on this vertex.
     *
     * @param vertex Vertex you want to know the neighbouring vertices of
     * @return neighbouring vertices to given vertex
     */
    public Vertex[] getNeighbouringVertices(Vertex vertex);
}
