package com.skloch.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added in version 1.1
 * This class handles the leaderboard.csv file and manages
 * an internal ArrayList representation of the leaderboard
 * All display / usage of these methods is done in LeaderboardScreen.java
 */
//purely handles leaderboard / file handling logic
public class Leaderboard {

    /**
     * //NEW//-TEAM15-IMPLEMENTATION: class added in version 1.1
     * Internal class to represent the (name,score) values in each
     * row of a leaderboard entry
     */
    static public class LeaderboardEntry {
        String name;
        float score;

        /**
         * Constructor - create an instance of a LeaderboardEntry
         * @param name this entry's name
         * @param score this user's score
         */
        public LeaderboardEntry(String name, float score) {
            this.name = name;
            this.score = score;
        }
    }

    private FileHandle leaderboardFile;

    /**
     * Constructor - create an instance of a Leaderboard
     * Finds the leaderboard.csv file in the assets folder
     * Provides a console log if not found
     */
    public Leaderboard(){
        leaderboardFile = Gdx.files.internal("leaderboard.csv");
        if (!leaderboardFile.exists()) {
            System.out.println("Leaderboard file not found");
        }
    }

    /**
     * Parses the leaderboard.csv file and converts to an internal ArrayList representation
     * @return An ArrayList of LeaderboardEntry
     */
    public ArrayList<LeaderboardEntry> parseLeaderboard() {
        //read leaderboard.csv and generate an ArrayList of LeaderboardEntry
        ArrayList<LeaderboardEntry> list = new ArrayList<LeaderboardEntry>();

        String fileContents = leaderboardFile.readString();
        String[] lines = fileContents.split("\n");

        for (String s : lines) {
            //csv file so split by comma - [0] = name, [1] = score
            String[] lineContent = s.trim().split(",");

            if (lineContent.length != 2) {
                //save data is not formatted correctly!!
                break;
            }

            //create new LeaderboardEntry and add to the ArrayList
            LeaderboardEntry thisEntry = new LeaderboardEntry(lineContent[0], Float.parseFloat(lineContent[1]));
            list.add(thisEntry);
        }

        return list;
    }

    /**
     * Given a name and a score, updates the leaderboard.csv file if necessary
     * @param name the player's name
     * @param finalScore the player's final score
     * @return boolean - true if the leaderboard was updated
     */
    public boolean updateLeaderboard(String name, float finalScore) {
        //if the new score is in the top 10, update leaderboard.csv and return true
        //else return false

        ArrayList<LeaderboardEntry> entries = parseLeaderboard();
        LeaderboardEntry newEntry = new LeaderboardEntry(name, finalScore);

        //test if we should add the new finalScore to the leaderboard file
        //IMPORTANT: leaderboard.csv is stored SORTED by score, and so entries is too
        boolean updated = false;
        for (int i = 0; i < 10; i++) {
            try {
                //assuming there are 10 entries, look for where the new finalScore might fit in
                if (entries.get(i).score < finalScore) {
                    entries.add(i, newEntry);
                    updated = true;
                    break;
                }
            } catch (Exception e) {
                //less than 10 entries, and hasn't been placed yet
                //add to the end of the array
                entries.add(newEntry);
                updated = true;
                break;
            }
        }

        if (updated) {
            //if we add a new entry into the top 10 (and we actually have 10 items), we must remove the old last entry
            if (entries.size() > 10) {
                entries.remove(entries.size() - 1);
            }

            //update csv file (overwrite with new entries array)
            String newFileContent = "";
            for (LeaderboardEntry entry : entries) {
                newFileContent += entry.name + "," + entry.score + "\n";
            }
            leaderboardFile.writeString(newFileContent, false);
        }

        return updated;
    }

}
