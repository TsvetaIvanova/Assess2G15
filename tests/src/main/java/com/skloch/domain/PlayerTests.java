package com.skloch.domain;

import com.skloch.game.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(org.example.GdxTestRunner.class)
public class PlayerTests {
    Player player;
    @Before
    public void setUp(){
        player = new Player("avatar1");
    }

    @Test
    public void testPlayerMoveRight(){
        player.testRight = true;
        float posX = player.getX();
        player.move(5f);
        assertTrue(player.getX() > posX);
    }

    @Test
    public void testPlayerMoveLeft(){
        player.testLeft = true;
        float posX = player.getX();
        player.move(5f);
        assertTrue(player.getX() < posX);
    }

    @Test
    public void testPlayerMoveUp(){
        player.testUp = true;
        float posY = player.getY();
        player.move(5f);
        assertTrue(player.getY() > posY);
    }

    @Test
    public void testPlayerMoveDown(){
        player.testDown = true;
        float posY = player.getY();
        player.move(5f);
        assertTrue(player.getY() < posY);
    }
}