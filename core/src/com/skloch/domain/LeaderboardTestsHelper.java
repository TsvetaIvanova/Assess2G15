package com.skloch.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added in version 1.1
 * This class helps abstract some of the logic from the Leaderboard class
 * Specifically, reading in leaderboard.csv and trying to add to the leaderboard
 */
public class LeaderboardTestsHelper {

    //get a reference to leaderboard.csv from the assets folder
    public static FileHandle leaderboardFileReference = Gdx.files.local("../assets/leaderboard.csv");

    /**
     * Returns the leaderboard file reference
     * @return the leaderboard file reference
     */
    public static FileHandle getLeaderboardFileReference() {
        return leaderboardFileReference;
    }

    /**
     * Mocks parsing the CSV file, abstracting away the ArrayList implementation
     * and simply counting the number of entries in the leaderboard
     * Used to test if the length of the leaderboard is valid.
     * @return the number of entries in the leaderboard
     */
    public static int mockParsingLeaderboard() {
        int count = 0;
        String fileContents = leaderboardFileReference.readString();
        String[] lines = fileContents.split("\n");

        for (String s : lines) {
            //csv file so split by comma - [0] = name, [1] = score
            String[] lineContent = s.trim().split(",");

            if (lineContent.length != 2) {
                //save data is not formatted correctly!!
                count = -1;
                break;
            }

            count++;

        }

        return count;
    }

    /**
     * Mocks adding a new entry to the leaderboard, abstracting away the ArrayList implementation
     * @return true if a score would be added to the leaderboard
     */
    public static boolean mockAddingToLeaderboard(int inputScore) {

        boolean gettingAdded = false;

        String fileContents = leaderboardFileReference.readString();
        String[] lines = fileContents.split("\n");
        for (String s : lines) {
            //csv file so split by comma - [0] = name, [1] = score
            String[] lineContent = s.trim().split(",");
            float lineScore = Float.parseFloat(lineContent[1]);

            if (inputScore > lineScore) {
                gettingAdded = true;
            }
        }

        return gettingAdded;
    }

}
