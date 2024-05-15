package com.skloch.domain;

public class GameOverHelper {
    private int hoursStudied;


    public GameOverHelper(int hoursStudied) {
        this.hoursStudied = hoursStudied;
    }

    public int calculateBonusStreak(){
        int bonusStreaks = 0;
        if (isBookWorm()) {
            bonusStreaks += 5;
        }
        return bonusStreaks;
    }

    public boolean isBookWorm(){
        return hoursStudied >= 7 && hoursStudied < 10;
    }

}
