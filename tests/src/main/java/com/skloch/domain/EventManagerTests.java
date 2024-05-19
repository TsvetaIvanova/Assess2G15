package com.skloch.domain;

import com.skloch.game.DialogueBox;
import com.skloch.game.EventManager;
import com.skloch.game.GameScreen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;



public class EventManagerTests {

    private GameScreen gameScreen;

    @Before
    public void setUp() {
        gameScreen = mock(GameScreen.class);
        gameScreen.dialogueBox = mock(DialogueBox.class);
    }

    @Test
    public void canChooseToCatchUpInStudies() {
        EventManager eventManager = new EventManager(gameScreen);
        int currentGameDay = 1; // Monday=1
        when(gameScreen.getDay()).thenReturn(currentGameDay);
        int hours = 2;
        int expectedEnergy = hours * eventManager.activityEnergies.get("studying");
        int expectedPassTime = hours * 60;
        eventManager.setHours(hours);
        gameScreen.daysStudied = new int[]{0,0,0,0,0,0,0};
        eventManager.event("catch_up-1");
        verify(gameScreen.dialogueBox).setText(String.format("You studied for %s hours!\nYou lost %d energy", hours, expectedEnergy));

        verify(gameScreen).decreaseEnergy(expectedEnergy);
        verify(gameScreen).addStudyHours(hours);
        assertEquals(hours, gameScreen.daysStudied[0]);
        assertTrue(gameScreen.catchUp);
        verify(gameScreen).passTime(expectedPassTime);

    }

    @Test
    public void canChooseNotToCatchUpInStudies() {
        EventManager eventManager = new EventManager(gameScreen);
        int currentGameDay = 1; // Monday=1
        when(gameScreen.getDay()).thenReturn(currentGameDay);
        int hours = 2;
        int expectedEnergy = hours * eventManager.activityEnergies.get("studying");
        int expectedPassTime = hours * 60;
        eventManager.setHours(hours);
        gameScreen.daysStudied = new int[]{0,0,0,0,0,0,0};
        eventManager.event("catch_up-2");
        verify(gameScreen.dialogueBox).setText(String.format("You studied for %s hours!\nYou lost %d energy", hours, expectedEnergy));

        verify(gameScreen).decreaseEnergy(expectedEnergy);
        verify(gameScreen).addStudyHours(hours);
        assertEquals(hours, gameScreen.daysStudied[0]);
        assertFalse(gameScreen.catchUp);
        verify(gameScreen).passTime(expectedPassTime);

    }


}
