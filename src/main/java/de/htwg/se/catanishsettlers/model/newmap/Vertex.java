package de.htwg.se.catanishsettlers.model.newmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stephan on 17.05.2015.
 */
public class Vertex {
    private List<Tile> tiles;
    private List<Edge> edges;

    private Vertex() {
        tiles = new LinkedList<Tile>();
        edges = new LinkedList<Edge>();
    }

    public Vertex(Edge a, Edge b) {
        this();
        edges.add(a);
        edges.add(b);
        //TODO: determine 3rd Vertex and add it
    }

    public Vertex(Tile a, Tile b, Tile c) {
        this();
        tiles.add(a);
        tiles.add(b);
        tiles.add(c);
    }
}
