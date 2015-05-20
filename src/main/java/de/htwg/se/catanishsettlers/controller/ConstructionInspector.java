package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

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

    public static void possibleHouses(Player player, Map map) {

    }

    public static void possibleCities(Player player, Map map) {

    }

    public static void possibleStreets(Player player, Map map) {

    }

    public static boolean canBuildSettlement(Player player, Vertex vertex, Map map) {
        Vertex[] vertices = map.getNeighbouringVertices(vertex);
        //check adjacent vertices for buildings and adjacent edges for roads owned by player.
        if (!hasABuilding(vertices) && hasAdjacentStreetOwnedByPlayer(player, vertex, map) && !vertex.hasBuilding()) {
            return true;
        }
        return false;
    }

    /**
     * this returns true if any of the given vertices has a building. One of the vertices having a building is enough that it will return true.
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
            if(edges[i] == null) {
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
        //to be able to build a road, there needs to be an adjacent settlement or road owned by the player
        return false;
    }
}
