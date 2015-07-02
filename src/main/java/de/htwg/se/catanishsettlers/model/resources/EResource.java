package de.htwg.se.catanishsettlers.model.resources;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by JonnyTieM on 23.04.2015.
 */
public enum EResource {
    BRICK, GRAIN, LUMBER, ORE, WOOL;

    public static Deque<EResource> getRandomResourceList(int amountBrick, int amountGrain, int amountLumber, int amountOre, int amountWool) {
        Deque<EResource> list = new LinkedList<EResource>();
        List<EResource> listTemp = new LinkedList<EResource>();
        add(amountBrick, BRICK, listTemp);
        add(amountGrain, GRAIN, listTemp);
        add(amountLumber, LUMBER, listTemp);
        add(amountOre, ORE, listTemp);
        add(amountWool, WOOL, listTemp);
        Random random = new Random();
        int i;
        while (!listTemp.isEmpty()) {
            i = random.nextInt(listTemp.size());
            list.add(listTemp.remove(i));
        }
        return list;
    }

    private static void add(int amount, EResource res, List<EResource> list) {
        for (int i = 0; i < amount; i++) {
            list.add(res);
        }
    }
}
