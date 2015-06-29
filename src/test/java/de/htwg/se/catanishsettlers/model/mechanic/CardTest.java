package de.htwg.se.catanishsettlers.model.mechanic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 29.06.2015.
 */
public class CardTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetType() throws Exception {
        Card card = new Card(Card.Types.KNIGHT);
        assertSame(Card.Types.KNIGHT, card.getType());
    }
}