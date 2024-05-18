package com.skloch.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;

public class FeedbackMessageManager {
    private Label feedbackLabel;
    private Table messageTable;

    public FeedbackMessageManager(Skin skin, Stage stage) {
        messageTable = new Table();
        messageTable.setFillParent(true);

        feedbackLabel = new Label("", skin, "feedback");
        messageTable.add(feedbackLabel).uniformX().padTop(10);
        messageTable.top().left().padLeft(10).padTop(10);

        stage.addActor(messageTable);
    }

    public void flashMessage(String message, float duration) {
        feedbackLabel.setText(message);

        // Schedule a task to clear the message after the duration
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                feedbackLabel.setText("");
            }
        }, duration);
    }
}
