package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.*;

/**
 * Created by Stephan on 01.04.2015.
 */
public final class Settlement extends Building{
    public Settlement(Player player) {
        super(player);
        cost = new ResourceCollection(1, 1, 1, 1, 0);
        score = 1;
        yield = 1;
    }
}
