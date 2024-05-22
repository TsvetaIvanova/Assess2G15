package com.skloch.domain;

import com.skloch.game.HustleGame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added version 1.1
 * This class contains unit tests for the HustleGame class.
 *Checks the right default resolution is set
 */
public class HustleGameTests {
    /**
     * Checks HustleGame can be made without exceptions
     */
    @Test
    public void initialises() {
        HustleGame testGame = new HustleGame();
    }
    /**
     *Checks resolution is correct
     */
    @Test
    public void correctResolution(){
        HustleGame game = new HustleGame();
        assertTrue(game.WIDTH ==1920 && game.HEIGHT==1080);
    }

}
