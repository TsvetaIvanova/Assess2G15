package com.skloch.domain;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.skloch.game.DialogueBox;
import com.skloch.game.EventManager;
import com.skloch.game.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

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
        gameScreen.blackScreen = mock(Image.class);
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

    @Test
    public void canInteractWithTree() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("tree");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("The tree doesn't say anything back.");
    }


    @Test
    public void canInteractWithChest() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("chest");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("Wow! This chest is full of so many magical items! I wonder how they will help you out on your journey! Boy, this is an awfully long piece of text, I wonder if someone is testing something?\n...\n...\n...\nHow cool!");
    }

    @Test
    public void canMeetFriendsAtPiazzaAndTalkAboutCats() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getSeconds()).thenReturn((float) (9*60));
        when(gameScreen.getEnergy()).thenReturn(50);

        eventManager.event("piazza-Cats");
        ArgumentCaptor<String> dialogueTextCaptor= ArgumentCaptor.forClass(String.class);
        verify(gameScreen.dialogueBox).setText(dialogueTextCaptor.capture());
        assertTrue(dialogueTextCaptor.getValue().contains("You talked about cats"));

    }

    @Test
    public void canStudyAtCompSciBuilding() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getSeconds()).thenReturn((float) (9*60));
        when(gameScreen.getEnergy()).thenReturn(40);
        when(gameScreen.getDay()).thenReturn(1);

        gameScreen.daysStudied = new int[]{0, 0, 0, 0, 0, 0, 0};

        eventManager.event("comp_sci-2");

        verify(gameScreen.dialogueBox).setText("You studied for 2 hours!\nYou lost 20 energy");
        verify(gameScreen).decreaseEnergy(20);
        verify(gameScreen).addStudyHours(2);
        assertEquals(2, gameScreen.daysStudied[0]);
        verify(gameScreen).passTime(2 * 60);
    }

    @Test
    public void canEatAtRonCookeHub() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getSeconds()).thenReturn((float) (9*60));
        when(gameScreen.getEnergy()).thenReturn(30);
        when(gameScreen.getMeal()).thenReturn("breakfast");

        eventManager.event("rch");

        verify(gameScreen.dialogueBox).setText("You took an hour to eat breakfast at the Ron Cooke Hub!\nYou lost 10 energy!");
        verify(gameScreen).decreaseEnergy(10);
        verify(gameScreen).passTime(60);
    }

    @Test
    public void canGoFishing() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getEnergy()).thenReturn(30);

        eventManager.event("fishing");

        verify(gameScreen.dialogueBox).setText("You caught a fish!");
        verify(gameScreen).decreaseEnergy(10);
        verify(gameScreen).passTime(60);
        verify(gameScreen).addRecreationalHours(1);
        assertEquals(1, GameScreen.fishCaught);
    }


    @Test
    public void canFeedDucks() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getEnergy()).thenReturn(30);

        eventManager.event("ducks");

        verify(gameScreen.dialogueBox).setText("You fed the ducks!");
        verify(gameScreen).decreaseEnergy(10);
        verify(gameScreen).passTime(30);
        verify(gameScreen).addRecreationalHours(1);
        assertEquals(1, GameScreen.duckFeeds);
    }

    @Test
    public void canGoToSleep() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getSeconds()).thenReturn((float) (7*60));
        when(gameScreen.getSleeping()).thenReturn(true);
        when(gameScreen.getWakeUpMessage()).thenReturn("Good morning!");

        eventManager.event("accommodation");

        verify(gameScreen).setSleeping(true);
        verify(gameScreen.dialogueBox).hide();

    }

    @Test
    public void canInteractWithNPC1() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("NPC1");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("Jerry: I dropped my phone in the lake...");
    }

    @Test
    public void canInteractWithNPC2() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("NPC2");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("Tilly: I'm too tired for this.");
    }

}
