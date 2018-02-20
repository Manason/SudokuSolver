
package models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testEnglishColumn {

    @Test
    public void testRemove() {
        EnglishColumns col = new EnglishColumns();
        Card c = new Card(13,Suit.Hearts);
        Card d = new Card( 9, Suit.Hearts);
        col.addCard(c, 1);
        col.addCard(d, 2);
        col.remove(2);
        Assert.assertNotEquals(col.columnSize(1), col.columnSize(2));
    }

    @Test
    public void testMove() {
        EnglishColumns col = new EnglishColumns();
        Card c = new Card(14, Suit.Clubs);
        Card d = new Card(12, Suit.Clubs);
        col.addCard(c, 2);
        col.addCard(d, 3);
        col.move(2, 1);
        Assert.assertEquals(0, col.columnSize(2));
        col.move(3, 2);
    }

}

