package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

/**
 * Created by Stephan on 02.04.2015.
 */
public abstract class Building extends Construction {
    //public static int SCORE;    // how many victory points? //this field not needed. look it up in Config
    int yield;    // how many resources gathered?

    Building(Player player) {
        super(player);
    }

    public int getYield() {
        return yield;
    }
}
