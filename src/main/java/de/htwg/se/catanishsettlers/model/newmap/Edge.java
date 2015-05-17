package de.htwg.se.catanishsettlers.model.newmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stephan on 17.05.2015.
 */
public class Edge {
    private List<Tile> tiles;
    private List<Vertex> vertices;

    private Edge() {
        tiles = new LinkedList<Tile>();
        vertices = new LinkedList<Vertex>();
    }

    public Edge(Vertex a, Vertex b) {
        this();
        vertices.add(a);
        vertices.add(b);
    }

    public Edge(Tile a, Tile b) {
        this();
        tiles.add(a);
        tiles.add(b);
    }
}
