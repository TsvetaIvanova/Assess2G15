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

}
