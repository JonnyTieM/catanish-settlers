package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 15.05.2015.
 */
public class PlayerInspector {

    private PlayerInspector() {}    // cannot be instantiated.

    private static boolean hasEnoughSettlements(Player player) {
        return player.getAvailableSettlements() > 0;
    }

    private static boolean hasEnoughCities(Player player) {
        return player.getAvailableCities() > 0;
    }

    private static boolean hasEnoughRoads(Player player) {
        return player.getAvailableRoads() > 0;
    }

    private static boolean hasEnoughResources(Player player, ResourceCollection cost) {
        return player.hasEnoughResources(cost);
    }

    public static boolean canBuildSettlement(Player player) {
        return (hasEnoughSettlements(player) && hasEnoughResources(player, Settlement.COST));
    }

    public static boolean canBuildCity(Player player) {
        return (hasEnoughCities(player) && hasEnoughResources(player, City.COST));
    }

    public static boolean canBuildRoad(Player player) {
        return (hasEnoughRoads(player) && hasEnoughResources(player, Road.COST));
    }
}
