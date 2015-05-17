package de.htwg.se.catanishsettlers.model.newmap;

import java.util.List;

/**
 * Created by Stephan on 17.05.2015.
 */
public class Board {

    private List<Tile> tiles;

    public Board(int radius) {
        Tile center = new Tile(0, 0);
        for(int r = 0; r < radius; r++) {
            for(int n = 0; n < r; n++) {

            }
        }
    }
}
