package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.resources.ResourceCollection;

/**
 * Created by Stephan on 02.04.2015.
 */
public abstract class Building extends Construction{
    //public static int SCORE;    // how many victory points? //this field not needed. look it up in Config
    protected int yield;    // how many resources gathered?
    private ResourceCollection collectedResources;    // temporal storage for resources from fields

    public Building(Player player) {
        super(player);
        collectedResources = new ResourceCollection();
    }

    public int getYield() { return yield; }

//    public ResourceCollection yieldResources() {
//        ResourceCollection collectedResources = new ResourceCollection();
//
//        Vertex vertex = (Vertex)getPosition();
//        for(Field surroundingField : vertex.getSurroundingFields()) {
//            EResource resource = surroundingField.getResourceType();
//            collectedResources.add(resource, yield);
//        }
//        return collectedResources;
//    }
}
