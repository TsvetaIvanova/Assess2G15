package com.skloch.domain;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added in version 1.1
 * This class helps abstract some of the logic from the GameOverScreen class;
 * It defines the logic for the achievements/streaks for "fishing", "ducks" and studying
 */

public class GameOverHelper {
    private int hoursStudied;
    private int fishCaught;
    private int duckFeeds;

    /**
     * Constructor to initialize GameOverHelper with specific values.
     *
     * @param hoursStudied The number of hours studied by the player.
     * @param fishCaught   The number of fish caught by the player.
     * @param duckFeeds    The number of ducks fed by the player.
     */

    public GameOverHelper(int hoursStudied, int fishCaught, int duckFeeds) {
        this.hoursStudied = hoursStudied;
        this.fishCaught = fishCaught;
        this.duckFeeds = duckFeeds;
    }

    public GameOverHelper() {
    }

    /**
     * This method adds the bonus points
     */
    public int calculateBonusStreak() {
        int bonusStreaks = 0;
        if (isBookWorm()) {
            bonusStreaks += 5;
        }
        if (isBestFisher()) {
            bonusStreaks += 5;
        }
        if (isDuckDuckGo()) {
            bonusStreaks += 5;
        }
        return bonusStreaks;
    }

    /**
     * These methods define the logic for the streaks
     */

    public boolean isBookWorm() {
        return hoursStudied >= 7 && hoursStudied < 10;
    }

    public boolean isBestFisher() {
        return fishCaught >= 5;
    }

    public boolean isDuckDuckGo() {
        return duckFeeds >= 5;
    }
}
