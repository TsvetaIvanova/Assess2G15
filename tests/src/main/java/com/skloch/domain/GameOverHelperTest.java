package com.skloch.domain;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameOverHelperTest {


    @Test
    public void canClassifyAsBookWormWhenEnoughHoursHaveBeenStudied() {
        GameOverHelper gameOverHelper=new GameOverHelper(8);
        assertTrue("It should have been classified as a BookWorm", gameOverHelper.isBookWorm());
    }


@Test
public void cannotClassifyAsBookWormWhenNotEnoughHoursHaveBeenStudied() {
    GameOverHelper gameOverHelper=new GameOverHelper(5);
    assertFalse("It shouldn't have been classified as a BookWorm ", gameOverHelper.isBookWorm());
}
}
