package com.skloch.domain;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameStateTests {
    private GameState gameState;
    private EventManagerLogic eventManager;

    @Before
    public void setUp() {
        gameState = new GameState();
        eventManager = new EventManagerLogic(gameState);
    }

    @Test
    public void testPiazzaEvent() {
        eventManager.event("piazza-2");

        assertEquals(80, gameState.getEnergy());
        assertEquals(2, gameState.getRecreationalHours());
    }

    @Test
    public void testCompSciEvent() {
        eventManager.event("comp_sci-2");

        assertEquals(80, gameState.getEnergy());
        assertEquals(2, gameState.getHoursStudied());
    }

    @Test
    public void testRonCookeEvent() {
        eventManager.event("rch");

        assertEquals(90, gameState.getEnergy());
        assertEquals(10, gameState.getEatingScore());
    }

    @Test
    public void testAccomEvent() {
        eventManager.event("accommodation");

        assertEquals(100, gameState.getEnergy());
    }

    @Test
    public void testDuckEvent() {
        eventManager.event("ducks");

        assertEquals(90, gameState.getEnergy());
        assertEquals(1, gameState.getRecreationalHours());
    }

    @Test
    public void testFishingEvent() {
        eventManager.event("fishing");

        assertEquals(90, gameState.getEnergy());
        assertEquals(1, gameState.getRecreationalHours());
    }
}
