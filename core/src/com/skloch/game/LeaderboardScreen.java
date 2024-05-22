package com.skloch.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added in version 1.1
 * This class defines the screen which shows the leaderboard,
 * using the Leaderboard class which actually handles the .csv file
 */
public class LeaderboardScreen implements Screen {

    private HustleGame game;
    Stage leaderboardStage;
    Viewport viewport;
    OrthographicCamera camera;

    /**
     * Constructor - creates the LeaderboardScreen and takes over the window and input controller
     * @param game the current game instance
     * @param playerName the player's name
     * @param finalScore the player's final score
     */
    public LeaderboardScreen (final HustleGame game, String playerName, float finalScore) {
        this.game = game;
        leaderboardStage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));
        Gdx.input.setInputProcessor(leaderboardStage);

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.WIDTH, game.HEIGHT, camera);
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

        // Create the window
        Window leaderboardWindow = new Window("", game.skin);
        leaderboardStage.addActor(leaderboardWindow);

        // Table for UI elements in window
        Table leaderboardTable = new Table();
        leaderboardWindow.add(leaderboardTable);

        //////////////////////// ACTUAL LEADERBOARD LOGIC /////////////////////

        //create an instance of the leaderboard
        Leaderboard leaderboard = new Leaderboard();

        //update the leaderboard with the player's name and score if it fits into the top 10
        leaderboard.updateLeaderboard(playerName, finalScore);

        //add all leaderboard entries to a table for display
        ArrayList<Leaderboard.LeaderboardEntry> entries = leaderboard.parseLeaderboard();
        for (Leaderboard.LeaderboardEntry entry : entries) {
            leaderboardTable.add(new Label(entry.name, game.skin,"interaction")).padBottom(5);
            leaderboardTable.add(new Label(Float.toString(entry.score), game.skin,"interaction")).padBottom(5);
            leaderboardTable.row();
        }

        // Exit button
        TextButton exitButton = new TextButton("Main Menu", game.skin);
        leaderboardTable.add(exitButton).bottom().width(300).padTop(10);

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.soundManager.playButton();
                game.soundManager.overworldMusic.stop();
                dispose();
                game.setScreen(new MenuScreen(game));
            }
        });


        leaderboardWindow.pack();
        leaderboardWindow.setSize(600, 600);
        // Centre the window
        leaderboardWindow.setX((viewport.getWorldWidth() / 2) - (leaderboardWindow.getWidth() / 2));
        leaderboardWindow.setY((viewport.getWorldHeight() / 2) - (leaderboardWindow.getHeight() / 2));

    }

    /**
     * Renders the screen and the background each frame
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.blueBackground.draw();

        leaderboardStage.act(delta);
        leaderboardStage.draw();

        camera.update();

    }

    /**
     * Correctly resizes the onscreen elements when the window is resized
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        leaderboardStage.getViewport().update(width, height);
        viewport.update(width, height);
    }

    // Other required methods from Screen
    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
