package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class testSpanishGame {

    @Test
    public void testReset() {
        SpanishGame g = new SpanishGame();
        g.resetGame();
    }

    @Test
    public void testDealFour() {
        SpanishGame g = new SpanishGame();
        g.dealFour();
        assertEquals(1, g.cols.columnSize(1));
        int i = 0;
        while(i < 12){
            g.dealFour();
            i++;
        }
        assertEquals(13, g.cols.columnSize(0));
        assertEquals(12, g.cols.columnSize(3));
    }
}