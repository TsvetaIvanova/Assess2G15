package com.skloch.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class created in version 1.1
 * A class that manages the popup messages related to streaks
 * The messages are defined in the GameScreenGlass.
 * They show for only a set amount of seconds. This class
 * creates the table that holds the messages.
 */

public class FeedbackMessageManager {
    private Label feedbackLabel;
    private Table messageTable;

    /**
     * The constructor initializes the FeedbackMessageManager with a given skin and stage.
     * It builds the table and label for displaying feedback messages.
     *
     * @param skin The Skin used for styling the label.
     * @param stage The Stage where the feedback messages will be displayed.
     */

    public FeedbackMessageManager(Skin skin, Stage stage) {
        // creating the table for the popup messages
        messageTable = new Table();
        messageTable.setFillParent(true);
        // creating the text label, the stylename font type can be viewed/edited in the assets/Interface BlockyInterface.json file
        feedbackLabel = new Label("", skin, "feedback");
        messageTable.add(feedbackLabel).uniformX().padTop(10);
        // the message is centered
        messageTable.top().center().padLeft(10).padTop(10);
        // adding the message table to the stage so that it gets displayed
        stage.addActor(messageTable);
    }

    /**
     * Manages the feedback message display duration using the Java Timer Class .
     * The message will be cleared after the duration has passed.
     *
     * @param message The feedback message to display.
     * @param duration The duration (in seconds) for which the message will be displayed.
     */

    public void flashMessage(String message, float duration) {
        // setting the text to the contents of the message(defined in GameScreen)
        feedbackLabel.setText(message);

        // Schedule a task to clear the message after the duration
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                // clearing the message
                feedbackLabel.setText("");
            }
        }, duration);
    }
}
