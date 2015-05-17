package de.htwg.se.catanishsettlers.model.newmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stephan on 17.05.2015.
 */
public class Tile {

    private final int q, r;

    public List<Tile> neighbours;
    public List<Edge> edges;
    public List<Vertex> vertices;

    public Tile(int q, int r) {
        this.q = q;
        this.r = r;
        neighbours = new LinkedList<Tile>();
        edges = new LinkedList<Edge>();
        vertices = new LinkedList<Vertex>();
    }
}
