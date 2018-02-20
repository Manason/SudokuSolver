package models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSpanishColumn {

    @Test
    public void testRemove() {
        SpanishColumns col = new SpanishColumns();
        Card c = new Card(13,Suit.Joker);
        Card d = new Card( 5, Suit.Copas);
        col.addCard(c, 1);
        col.addCard(d, 3);
        col.remove(3);
        Assert.assertEquals(col.columnSize(1), col.columnSize(2));
        Assert.assertEquals(col.columnSize(1),0);
        col.addCard(d,1);
        Card e = new Card(7,Suit.Copas);
        col.addCard(e,3);
        col.remove(3);
        Assert.assertEquals(1,col.columnSize(3));
        col.remove(1);
        Assert.assertEquals(0,col.columnSize(1));
    }

    @Test
    public void testMove() {
        SpanishColumns col = new SpanishColumns();
        Card c = new Card(10, Suit.Copas);
        Card d = new Card(12, Suit.Copas);
        col.addCard(c, 2);
        col.addCard(d, 3);
        col.move(2, 1);
        Assert.assertEquals(0, col.columnSize(2));
        col.move(3, 2);
        Assert.assertEquals(1,col.columnSize(2));
    }

}