package com.skloch.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.skloch.game.Leaderboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;


@RunWith(org.example.GdxTestRunner.class)
public class LeaderboardTests {

    FileHandle leaderboardFileReference = LeaderboardTestsHelper.getLeaderboardFileReference();


    @Test
    public void leaderboardFileExists() {
        assertTrue("Leaderboard file does not exist", leaderboardFileReference.exists());
    }

    @Test
    public void tenOrLessEntries() {
        int entries = LeaderboardTestsHelper.mockParsingLeaderboard();
        assertTrue("Leaderboard data formatted incorrectly", entries != -1);
        assertTrue("Leaderboard has more than 10 entries", entries <= 10 && entries >= 0);
    }

    @Test
    public void addToLeaderboardLowScore() {
        boolean added = LeaderboardTestsHelper.mockAddingToLeaderboard(0);
        //THIS IS ASSUMING A SCORE OF 0 SHOULD NOT BE ADDED
        //IF THERE IS <10 ENTRIES OR A 0 IN THE LEADERBOARD THIS TEST WILL FAIL
        assertFalse("Score of 0 added to leaderboard", added);
    }

    @Test
    public void addToLeaderboardHighScore() {
        boolean added = LeaderboardTestsHelper.mockAddingToLeaderboard(1000);
        assertTrue("Score of 1000 not added to leaderboard", added);
    }
}
