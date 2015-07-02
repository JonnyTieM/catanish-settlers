package de.htwg.se.catanishsettlers.model.resources;

import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 26.06.2015.
 */
public class EResourceTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetRandomResourceList() throws Exception {
        Deque<EResource> randResources = EResource.getRandomResourceList(2,3,4,5,6);
        EResource res;
        int amountBrick = 0;
        int amountGrain = 0;
        int amountLumber = 0;
        int amountOre = 0;
        int amountWool = 0;
        while(!randResources.isEmpty()) {
            res = randResources.pop();
            switch (res) {
                case BRICK:
                    amountBrick++;
                    break;
                case GRAIN:
                    amountGrain++;
                    break;
                case LUMBER:
                    amountLumber++;
                    break;
                case ORE:
                    amountOre++;
                    break;
                case WOOL:
                    amountWool++;
                    break;
            }
        }
        assertEquals(2, amountBrick);
        assertEquals(3, amountGrain);
        assertEquals(4, amountLumber);
        assertEquals(5, amountOre);
        assertEquals(6, amountWool);
    }
}