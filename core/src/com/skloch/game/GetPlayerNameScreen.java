package com.skloch.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GetPlayerNameScreen implements Screen {
    //this whole class is for displaying a screen to get the player's name
    //and then passing that to LeaderboardScreen

    private HustleGame game;
    float finalScore;
    String playerName = "";
    Stage getNameStage;
    Viewport viewport;
    OrthographicCamera camera;

    public GetPlayerNameScreen (final HustleGame game, float finalScore) {
        this.game = game;
        this.finalScore = finalScore;

        getNameStage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.WIDTH, game.HEIGHT, camera);
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

        // Create the window
        Window inputWindow = new Window("", game.skin);
        getNameStage.addActor(inputWindow);

        // Table for UI elements in window
        Table inputTable = new Table();
        inputWindow.add(inputTable);

        //Add text to table
        inputTable.add(new Label("Enter your name: ", game.skin,"interaction")).padBottom(5);
        inputTable.row();
        inputTable.add(new Label("Press ENTER to confirm.", game.skin,"interaction")).bottom().padBottom(5);
        inputTable.row();

        //Table for just the player's name
        Table nameTable = new Table();
        nameTable.add(new Label(" ",game.skin,"interaction")).padBottom(5);
        inputTable.add(nameTable).bottom().padTop(50);

        //Finalise window
        inputWindow.pack();
        inputWindow.setSize(600, 600);
        // Centre the window
        inputWindow.setX((viewport.getWorldWidth() / 2) - (inputWindow.getWidth() / 2));
        inputWindow.setY((viewport.getWorldHeight() / 2) - (inputWindow.getHeight() / 2));

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                //enter
                if (keycode == 66) {
                    //player has pressed enter
                    dispose();
                    game.setScreen(new LeaderboardScreen(game, playerName, finalScore));
                }

                //backspace
                if (keycode == 67 && playerName.length() > 0) {
                    playerName = playerName.substring(0, playerName.length() - 1);
                }

                //Only allow A-Z/0-9 input
                //7 - 16 = 0-9, 29 - 54 = A - Z
                //also enforce maximum player name length of 10
                if (((keycode >= 29 && keycode <= 54) || (keycode >= 7 && keycode <= 16)) && playerName.length() < 10) {
                    playerName += Input.Keys.toString(keycode);
                }

                nameTable.clear();
                nameTable.add(new Label(playerName, game.skin,"interaction")).padBottom(5);
                nameTable.row();

                return false;
            };
        });
    }

    /**
     * Renders the screen and the background each frame
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.blueBackground.draw();
        getNameStage.act(delta);
        getNameStage.draw();
        camera.update();
    }

    /**
     * Correctly resizes the onscreen elements when the window is resized
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        getNameStage.getViewport().update(width, height);
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