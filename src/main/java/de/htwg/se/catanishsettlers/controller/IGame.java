package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.List;

/**
 * This is the Game Catanish Settlers. You can control and play the game with this class.
 * Created by Jonathan on 02.07.2015.
 */
interface IGame {

    /**
     * You can build your First Settlement combined with a Road during the Preparation Phase with this method.
     * The settlement and the road have to be next to each other. Otherwise this will return false and not build anything.
     *
     * @param player  Player who wants to build the settlement and the road
     * @param xVertex x-Position of the vertex, where the settlement will be build
     * @param yVertex y-Position of the vertex, where the settlement will be build
     * @param xEdge   x-Position of the Edge, where the road will be placed
     * @param yEdge   y-Position of the Edge, where the road will be placed
     * @return true if succesful and the settlement and the road were placed, otherwise false
     */
    boolean buildFirstSettlementWithRoad(Player player, int xVertex, int yVertex, int xEdge, int yEdge);

    /**
     * You can build your First Settlement during the Preparation Phase with this method.
     *
     * @param player Player who wants to build the settlement
     * @param vertex vertex, where the settlement will be build
     * @return true if succesful and the settlement was placed, otherwise false
     */
    boolean buildFirstSettlement(Player player, Vertex vertex);

    /**
     * You can build your First Road during the Preparation Phase with this method.
     * There needs to be a Settlement next to the edge, where the road will be place, otherwise nothing will happen and this will return false.
     *
     * @param player Player who wants to build the road
     * @param vertex vertex, where the settlement is
     * @param edge   edge, where the road will be placed
     * @return true if succesful and the road was placed, otherwise false
     */
    boolean buildFirstRoad(Player player, Vertex vertex, Edge edge);

    /**
     * The currently active Player tries to build a settlement at given position.
     *
     * @param x x-position of the vertex, where the settlement will be build
     * @param y y-position of the vertex, where the settlement will be build
     * @return true if the settlement was successfully build, false if not
     */
    boolean buildSettlement(int x, int y);

    /**
     * The currently active Player tries to build a city at given position.
     *
     * @param x x-position of the vertex, where the city will be build
     * @param y y-position of the vertex, where the city will be build
     * @return true if the city was successfully build, false if not
     */
    boolean buildCity(int x, int y);

    /**
     * The currently active Player tries to build a road at given position.
     *
     * @param x x-position of the edge, where the road will be build
     * @param y y-position of the edge, where the road will be build
     * @return true if road can be built, otherwise false.
     */
    boolean buildRoad(int x, int y);

    /**
     * Checks if there is a winner yet. If there is/are a winner/s, they will be returned.
     *
     * @return list of winner(s)
     */
    List<Player> checkVictory();

    /**
     * This returns the sum of the dice numbers of the last roll.
     *
     * @return sum of dice numbers of last roll
     */
    int getLastRolledDiceNumber();

    /**
     * This returns the Dice of the game. You can see the single values of the dice through that.
     *
     * @return dice of the game
     */
    Dice getDice();

    /**
     * Tells you if there is a winner yet.
     *
     * @return is there a winner yet?
     */
    boolean isThereAWinner();

    /**
     * Tells you if you're currently in the buildingPhase/PostDiceRollState or not.
     *
     * @return is currently the building phase?
     */
    boolean isBuildingPhase();

    /**
     * Tells you if you're currently in the Preparation Phase or not.
     *
     * @return is currently the preparation phase?
     */
    boolean isPreparationPhase();

    /**
     * returns you the player at given position.
     *
     * @param i given position
     * @return player at given position
     */
    Player getPlayer(int i);

    /**
     * gives you the map of the current game.
     *
     * @return Map of the game
     */
    Map getMap();

    /**
     * This gives you the PlayerContainter, which contains all players of the current game.
     *
     * @return PlayerContainer with all players of the game
     */
    PlayerContainer getPlayerContainer();

    /**
     * Tells you whose turn it is currently.
     *
     * @return currently active player
     */
    Player getActivePlayer();

    /**
     * brings the game to the next Phase.
     * From GameSetupState to PreparationState to PreDiceRollState.
     * Then it goes between PreDiceRollState and PostDiceRollState/BuildingPhase
     * till someone wins and then this will bring it to the WinnerEndState.
     */
    void nextPhase();
}
