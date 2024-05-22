package com.skloch.domain;
import com.skloch.game.HustleGame;
import com.skloch.game.MenuScreen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(org.example.GdxTestRunner.class)
public class IntroductionTests {

    @Before
    public void setUp(){

    }
    @Test
    public void correctTutorialString(){
        HustleGame game = new HustleGame(1920, 1080);
        game.gameTest = true;
        game.create();

        MenuScreen menu = new MenuScreen(game);

        String expectedText = "Welcome to Heslington! You are a student at the university of York and your first big exam is fast approaching, but you haven't studied yet at all!\n\nOver the next 7 days you will need to manage studying, eating, sleeping and relaxing.\n\nYou can study in the Computer Science building, eat in the Ron Cooke Hub, meet friends in the Piazza, and sleep in your accomodation building.\n\nGood luck with your exam!\n\nWASD or Arrow Keys to Move\nE to Interact\nEsc to Pause";

        //Remove line seperators from the tutorial text
        String tutorialText = menu.game.tutorialText.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
        Assert.assertEquals(expectedText, tutorialText);
    }
}
