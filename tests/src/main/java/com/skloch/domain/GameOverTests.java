package com.skloch.domain;
import com.skloch.game.GameScreen;
import com.skloch.game.HustleGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added version 1.1
 * This class contains unit tests for the game over logic
 * It verifies whether the game will end under the correct conditions
 */
@RunWith(org.example.GdxTestRunner.class)
public class GameOverTests {
    GameScreen gameScreen;
    HustleGame game;

    /**
     * Sets up an instance of the game and GameScreen before running tests
     */
    @Before
    public void setUp(){
        game = new HustleGame(1920, 1080);
        game.gameTest = true;
        gameScreen = new GameScreen(game, 1);
    }

    /**
     * Game ends on day 8
     */
    @Test
    public void gameEndsAfterSevenDays(){
        gameScreen.setDay(8);
        gameScreen.passTime(1);
        Assert.assertTrue(gameScreen.testGameOver);
    }
}
