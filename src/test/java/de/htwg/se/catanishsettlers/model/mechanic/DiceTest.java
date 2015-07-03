package de.htwg.se.catanishsettlers.model.mechanic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class DiceTest {
    private Dice dice;

    @Before
    public void setUp() throws Exception {
        dice = new Dice(2,6);
    }

    @Test
    public void testRoll() throws Exception {
        boolean thisWorks = false;
        dice.roll();
        int rolledValue = dice.getValue();

        for (int i = 0; i < 100000; i++) {
            dice.roll();
            if (rolledValue != dice.getValue()) {
                thisWorks = true;
                break;
            }
        }

        assertTrue(thisWorks);
    }

    @Test
    public void testGetValue() throws Exception {
        dice.roll();
        assertEquals(dice.getSingleValues()[0] + dice.getSingleValues()[1], dice.getValue());
    }

    @Test
    public void testGetSingleValues() throws Exception {
        dice.roll();
        assertEquals(dice.getValue(), dice.getSingleValues()[0] + dice.getSingleValues()[1]);
    }

    @Test
    public void testGetDiceCount() throws Exception {
        assertEquals(2, dice.getDiceCount());
    }
}