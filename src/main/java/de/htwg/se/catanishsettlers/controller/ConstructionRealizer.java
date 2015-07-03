package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 15.05.2015.
 */
final class ConstructionRealizer {

    private ConstructionRealizer() {
    }   // cannot be initialized

    public static boolean buildSettlement(Player player, Vertex vertex, Map map) {
        ResourceCollection cost = Settlement.COST;
        if (ConstructionInspector.canBuildSettlement(player, vertex, map) && PlayerInspector.canBuildSettlement(player)) {
            Settlement settlement = new Settlement(player);
            vertex.placeBuilding(settlement);
            player.payCost(cost);
            player.decreaseSettlements();
            return true;
        }
        return false;
    }

    public static boolean buildCity(Player player, Vertex vertex, Map map) {
        ResourceCollection cost = City.COST;
        if (ConstructionInspector.canBuildCity(player, vertex) && PlayerInspector.canBuildCity(player)) {
            City city = new City(player);
            vertex.placeBuilding(city);
            player.payCost(cost);
            player.increaseSettlements();
            player.decreaseCities();
            return true;
        }
        return false;
    }

    public static boolean buildRoad(Player player, Edge edge, Map map) {
        ResourceCollection cost = Road.COST;
        if (ConstructionInspector.canBuildRoad(player, edge, map) && PlayerInspector.canBuildRoad(player)) {
            Road road = new Road(player);
            edge.buildRoad(road);
            player.payCost(cost);
            player.decreaseRoads();
            return true;
        }
        return false;
    }

    public static boolean buildFirstSettlementWithRoad(Player player, Vertex vertex, Edge edge, Map map) {
        if (ConstructionInspector.canBuildFirstSettlementWithRoad(vertex, edge, map)) {
            return buildFirstSettlement(player, vertex, map) && buildFirstRoad(player, vertex, edge, map);
        }
        return false;
    }
    public static boolean buildFirstSettlement(Player player, Vertex vertex, Map map) {
        if (ConstructionInspector.canBuildFirstSettlement(player, vertex, map)) {
            Settlement settlement = new Settlement(player);
            vertex.placeBuilding(settlement);
            player.decreaseSettlements();

            return true;
        }
        return false;
    }
    public static boolean buildFirstRoad(Player player, Vertex vertex, Edge edge, Map map) {
        if (ConstructionInspector.canBuildFirstRoad(player, vertex, edge, map)) {
            Road road = new Road(player);
            edge.buildRoad(road);
            player.decreaseRoads();
            return true;
        }
        return false;
    }
}
