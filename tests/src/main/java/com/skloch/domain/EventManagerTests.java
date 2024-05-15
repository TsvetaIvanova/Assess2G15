package com.skloch.domain;

import com.skloch.game.DialogueBox;
import com.skloch.game.EventManager;
import com.skloch.game.GameScreen;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


public class EventManagerTests {

    private EventManager eventManager;
    private GameScreen game;

    @Before
    public void setUp() throws Exception {

        game = mock(GameScreen.class);
        DialogueBox dialogueBox = mock(DialogueBox.class);



        Field dialogueBoxField = GameScreen.class.getDeclaredField("dialogueBox");
        dialogueBoxField.setAccessible(true);
        dialogueBoxField.set(game, dialogueBox);

        eventManager = new EventManager(game);


        dialogueBoxField.setAccessible(true);
        dialogueBoxField.set(game, dialogueBox);

        Field blackScreenField = GameScreen.class.getDeclaredField("blackScreen");
        blackScreenField.setAccessible(true);
        Object blackScreen = null;
        blackScreenField.set(game, blackScreen);


        Field daysStudiedField = GameScreen.class.getDeclaredField("daysStudied");
        daysStudiedField.setAccessible(true);
        daysStudiedField.set(game, new int[]{0, 0, 0, 0, 0, 0, 0});

        Field catchUpField = GameScreen.class.getDeclaredField("catchUp");
        catchUpField.setAccessible(true);
        catchUpField.set(game, false);

        eventManager = new EventManager(game);
        eventManager.setTesting(true);


        when(game.getEnergy()).thenReturn(100);
        when(game.getSeconds()).thenReturn(54000F);
        when(game.getDay()).thenReturn(1);
        when(game.getMeal()).thenReturn("lunch");


        doNothing().when(dialogueBox).show();
        doNothing().when(dialogueBox).hide();
        doNothing().when(dialogueBox).setText(anyString());
        doNothing().when(dialogueBox).hideSelectBox();


    }

    @Test
    public void testStudyAtCompSci() {
        String[] args = {"comp_sci", "3"};
        eventManager.compSciEvent(args);


        verify(game).decreaseEnergy(30);
        verify(game).passTime(3 * 60);
        verify(game).addStudyHours(3);
        assertEquals(3, game.daysStudied[0]);
    }

    @Test
    public void testMeetFriendsAtPiazza() {
        String[] args = {"piazza", "video_games"};
        eventManager.piazzaEvent(args);


        verify(game).decreaseEnergy(anyInt());
        verify(game).passTime(anyInt());
        verify(game).addRecreationalHours(anyInt());
    }

    @Test
    public void testEatAtRonCookeHub() {
        String[] args = {"ron_cooke"};
        eventManager.ronCookeEvent(args);


        verify(game).decreaseEnergy(10);
        verify(game).passTime(60);
    }

    @Test
    public void testSleepAtAccommodation() {
        String[] args = {"accommodation"};
        eventManager.accomEvent(args);


        //verify(game).setEnergy(8 * 13);
        //verify(game).passTime(8 * 60);
    }

    @Test
    public void testFeedDucks() {
        eventManager.duckEvent();


        verify(game).decreaseEnergy(10);
        verify(game).passTime(30);
        verify(game).addRecreationalHours(1);
    }

    @Test
    public void testFishing() {
        eventManager.fishingEvent();


        verify(game).decreaseEnergy(10);
        verify(game).passTime(60);
        verify(game).addRecreationalHours(1);
    }
}
