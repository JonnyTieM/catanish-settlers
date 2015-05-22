package de.htwg.se.catanishsettlers.model.map;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Jonathan on 22.05.2015.
 */
public final class TriggerNumberCreator {
    private TriggerNumberCreator() {

    }

    public static LinkedList<Integer> getRandomTriggerNumbers(int amount2, int amount3,
                                                              int amount4, int amount5,
                                                              int amount6, int amount8,
                                                              int amount9, int amount10,
                                                              int amount11, int amount12) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        LinkedList<Integer> listTemp = new LinkedList<Integer>();

        add(amount2, 2, listTemp);
        add(amount3, 3, listTemp);
        add(amount4, 4, listTemp);
        add(amount5, 5, listTemp);
        add(amount6, 6, listTemp);
        add(amount8, 8, listTemp);
        add(amount9, 9, listTemp);
        add(amount10, 10, listTemp);
        add(amount11, 11, listTemp);
        add(amount12, 12, listTemp);

        Random random = new Random();
        int i;
        while (!listTemp.isEmpty()) {
            i = random.nextInt(listTemp.size());
            list.add(listTemp.remove(i));
        }

        return list;
    }

    private static void add(int amount, int trigger, LinkedList<Integer> list) {
        for (int i = 0; i < amount; i++) {
            list.add(trigger);
        }
    }
}
