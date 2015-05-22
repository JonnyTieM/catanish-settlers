package de.htwg.se.catanishsettlers.model.resources;

import java.util.LinkedList;

/**
 * Created by JonnyTieM on 23.04.2015.
 */
public enum EResource {
    BRICK, GRAIN, LUMBER, ORE, WOOL;

    public static LinkedList<EResource> getRandomResourceList(int amountBrick, int amountGrain, int amountLumber, int amountOre, int amountWool) {
        LinkedList<EResource> list = new LinkedList<EResource>();
        add(amountBrick, BRICK, list);
        add(amountGrain, GRAIN, list);
        add(amountLumber, LUMBER, list);
        add(amountOre, ORE, list);
        add(amountWool, WOOL, list);
        return list;
    }

    private static void add(int amount, EResource res, LinkedList<EResource> list) {
        for (int i = 0; i < amount; i++) {
            list.add(res);
        }
    }
}
