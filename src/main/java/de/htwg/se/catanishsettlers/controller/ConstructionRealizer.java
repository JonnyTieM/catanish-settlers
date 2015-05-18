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
public final class ConstructionRealizer {

    private ConstructionRealizer() {}   // cannot be initialized

    public static boolean buildSettlement(Player player, Vertex vertex, Map map) {
        ResourceCollection cost = Settlement.COST;
        if (ConstructionInspector.canBuildSettlement(player, vertex, map) && PlayerInspector.canBuildSettlement(player)) {
            Settlement settlement = new Settlement(player);
            vertex.placeBuilding(settlement);
            player.payCost(cost);
            player.settlements--;
            return true;
        }
        return false;
    }

    public static boolean buildCity(Player player, Vertex vertex, Map map) {
        ResourceCollection cost = City.COST;
        if (ConstructionInspector.canBuildCity(player, vertex, map) && PlayerInspector.canBuildCity(player)) {
            City city = new City(player);
            vertex.placeBuilding(city);
            player.payCost(cost);
            player.settlements++;
            player.cities--;
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
            player.roads--;
            return true;
        }
        return false;
    }
}