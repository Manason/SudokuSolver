package models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSpanishDeck {
    @Test
    public void testConstructor() {
        SpanishDeck d = new SpanishDeck();
        int value = 1;
        for(int i = 0; i < 48; i += 4){
            assertEquals(d.deck.get(i).getValue(), value);
            assertEquals(d.deck.get(i).getSuit(), Suit.Bastos);

            assertEquals(d.deck.get(i+1).getValue(), value);
            assertEquals(d.deck.get(i+1).getSuit(), Suit.Oros);

            assertEquals(d.deck.get(i+2).getValue(), value);
            assertEquals(d.deck.get(i+2).getSuit(), Suit.Copas);

            assertEquals(d.deck.get(i+3).getValue(), value);
            assertEquals(d.deck.get(i+3).getSuit(), Suit.Espadas);
            value++;
        }

        assertEquals(d.deck.get(48).getValue(), 13);
        assertEquals(d.deck.get(48).getSuit(), Suit.Joker);

        assertEquals(d.deck.get(49).getValue(), 13);
        assertEquals(d.deck.get(49).getSuit(), Suit.Joker);
    }
}