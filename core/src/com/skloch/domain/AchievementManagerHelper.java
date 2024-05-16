package com.skloch.domain;

public class AchievementManagerHelper {
    private int bonusStreaks = 0;
    private int hoursStudied;
    private int duckFeeds;
    private int fishCaught;

    public AchievementManagerHelper(int hoursStudied, int duckFeeds, int fishCaught) {
        this.hoursStudied = hoursStudied;
        this.duckFeeds = duckFeeds;
        this.fishCaught = fishCaught;
    }

    public void calculateAchievements() {
        bonusStreaks = 0;

        if (hoursStudied >= 7 && hoursStudied < 10) {
            bonusStreaks += 5;
        }

        if (duckFeeds >= 5) {
            bonusStreaks += 5;
        }

        if (fishCaught >= 5) {
            bonusStreaks += 5;
        }
    }

    public int getBonusStreaks() {
        return bonusStreaks;
    }

    public float calculateFinalScore(float recreationalScore, float eatingScore) {
        int finalStudyScore = hoursStudied;

        if (hoursStudied >= 8 && hoursStudied <= 10) {
            finalStudyScore *= 2;
        } else if (hoursStudied > 10) {
            finalStudyScore /= 2;
        }

        return finalStudyScore + recreationalScore + eatingScore + bonusStreaks;
    }
}