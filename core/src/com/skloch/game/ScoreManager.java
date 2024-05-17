package com.skloch.game;

import java.util.Arrays;

public class ScoreManager {
    private static double totalEatScore = 0, totalRecreationScore = 0;
    public static int[] dayEatScore = {0,0,0}, dayRecreationScore = {0,0,0};

    public static void updateEatScore(){
        int sum = Arrays.stream(dayEatScore).sum();
        boolean allOnes = true;

        for(int i = 0; i < dayEatScore.length; i++){
            if(dayEatScore[i] != 1){
                allOnes = false;
                break;
            }
        }

        if(allOnes){
            totalEatScore += sum * 2;
        }
        else{
            totalEatScore += sum;
        }

        resetDayEatScore();
    }

    public static void updateRecreationScore(){
        int sum = Arrays.stream(dayRecreationScore).sum();
        int numZeros = 0;
        double multiplier = 0;

        for(int i = 0; i < dayRecreationScore.length; i++){
            if(dayRecreationScore[i] == 0){
                numZeros++;
            }
        }
        if(numZeros == 0){
            multiplier = 2;
        } else if (numZeros == 1) {
            multiplier = 1;
        } else if (numZeros == 2) {
            multiplier = 0.5;
        }

        totalRecreationScore += (sum * multiplier);

        resetDayRecreationScore();
    }

    public static void resetDayEatScore(){
        dayEatScore[0] = 0;
        dayEatScore[1] = 0;
        dayEatScore[2] = 0;
    }

    public static void resetDayRecreationScore(){
        dayRecreationScore[0] = 0;
        dayRecreationScore[1] = 0;
        dayRecreationScore[2] = 0;
    }

    public static int getTotalEatScore(){
        return (int) totalEatScore;
    }

    public static int getTotalRecreationScore(){
        return (int) totalRecreationScore;
    }
    public static void setTotalEatScore(int val){
        totalEatScore = val;
    }
    public static void setTotalRecreationScore(int val){
        totalRecreationScore = val;
    }
}
