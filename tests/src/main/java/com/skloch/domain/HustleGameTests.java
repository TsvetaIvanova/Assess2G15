package com.skloch.domain;

import com.skloch.game.HustleGame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class HustleGameTests {

    @Test
    public void initialises() {
        HustleGame testGame = new HustleGame();
    }
    @Test
    public void correctResolution(){
        HustleGame game = new HustleGame();
        assertTrue(game.WIDTH ==1920 && game.HEIGHT==1080);
    }

}
