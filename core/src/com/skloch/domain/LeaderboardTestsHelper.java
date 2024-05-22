package com.skloch.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.skloch.game.Leaderboard;
import java.util.ArrayList;

public class LeaderboardTestsHelper {

    public static FileHandle leaderboardFileReference = Gdx.files.local("../assets/leaderboard.csv");
    //public static Leaderboard leaderboardInstance = new Leaderboard();

    public static FileHandle getLeaderboardFileReference() {
        //System.out.println(Gdx.files.getLocalStoragePath());
        //System.out.println("hello");
        return leaderboardFileReference;
    }

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



}
