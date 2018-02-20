package models;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class testEnglishDeck {
    @Test
    public void testgetSize() {
        Deck d = new EnglishDeck();
        assertEquals(d.getSize(), 52);
    }

    @Test
    public void testConstructor() {
        Deck d = new EnglishDeck();
        int value = 2;
        for(int i = 0; i < 52; i+= 4){
            assertEquals(d.deck.get(i).getValue(), value);
            assertEquals(d.deck.get(i).getSuit(), Suit.Clubs);

            assertEquals(d.deck.get(i+1).getValue(), value);
            assertEquals(d.deck.get(i+1).getSuit(), Suit.Hearts);

            assertEquals(d.deck.get(i+2).getValue(), value);
            assertEquals(d.deck.get(i+2).getSuit(), Suit.Diamonds);

            assertEquals(d.deck.get(i+3).getValue(), value);
            assertEquals(d.deck.get(i+3).getSuit(), Suit.Spades);
            value++;
        }
    }

    @Test
    public void testDraw() {
        Deck d = new EnglishDeck();
        Card lastCard = d.deck.get(51);
        Card c = d.draw();
        assertEquals(d.getSize(), 51);
        assertEquals(lastCard, c);
    }
    @Test
    public void testShuffle(){
        Deck d = new EnglishDeck();
        Deck dShuffled = new EnglishDeck();
        dShuffled.shuffle();
        Assert.assertNotEquals(dShuffled, d);

    }

}