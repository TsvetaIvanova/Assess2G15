package com.skloch.domain;
import org.

public class GameOverHelperTest {


    @Test
    public void canClassifyAsBookWormWhenEnoughHoursHaveBeenStudied() {
        GameOverHelper gameOverHelper=new GameOverHelper(8);
        assertTrue("It should have been classified as a BookWorm", gameOverHelper.isBookWorm());
    }
}
