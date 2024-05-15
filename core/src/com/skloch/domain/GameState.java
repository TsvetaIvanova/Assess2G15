package com.skloch.domain;

import java.util.Arrays;

public class GameState {
    private int energy;
    private int[] daysStudied;
    private boolean catchUp;
    private int hoursStudied;
    private int recreationalHours;
    private int eatingScore;

    public GameState() {
        this.energy = 100;
        this.daysStudied = new int[]{0, 0, 0, 0, 0, 0, 0};
        this.catchUp = false;
        this.hoursStudied = 0;
        this.recreationalHours = 0;
        this.eatingScore = 0;
    }

    // Getters and setters
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int[] getDaysStudied() {
        return daysStudied;
    }

    public void setDaysStudied(int[] daysStudied) {
        this.daysStudied = daysStudied;
    }

    public boolean isCatchUp() {
        return catchUp;
    }

    public void setCatchUp(boolean catchUp) {
        this.catchUp = catchUp;
    }

    public int getHoursStudied() {
        return hoursStudied;
    }

    public void addStudyHours(int hours) {
        this.hoursStudied += hours;
    }

    public int getRecreationalHours() {
        return recreationalHours;
    }

    public void addRecreationalHours(int hours) {
        this.recreationalHours += hours;
    }

    public int getEatingScore() {
        return eatingScore;
    }

    public void addEatingScore(int score) {
        this.eatingScore += score;
    }

    public void decreaseEnergy(int amount) {
        this.energy -= amount;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void passTime(int minutes) {

    }
}
