package com.skloch.domain;

import com.skloch.game.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(org.example.GdxTestRunner.class)
public class PlayerTests {
    @Test
    public void testPlayerMoveRight(){
        Player player = new Player("avatar1");
        player.testRight = true;
        float posX = player.getX();
        player.move(5f);
        assertTrue(player.getX() > posX);
    }

    @Test
    public void testPlayerMoveLeft(){
        Player player = new Player("avatar1");
        player.testLeft = true;
        float posX = player.getX();
        player.move(5f);
        assertTrue(player.getX() < posX);
    }

    @Test
    public void testPlayerMoveUp(){
        Player player = new Player("avatar1");
        player.testUp = true;
        float posY = player.getY();
        player.move(5f);
        assertTrue(player.getY() > posY);
    }

    @Test
    public void testPlayerMoveDown(){
        Player player = new Player("avatar1");
        player.testDown = true;
        float posY = player.getY();
        player.move(5f);
        assertTrue(player.getY() < posY);
    }
}