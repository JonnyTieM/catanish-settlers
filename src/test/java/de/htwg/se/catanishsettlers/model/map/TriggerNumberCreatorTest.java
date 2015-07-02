package de.htwg.se.catanishsettlers.model.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class TriggerNumberCreatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetRandomTriggerNumbers() throws Exception {
        Deque<Integer> list = TriggerNumberCreator.getRandomTriggerNumbers(2,0,0,0,3,0,0,0,0,0);
        int two = 0;
        int six = 0;

        while (!list.isEmpty()) {
            int x = list.pop();
            if (x == 2) {
                two++;
            } else  if(x == 6) {
                six++;
            }
        }
        assertEquals(2, two);
        assertEquals(3, six);
    }
}