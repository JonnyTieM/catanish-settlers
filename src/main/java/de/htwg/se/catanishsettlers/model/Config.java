package de.htwg.se.catanishsettlers.model;

import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 15.05.2015.
 */
public final class Config {

    private Config() {
    } // cannot be instantiated.

    // player config
    public static final int MAX_SETTLEMENTS = 5;
    public static final int MAX_CITIES = 4;
    public static final int MAX_ROADS = 15;

    // map config
    public static final int STANDARD_MAP_EDGE_LENGTH = 3;

    public static final int FIELDS_WIDTH = 5;
    public static final int FIELDS_HEIGHT = 5;

    public static final int EDGES_WIDTH = 6;
    public static final int EDGES_HEIGHT = 17;

    public static final int VERTICES_WIDTH = 6;
    public static final int VERTICES_HEIGHT = 12;


    // resources       (brick, lumber, wool, grain, ore)
    public static final ResourceCollection CARD_COST = new ResourceCollection(0, 0, 1, 1, 1);
    public static final ResourceCollection SETTLEMENT_COST = new ResourceCollection(1, 1, 1, 1, 0);
    public static final ResourceCollection CITY_COST = new ResourceCollection(0, 0, 0, 2, 3);
    public static final ResourceCollection ROAD_COST = new ResourceCollection(1, 1, 0, 0, 0);

    public static final int SETTLEMENT_YIELD = 1;
    public static final int CITY_YIELD = 2;


    // scores
    public static final int LARGEST_KNIGHT_ARMY = 2;
    public static final int LONGEST_TRADE_ROUTE = 2;

    public static final int CITY_SCORE = 2;
    public static final int SETTLEMENT_SCORE = 1;

    public static final int SCORE_TO_VICTORY = 10;


    // card distribution
    public static final int KNIGHTS_AMOUNT = 14;
    public static final int MONOPOLY_AMOUNT = 2;
    public static final int DEVELOPMENT_AMOUNT = 2;
    public static final int CONSTRUCTION_AMOUNT = 2;
    public static final int VICTORY_CARD_AMOUNT = 5;
}
