package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * This class has different utility functions for checking whether a player can build certain constructions on
 * certain places and to find possible places where a player can place constructions.
 * Created by JonnyTieM on 14.05.2015.
 */
public final class ConstructionInspector {
    /**
     * no need for creating an object of this class, since it is a utility class.
     */
    private ConstructionInspector() {
    }

    public static LinkedList<Vertex> possibleSettlements(Player player, Map map) {
        LinkedList<Vertex> possibleHouses = new LinkedList<Vertex>();

        LinkedList<Vertex> vertices = map.getVertices();

        while (!vertices.isEmpty()) {
            Vertex vertex = vertices.pop();
            if (canBuildSettlement(player, vertex, map)) {
                possibleHouses.add(vertex);
            }
        }

        return possibleHouses;
    }

    public static LinkedList<Vertex> possibleCities(Player player, Map map) {
        LinkedList<Vertex> possibleCities = new LinkedList<Vertex>();

        LinkedList<Vertex> vertices = map.getVertices();

        while (!vertices.isEmpty()) {
            Vertex vertex = vertices.pop();
            if (canBuildCity(player, vertex, map)) {
                possibleCities.add(vertex);
            }
        }

        return possibleCities;
    }

    public static LinkedList<Edge> possibleStreets(Player player, Map map) {
        LinkedList<Edge> possibleStreets = new LinkedList<Edge>();

        LinkedList<Edge> edges = map.getEdges();

        while (!edges.isEmpty()) {
            Edge edge = edges.pop();
            if (canBuildRoad(player, edge, map)) {
                possibleStreets.add(edge);
            }
        }

        return possibleStreets;
    }

    public static boolean canBuildSettlement(Player player, Vertex vertex, Map map) {
        Vertex[] vertices = map.getNeighbouringVertices(vertex);
        //check adjacent vertices for buildings and adjacent edges for roads owned by player.
        if (vertex != null && !hasABuilding(vertices) && hasAdjacentStreetOwnedByPlayer(player, vertex, map) && !vertex.hasBuilding()) {
            return true;
        }
        return false;
    }

    /**
     * this returns true if any of the given vertices has a building. One of the vertices having a building is enough that it will return true.
     *
     * @param vertices these will be checked for buildings
     * @return true if on one of the vertices a building is found. false if there is no building on any given vertex.
     */
    private static boolean hasABuilding(Vertex[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].hasBuilding()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasAdjacentStreetOwnedByPlayer(Player player, Vertex vertex, Map map) {
        Edge[] edges = map.getNeighbouringEdges(vertex);
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] == null) {
                continue;
            }
            Road road = edges[i].getRoad();
            if (road != null && road.getPlayer() == player) {
                return true;
            }
        }
        return false;
    }

    public static boolean canBuildCity(Player player, Vertex vertex, Map map) {
        //to be able to build a city, there needs to be a settlement on this vertex owned by the player
        if (vertex != null && vertex.hasSettlement() && vertex.getBuilding().getPlayer() == player) {
            return true;
        }
        return false;
    }

    public static boolean canBuildRoad(Player player, Edge edge, Map map) {
        //to be able to build a road, there needs to be an adjacent road owned by the player
        Vertex[] vertices = map.getVerticesOfEdge(edge);
        if (edge != null && !edge.hasRoad() && hasVerticesArrayAdjacentStreetOwnedByPlayer(player, vertices, map)) {
            return true;
        }
        return false;
    }

    private static boolean hasVerticesArrayAdjacentStreetOwnedByPlayer(Player player, Vertex[] vertices, Map map) {
        for (int i = 0; i < vertices.length; i++) {
            if (hasAdjacentStreetOwnedByPlayer(player, vertices[i], map)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canBuildFirstSettlementWithRoad(Player player, Vertex vertex, Edge edge, Map map) {
        Vertex[] vertices = map.getNeighbouringVertices(vertex);
        if (vertex != null && edge != null && !hasABuilding(vertices) && !vertex.hasBuilding() && !edge.hasRoad() && vertexAndEdgeAreNeighbours(vertex, edge, map)) {
            return true;
        }
        return false;
    }

    public static boolean canBuildFirstSettlement(Player player, Vertex vertex, Map map) {
        Vertex[] vertices = map.getNeighbouringVertices(vertex);
        if (vertex != null && !hasABuilding(vertices) && !vertex.hasBuilding() && player.getAvailableSettlements() > Config.MAX_SETTLEMENTS - 2) {
            return true;
        }
        return false;
    }

    public static boolean canBuildFirstRoad(Player player, Vertex vertex, Edge edge, Map map) {
        if (vertex != null && edge != null && vertex.hasBuilding() && vertex.getBuilding().getPlayer().equals(player)
                && !edge.hasRoad() && vertexAndEdgeAreNeighbours(vertex, edge, map)
                && player.getAvailableRoads() > Config.MAX_ROADS - 2) {
            return true;
        }
        return false;
    }

    private static boolean vertexAndEdgeAreNeighbours(Vertex vertex, Edge edge, Map map) {
        Vertex[] vertices = map.getVerticesOfEdge(edge);
        if (vertices[0].equals(vertex) || vertices[1].equals(vertex)) {
            return true;
        }
        return false;
    }
}
