package com.skloch.domain;
import com.skloch.game.GameScreen;
import com.skloch.game.HustleGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(org.example.GdxTestRunner.class)
public class GameOverTests {
    GameScreen gameScreen;
    HustleGame game;
    @Before
    public void setUp(){
        game = new HustleGame(1920, 1080);
        game.gameTest = true;
        gameScreen = new GameScreen(game, 1);
    }
    @Test
    public void gameEndsAfterSevenDays(){
        gameScreen.setDay(8);
        gameScreen.passTime(1);
        Assert.assertTrue(gameScreen.testGameOver);
    }
}
