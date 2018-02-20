package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class testCard {
    @Test
    public void testGetSuit() {
        Card c = new Card(5, Suit.Clubs);
        assertEquals(Suit.Clubs, c.getSuit());
    }

    @Test
    public void testToString() {
        Card c = new Card(5, Suit.Clubs);
        assertEquals("5Clubs", c.toString());
        c.getValue();
        c.getSuit();
    }
}