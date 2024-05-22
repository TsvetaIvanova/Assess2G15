package com.skloch.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.badlogic.gdx.files.FileHandle;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added version 1.1
 * This class contains unit tests for the Leaderboard class
 * It verifies the leaderboard file exists, it has a valid size,
 * and that items are added to it correctly.
 */
@RunWith(org.example.GdxTestRunner.class)
public class LeaderboardTests {

    FileHandle leaderboardFileReference = LeaderboardTestsHelper.getLeaderboardFileReference();

    /**
     * Tests if the leaderboard.csv file exists
     */
    @Test
    public void leaderboardFileExists() {
        assertTrue("Leaderboard file does not exist", leaderboardFileReference.exists());
    }

    /**
     * Tests if the leaderboard has ten or fewer entries
     * If the entries are incorrectly formatted, this test will also fail with a different error message
     */
    @Test
    public void tenOrLessEntries() {
        int entries = LeaderboardTestsHelper.mockParsingLeaderboard();
        assertTrue("Leaderboard data formatted incorrectly", entries != -1);
        assertTrue("Leaderboard has more than 10 entries", entries <= 10 && entries >= 0);
    }

    /**
     * Tests if we can add a score too low for the current leaderboard
     */
    @Test
    public void addToLeaderboardLowScore() {
        boolean added = LeaderboardTestsHelper.mockAddingToLeaderboard(0);
        //THIS IS ASSUMING A SCORE OF 0 SHOULD NOT BE ADDED
        //IF THERE IS <10 ENTRIES OR A 0 IN THE LEADERBOARD THIS TEST WILL FAIL
        assertFalse("Score of 0 added to leaderboard", added);
    }

    /**
     * Tests if we can add a valid score to the leaderboard
     */
    @Test
    public void addToLeaderboardHighScore() {
        boolean added = LeaderboardTestsHelper.mockAddingToLeaderboard(1000);
        assertTrue("Score of 1000 not added to leaderboard", added);
    }
}
