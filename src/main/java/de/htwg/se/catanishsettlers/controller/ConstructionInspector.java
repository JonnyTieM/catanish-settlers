package de.htwg.se.catanishsettlers.controller;

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
        return false;
    }

    public static boolean canBuildCity(Player player, Vertex vertex, Map map) {
        return false;
    }

    public static boolean canBuildRoad(Player player, Edge edge, Map map) {
        return false;
    }
}
