package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 01.07.2015.
 */
public class GameTest {
    Game game;
    Player hans;
    Player peter;

    @Before
    public void setUp() throws Exception {
        hans = new Player("Hans");
        peter = new Player("Peter");
        LinkedList<Player> players = new LinkedList<Player>();
        players.add(hans);
        players.add(peter);
        game = new Game(players);
        hans.addResources(new ResourceCollection(5, 5, 5, 5, 5));
        peter.addResources(new ResourceCollection(5, 5, 5, 5, 5));
    }

    @Test
    public void testSwitchPlayer() throws Exception {
        assertSame(hans, game.getActivePlayer());

        game.switchPlayer();
        assertSame(peter, game.getActivePlayer());
    }

    @Test
    public void testBuildFirstSettlementWithRoad() throws Exception {
        game.nextPhase();
        assertTrue(game.buildFirstSettlementWithRoad(hans, 2, 3, 2, 5));
        game.nextPhase();
        assertFalse(game.buildFirstSettlementWithRoad(peter, 2, 6, 2, 9));
    }

    @Test
    public void testBuildFirstSettlement() throws Exception {
        game.nextPhase();
        assertTrue(game.buildFirstSettlement(hans, 2, 3));
        game.nextPhase();
        assertFalse(game.buildFirstSettlement(peter, 2, 6));
    }

    @Test
    public void testBuildFirstRoad() throws Exception {
        game.nextPhase();
        assertFalse(game.buildFirstRoad(hans, 2, 3, 2, 5));
        game.buildFirstSettlement(hans, 2, 3);
        assertTrue(game.buildFirstRoad(hans, 2, 3, 2, 5));
        game.nextPhase();
        assertFalse(game.buildFirstRoad(peter, 2, 6, 2, 9));
        game.buildFirstSettlement(hans, 2, 6);
        assertFalse(game.buildFirstRoad(peter, 2, 6, 2, 9));
    }

    @Test
    public void testBuildSettlement() throws Exception {
        game.getMap().getEdge(2, 5).buildRoad(new Road(hans));
        game.nextPhase();
        game.nextPhase();
        assertFalse(game.buildSettlement(2, 3));
        game.nextPhase();
        assertTrue(game.buildSettlement(2, 3));
    }

    @Test
    public void testBuildCity() throws Exception {
        game.getMap().getVertex(2, 3).placeBuilding(new Settlement(hans));
        game.nextPhase();
        game.nextPhase();
        assertFalse(game.buildCity(2, 3));
        game.nextPhase();
        assertTrue(game.buildCity(2, 3));
    }

    @Test
    public void testBuildRoad() throws Exception {
        game.getMap().getEdge(2, 5).buildRoad(new Road(hans));
        game.nextPhase();
        game.nextPhase();
        assertFalse(game.buildRoad(2, 6));
        game.nextPhase();
        assertTrue(game.buildRoad(2, 6));
    }

    @Test
    public void testDistributeResources() throws Exception {
        List<Field> productiveFields = game.getMap().getFieldsWithTriggerNumber(6);
        Field one = productiveFields.get(0);
        Field two = productiveFields.get(1);

        Vertex[] verticesOne = game.getMap().getVertices(one);
        Vertex[] verticesTwo = game.getMap().getVertices(two);

        LinkedList<Vertex> commonVertices = new LinkedList<Vertex>();

        for(Vertex vertexOne : verticesOne) {
            for (Vertex vertexTwo : verticesTwo) {
                if (vertexOne == vertexTwo) {
                    commonVertices.add(vertexOne);
                }
            }
        }

        for(Vertex vertexOne : verticesOne) {
            if (!commonVertices.contains(vertexOne)) {
                vertexOne.placeBuilding(new Settlement(hans));
                break;
            }
        }

        for (Vertex vertexTwo : verticesTwo) {
            if (!commonVertices.contains(vertexTwo)) {
                vertexTwo.placeBuilding(new City(hans));
                break;
            }
        }

        ResourceCollection result = new ResourceCollection(5,5,5,5,5);
        result.add(one.getResourceType(), 1);
        result.add(two.getResourceType(), 2);

        game.distributeResources(6);

        assertTrue(hans.getResources().compareTo(result) == 0);
    }

    @Test
    public void testCheckVictory() throws Exception {

    }

    @Test
    public void testBuyCard() throws Exception {

    }

    @Test
    public void testGetLastRolledDiceNumber() throws Exception {

    }

    @Test
    public void testGetDice() throws Exception {

    }

    @Test
    public void testIsThereAWinner() throws Exception {

    }

    @Test
    public void testIsBuildingPhase() throws Exception {

    }

    @Test
    public void testIsPreparationPhase() throws Exception {

    }

    @Test
    public void testGetPlayer() throws Exception {

    }

    @Test
    public void testSetState() throws Exception {

    }

    @Test
    public void testGetMap() throws Exception {

    }

    @Test
    public void testGetPlayerContainer() throws Exception {

    }

    @Test
    public void testGetActivePlayer() throws Exception {

    }

    @Test
    public void testNextPhase() throws Exception {

    }
}