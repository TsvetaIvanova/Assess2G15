package com.skloch.domain;

public class StudyManager {
    private boolean catchUp = false;
    private int[] daysStudied = new int[]{0, 0, 0, 0, 0, 0, 0};
    private int energy = 100;
    private int studyHours = 0;

    public boolean isCatchUp() {
        return catchUp;
    }

    public int[] getDaysStudied() {
        return daysStudied;
    }

    public int getEnergy() {
        return energy;
    }

    public int getStudyHours() {
        return studyHours;
    }

    public void setDayStudied(int day, int hours) {
        this.daysStudied[day - 1] = hours;
    }

    public void setCatchUp(boolean catchUp) {
        this.catchUp = catchUp;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void addStudyHours(int hours) {
        this.studyHours += hours;
    }

    public void decreaseEnergy(int energy) {
        this.energy -= energy;
    }

    public void catchUpEvent(int day, int hours, String option) {
        int energyCost = 10; // Assuming studying always costs 10 energy per hour
        if (option.equals("1")) {
            decreaseEnergy(energyCost * hours);
            addStudyHours(hours);

            for (int i = 0; i < daysStudied.length; i++) {
                if (daysStudied[i] == 0) {
                    daysStudied[i] += 1;
                    daysStudied[day - 1] += hours - 1;
                    catchUp = true;
                    break;
                }
            }
        } else if (option.equals("2")) {
            decreaseEnergy(energyCost * hours);
            addStudyHours(hours);
            daysStudied[day - 1] += hours;
        }
    }
}
