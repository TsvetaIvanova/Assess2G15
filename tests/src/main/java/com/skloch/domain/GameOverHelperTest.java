package com.skloch.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameOverHelperTest {

    @Test
    public void canClassifyAsBookWormWhenEnoughHoursHaveBeenStudied() {
        GameOverHelper gameOverHelper = new GameOverHelper(8, 0, 0);
        assertTrue("It should have been classified as a BookWorm", gameOverHelper.isBookWorm());
    }

    @Test
    public void cannotClassifyAsBookWormWhenNotEnoughHoursHaveBeenStudied() {
        GameOverHelper gameOverHelper = new GameOverHelper(5, 0, 0);
        assertFalse("It shouldn't have been classified as a BookWorm", gameOverHelper.isBookWorm());
    }

    @Test
    public void canClassifyAsBestFisherWhenEnoughFishHaveBeenCaught() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 5, 0);
        assertTrue("It should have been classified as Best Fisher", gameOverHelper.isBestFisher());
    }

    @Test
    public void cannotClassifyAsBestFisherWhenNotEnoughFishHaveBeenCaught() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 3, 0);
        assertFalse("It shouldn't have been classified as Best Fisher", gameOverHelper.isBestFisher());
    }

    @Test
    public void canClassifyAsDuckDuckGoWhenEnoughDucksHaveBeenFed() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 0, 5);
        assertTrue("It should have been classified as Duck Duck Go", gameOverHelper.isDuckDuckGo());
    }

    @Test
    public void cannotClassifyAsDuckDuckGoWhenNotEnoughDucksHaveBeenFed() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 0, 3);
        assertFalse("It shouldn't have been classified as Duck Duck Go", gameOverHelper.isDuckDuckGo());
    }

    @Test
    public void calculateTotalBonusStreaks() {
        GameOverHelper gameOverHelper = new GameOverHelper(8, 5, 5);
        assertEquals("The total bonus streaks should be 15", 15, gameOverHelper.calculateBonusStreak());
    }

    @Test
    public void calculateTotalBonusStreaksWhenNoAchievements() {
        GameOverHelper gameOverHelper = new GameOverHelper(6, 3, 2);
        assertEquals("The total bonus streaks should be 0", 0, gameOverHelper.calculateBonusStreak());
    }
}
