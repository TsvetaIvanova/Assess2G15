package com.skloch.domain;

import com.skloch.game.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added version 1.1
 * This class contains unit tests for the Player class
 * It verifies whether the player is able to be controlled correctly by the user
 */
@RunWith(org.example.GdxTestRunner.class)
public class PlayerTests {
    Player player;
    @Before
    public void setUp(){
        player = new Player("avatar1");
    }

    /**
     * Tests if the player can move right
     */
    @Test
    public void testPlayerMoveRight(){
        player.testRight = true;
        float posX = player.getX();
        player.move(5f);
        assertTrue(player.getX() > posX);
    }

    /**
     * Tests if the player can move left
     */
    @Test
    public void testPlayerMoveLeft(){
        player.testLeft = true;
        float posX = player.getX();
        player.move(5f);
        assertTrue(player.getX() < posX);
    }

    /**
     * Tests if the player can move up
     */
    @Test
    public void testPlayerMoveUp(){
        player.testUp = true;
        float posY = player.getY();
        player.move(5f);
        assertTrue(player.getY() > posY);
    }

    /**
     * Tests if the player can move down
     */
    @Test
    public void testPlayerMoveDown(){
        player.testDown = true;
        float posY = player.getY();
        player.move(5f);
        assertTrue(player.getY() < posY);
    }
}