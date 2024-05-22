package com.skloch.domain;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class contains unit tests for the GameOverHelper class, that itself abstracts the logic in GameOverScreen
 * for the streaks in the game.
 * It verifies the correct classification of achievements and the calculation of bonus streaks.
 */
public class AchievementsTests {

    /**
     * Tests if a player is classified as a BookWorm when enough hours have been studied.
     */
    @Test
    public void canClassifyAsBookWormWhenEnoughHoursHaveBeenStudied() {
        GameOverHelper gameOverHelper = new GameOverHelper(8, 0, 0);
        assertTrue("It should have been classified as a BookWorm", gameOverHelper.isBookWorm());
    }
    /**
     * Tests if a player is not classified as a BookWorm when they have not studied enough.
     */
    @Test
    public void cannotClassifyAsBookWormWhenNotEnoughHoursHaveBeenStudied() {
        GameOverHelper gameOverHelper = new GameOverHelper(5, 0, 0);
        assertFalse("It shouldn't have been classified as a BookWorm", gameOverHelper.isBookWorm());
    }

    /**
     * Tests if a player is classified as Best Fisher when enough fish have been caught
     * to classify as a streak, greater than 5.
     */

    @Test
    public void canClassifyAsBestFisherWhenEnoughFishHaveBeenCaught() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 5, 0);
        assertTrue("It should have been classified as Best Fisher", gameOverHelper.isBestFisher());
    }

    /**
     * Tests if a player is not classified as Best Fisher when they have not fished enough
     * in the game
     */

    @Test
    public void cannotClassifyAsBestFisherWhenNotEnoughFishHaveBeenCaught() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 3, 0);
        assertFalse("It shouldn't have been classified as Best Fisher", gameOverHelper.isBestFisher());
    }

    /**
     * Tests if a player is classified as Duck Duck Go when enough ducks have been fed, greater than 5.
     */

    @Test
    public void canClassifyAsDuckDuckGoWhenEnoughDucksHaveBeenFed() {
        GameOverHelper gameOverHelper = new GameOverHelper(0, 0, 5);
        assertTrue("It should have been classified as Duck Duck Go", gameOverHelper.isDuckDuckGo());
    }

    /**
     * Tests if a player is not classified as Duck Duck Go when they have not fed enough fish.
     */

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
