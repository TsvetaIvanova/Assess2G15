package com.skloch.domain;

public class GameOverHelper {
    private int hoursStudied;
    private int fishCaught;
    private int duckFeeds;

    public GameOverHelper(int hoursStudied, int fishCaught, int duckFeeds) {
        this.hoursStudied = hoursStudied;
        this.fishCaught = fishCaught;
        this.duckFeeds = duckFeeds;
    }

    public GameOverHelper() {
    }

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
