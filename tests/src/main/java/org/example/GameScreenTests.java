package com.skloch.tests;
import com.skloch.game.GameScreen;
import com.skloch.game.HustleGame;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class GameScreenTests {

    @Test
    public void testGameValueInitialisation() {
        HustleGame hustleGame = new HustleGame(1280, 720);
        /*GameScreen gameScreen = new GameScreen(hustleGame, 1) {
            assertEquals(gameScreen.hoursStudied, 0);
            assertEquals(gameScreen.hoursSlept, 0);
            assertEquals(gameScreen.hoursRecreational, 0);
            assertEquals(gameScreen.energy, 100);
            assertEquals(gameScreen.daySeconds, 0);
        }*/
    }

}
