package com.skloch.domain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AchievementManagerTests {
    private AchievementManagerHelper achievementManager;

    @Before
    public void setUp() {
        achievementManager = new AchievementManagerHelper(0, 0, 0);
    }

    @Test
    public void testCalculateAchievements_Bookworm() {
        achievementManager = new AchievementManagerHelper(8, 0, 0);
        achievementManager.calculateAchievements();
        assertEquals(5, achievementManager.getBonusStreaks());
    }

    @Test
    public void testCalculateAchievements_DuckDuckGo() {
        achievementManager = new AchievementManagerHelper(0, 5, 0);
        achievementManager.calculateAchievements();
        assertEquals(5, achievementManager.getBonusStreaks());
    }

    @Test
    public void testCalculateAchievements_BestFisher() {
        achievementManager = new AchievementManagerHelper(0, 0, 5);
        achievementManager.calculateAchievements();
        assertEquals(5, achievementManager.getBonusStreaks());
    }

    @Test
    public void testCalculateAchievements_Multiple() {
        achievementManager = new AchievementManagerHelper(8, 5, 5);
        achievementManager.calculateAchievements();
        assertEquals(15, achievementManager.getBonusStreaks());
    }

    @Test
    public void testCalculateFinalScore() {
        achievementManager = new AchievementManagerHelper(9, 5, 5);
        achievementManager.calculateAchievements();
        float finalScore = achievementManager.calculateFinalScore(30, 20);
        assertEquals(83, finalScore,0);
    }

    @Test
    public void testCalculateFinalScore_Overwork() {
        achievementManager = new AchievementManagerHelper(11, 0, 0);
        achievementManager.calculateAchievements();
        float finalScore = achievementManager.calculateFinalScore(30, 20);
        assertEquals(55.0, finalScore, 0);
    }
}
