package com.skloch.domain;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.skloch.game.DialogueBox;
import com.skloch.game.EventManager;
import com.skloch.game.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added version 1.1
 * This class contains unit tests for the EventManager class.
 * It verifies the correct handling of various game locations and confirms that it
 * is possible to interact with them.
 */

public class EventManagerTests {

    private GameScreen gameScreen;

    /**
     * Sets up the test environment by initializing the GameScreen instance and its components.
     */

    @Before
    public void setUp() {
        gameScreen = mock(GameScreen.class);
        gameScreen.dialogueBox = mock(DialogueBox.class);
        gameScreen.blackScreen = mock(Image.class);
    }

    /**
     * Tests if the player can choose to catch up on studies.
     * Verifies the effects on energy, study hours, and game state.
     */

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

    /**
     * Tests if the player can choose not to catch up on studies.
     * Verifies the effects on energy, study hours, and game state.
     */

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

    /**
     * Tests if the player can interact with a tree.
     * Verifies the dialogue displayed.
     */

    @Test
    public void canInteractWithTree() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("tree");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("The tree doesn't say anything back.");
    }

    /**
     * Tests if the player can interact with a chest.
     * Verifies the dialogue displayed.
     */

    @Test
    public void canInteractWithChest() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("chest");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("Wow! This chest is full of so many magical items! I wonder how they will help you out on your journey! Boy, this is an awfully long piece of text, I wonder if someone is testing something?\n...\n...\n...\nHow cool!");
    }

    /**
     * Tests if the player can meet friends at Piazza and talk about cats.
     * Verifies the dialogue displayed and energy changes.
     */

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

    /**
     * Tests if the player can study at the CompSci building.
     * Verifies the effects on energy, study hours, and game state.
     */

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

    /**
     * Tests if the player can eat at the Ron Cooke Hub.
     * Verifies the effects on energy and game state and dialogueBox text.
     */

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

    /**
     * Tests if the player can go fishing.
     * Verifies the effects on energy, recreational hours, and game state and dialogueBox text.
     */

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

    /**
     * Tests if the player can feed ducks.
     * Verifies the effects on dialogueBox text, energy, recreational hours, and game state.
     */

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

    /**
     * Tests if the player can go to sleep.
     * Verifies the effects on game state.
     */

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

    /**
     * Tests if the player can interact with NPC1.
     * Verifies the dialogue displayed.
     */

    @Test
    public void canInteractWithNPC1() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("NPC1");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("Jerry: I dropped my phone in the lake...");
    }

    /**
     * Tests if the player can interact with NPC2.
     * Verifies the dialogue displayed.
     */

    @Test
    public void canInteractWithNPC2() {
        EventManager eventManager = new EventManager(gameScreen);
        eventManager.event("NPC2");
        verify(gameScreen.dialogueBox).hideSelectBox();
        verify(gameScreen.dialogueBox).setText("Tilly: I'm too tired for this.");
    }

    /**
     * Tests if piazza event returns a random time
     */
    @Test
    public void piazzaRandomEnergy() {
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getEnergy()).thenReturn(100);
        when(gameScreen.getSeconds()).thenReturn(600F);

        int i =0;
        boolean isEnergyRandom = false;
        ArrayList<Integer> hours = new ArrayList<Integer>() ;
        while ((i<10000 && isEnergyRandom==false)) {
            eventManager.piazzaEvent(new String[]{"a", "b", "c"});
            hours.add(eventManager.hours);
            if ((hours.size()>2) && (hours.get(hours.size()-1)!=hours.get(hours.size()-2))){
                isEnergyRandom=true;
            }
            i += 1;
        }
        assertTrue(isEnergyRandom);
    }

    /**
     * Tests if player is told they are too tired to do an activity if their energy is too low
     * Verifies displayed dialogue
     */
    @Test
    public void attemptActivityTooTired(){
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getEnergy()).thenReturn(0);
        when(gameScreen.getSeconds()).thenReturn((float) (9*60));
        eventManager.event("ducks");
        verify(gameScreen.dialogueBox).setText("You're too tired to feed the ducks right now!");
        eventManager.event("comp_sci");
        verify(gameScreen.dialogueBox).setText("You are too tired to study right now!");
        eventManager.event("piazza");
        verify(gameScreen.dialogueBox).setText("You are too tired to meet your friends right now!");
    }
    /**
     * Tests if player is told it's the wrong time to do an activity
     * Verifies the dialogue
     */
    @Test
    public void attemptActivityTooLate(){
        EventManager eventManager = new EventManager(gameScreen);
        when(gameScreen.getEnergy()).thenReturn(100);
        when(gameScreen.getSeconds()).thenReturn(0F);
        eventManager.event("piazza");
        verify(gameScreen.dialogueBox).setText("It's too early in the morning to meet your friends, go to bed!");

    }


}
