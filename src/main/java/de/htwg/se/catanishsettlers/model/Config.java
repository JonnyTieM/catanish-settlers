package de.htwg.se.catanishsettlers.model;

import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 15.05.2015.
 */
public final class Config {

    private Config() {} // cannot be instantiated.

    // player config
    public final static int MAX_SETTLEMENTS = 5;
    public final static int MAX_CITIES = 4;
    public final static int MAX_ROADS = 15;


    // map config
    public final static int STANDARD_MAP_EDGE_LENGTH = 3;

    public final static int FIELDS_WIDTH = 5;
    public final static int FIELDS_HEIGHT = 5;

    public final static int EDGES_WIDTH = 6;
    public final static int EDGES_HEIGHT = 17;

    public final static int VERTICES_WIDTH = 6;
    public final static int VERTICES_HEIGHT = 12;


    // resources       (brick, lumber, wool, grain, ore)
    public final static ResourceCollection CARD_COST = new ResourceCollection(0, 0, 1, 1, 1);
    public final static ResourceCollection SETTLEMENT_COST = new ResourceCollection(1, 1, 1, 1, 0);
    public final static ResourceCollection CITY_COST = new ResourceCollection(0, 0, 0, 2, 3);
    public final static ResourceCollection ROAD_COST = new ResourceCollection(1, 1, 0, 0, 0);

    public final static int SETTLEMENT_YIELD = 1;
    public final static int CITY_YIELD = 2;


    // scores
    public final static int LARGEST_KNIGHT_ARMY = 2;
    public final static int LONGEST_TRADE_ROUTE = 2;

    public final static int CITY_SCORE = 2;
    public final static int SETTLEMENT_SCORE = 1;

    public final static int SCORE_TO_VICTORY = 10;


    // card distribution
    public final static int KNIGHTS_AMOUNT = 14;
    public final static int MONOPOLY_AMOUNT = 2;
    public final static int DEVELOPMENT_AMOUNT = 2;
    public final static int CONSTRUCTION_AMOUNT = 2;
    public final static int VICTORY_CARD_AMOUNT = 5;
}
