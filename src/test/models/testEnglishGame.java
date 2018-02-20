package models;

import org.junit.Test;

import static org.junit.Assert.*;
public class testEnglishGame {

    @Test
    public void testDealFour() {
        EnglishGame g = new EnglishGame();
        g.dealFour();
        assertEquals(1, g.cols.columnSize(1));
    }
}
