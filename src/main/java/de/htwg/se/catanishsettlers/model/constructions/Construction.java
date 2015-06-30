package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Investment;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

/**
 * Created by Stephan on 01.04.2015.
 */
public abstract class Construction {
    protected Player owner;

    public Construction(Player player) {
        owner = player;
    }

    public Player getPlayer() {
        return owner;
    }
}
