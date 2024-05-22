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
import com.skloch.domain.GameOverHelper;

/**
 //EXTENDED//-TEAM15-IMPLEMENTATION: class extended in version 1.1
 * A screen to display a 'Game Over' screen when the player finishes their exams
 * Calculates a  final score, shows the player's stats to them, as requested in assessment 1
 * Displays 2 tables, the scores table and the achievements/streaks table
 */
public class GameOverScreen implements Screen {

    private HustleGame game;
    Stage gameOverStage;
    Viewport viewport;
    OrthographicCamera camera;
    int bonusStreaks = 0, studyScoreLost = 0;

    /**
     * //EXTENDED//-TEAM15-IMPLEMENTATION: constructor extended in version 1.1
     * Creating a stage to display a 'Game Over' screen when the player finishes their exams
     * Calculates a  final score, shows the player's stats to them, as requested in assessment 1
     * Displays 2 tables, the scores table and the achievements/streaks table
     *
     * @param game              An instance of HustleGame
     * @param hoursStudied      The hours studied in the playthrough
     * @param gameOverHelper    an instance of a helper class abstracting the logic from ui for the GameOver
     */
    public GameOverScreen(final HustleGame game, int hoursStudied, GameOverHelper gameOverHelper) {
        this.game = game;
        bonusStreaks = gameOverHelper.calculateBonusStreak();
        gameOverStage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));
        Gdx.input.setInputProcessor(gameOverStage);
        camera = new OrthographicCamera();
        viewport = new FitViewport(game.WIDTH, game.HEIGHT, camera);
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

        // Create the window
        Window gameOverWindow = new Window("", game.skin);
        gameOverStage.addActor(gameOverWindow);

        // Create the window
        Window achievementsWindow = new Window("", game.skin);
        achievementsWindow.setSize(330, 660);
        gameOverStage.addActor(achievementsWindow);

        // Table for UI elements in window
        Table gameOverTable = new Table();
        gameOverWindow.add(gameOverTable);

        // Table for UI elements in window
        Table achievementsTable = new Table();
        achievementsWindow.add(achievementsTable).prefHeight(600).prefWidth(300).fill().expand();
        achievementsTable.setFillParent(true);
        achievementsTable.setVisible(true);

        // Title
        Label title = new Label("Game Over!", game.skin, "button");
        gameOverTable.add(title).padTop(30);
        gameOverTable.row();

        // Achievements table title set up
        Label.LabelStyle style = new Label.LabelStyle(game.skin.get("button", Label.LabelStyle.class));
        Label aTitle = new Label("Achievements!", style);
        title.setFontScale(0.79f);
        achievementsTable.add(aTitle).padTop(35).padLeft(-10).padRight(10).center();
        achievementsTable.row();
        achievementsTable.top();

        // Achievements scores table setup
        Table achievementsScoresTable = new Table();
        achievementsTable.add(achievementsScoresTable).prefHeight(300).prefWidth(300).expand().fill();
        achievementsTable.row();

        // Set font scale for achievement descriptions
        Label.LabelStyle descriptionStyle = new Label.LabelStyle(game.skin.get("button", Label.LabelStyle.class));
        descriptionStyle.font.getData().setScale(0.65f);

        // Track if any achievements have been added
        boolean anyAchievements = false;

        // Populate the table with achievements using GameOverHelper methods
        if (gameOverHelper.isBestFisher()) {
            anyAchievements = true;
            Label bestFisherLabel = new Label("Best Fisher +5 bonus", descriptionStyle);
            bestFisherLabel.setWrap(true);
            achievementsScoresTable.add(bestFisherLabel).width(240).padTop(25).padBottom(75).padLeft(50).padRight(-50);
            achievementsScoresTable.row();
        }
        if (gameOverHelper.isBookWorm()) {
            anyAchievements = true;
            Label bookwormLabel = new Label("Bookworm +5 bonus", descriptionStyle);
            bookwormLabel.setWrap(true);
            achievementsScoresTable.add(bookwormLabel).width(240).padTop(25).padBottom(75).padLeft(50).padRight(-50);
            achievementsScoresTable.row();
        }
        if (gameOverHelper.isDuckDuckGo()) {
            anyAchievements = true;
            Label duckDuckGoLabel = new Label("Duck duck go +5 bonus", descriptionStyle);
            duckDuckGoLabel.setWrap(true);
            achievementsScoresTable.add(duckDuckGoLabel).width(245).padTop(25).padBottom(75).padLeft(50).padRight(-50);
            achievementsScoresTable.row();
        }

        if (!anyAchievements) {
            Label noAchievLabel = new Label("No streaks this game:(", descriptionStyle);
            noAchievLabel.setWrap(true);
            achievementsScoresTable.add(noAchievLabel).width(245).height(300).padTop(45).padBottom(225).padLeft(50).padRight(-50);
            achievementsScoresTable.row();
        }

        // Always display the bonus streaks at the end if there are any
        if (bonusStreaks > 0) {
            Label bonusStreaksLabel = new Label("Streak Bonus: " + bonusStreaks, descriptionStyle);
            bonusStreaksLabel.setWrap(true);
            achievementsScoresTable.add(bonusStreaksLabel).width(240).padTop(20).padBottom(40).padLeft(50).padRight(-50);
            achievementsScoresTable.row();
        }

        // Pack to apply sizes and positions
        achievementsTable.pack();
        achievementsScoresTable.pack();

        // creating the scores table
        Table scoresTable = new Table();
        gameOverTable.add(scoresTable).prefHeight(380).prefWidth(450);
        gameOverTable.row();

        // defining what are reasonable study patterns
        String studyMessage;
        if (hoursStudied >= 8 && hoursStudied <= 10) {
            hoursStudied *= 2;
            studyMessage = "You studied enough!" + hoursStudied;
        } else if (hoursStudied > 10) {
            hoursStudied /= 2;
            studyMessage = "You overworked and were not very productive " + hoursStudied;
        } else {
            studyMessage = "You did not study enough!";
        }
        for (int i = 0; i < game.gameScreen.daysStudied.length; i++) {
            if (game.gameScreen.daysStudied[i] == 0 && hoursStudied > 0) {
                hoursStudied -= 1;
                studyScoreLost += 1;
            }
        }

        // Calculating the overall score
        float finalScore = (float) (ScoreManager.getTotalRecreationScore() + (float) (ScoreManager.getTotalEatScore()) + hoursStudied);

        // Display scores
        scoresTable.add(new Label(studyMessage, game.skin, "interaction")).padBottom(5);
        scoresTable.row();
        if (studyScoreLost != 0) {
            scoresTable.add(new Label(String.valueOf(hoursStudied) + " (Missed Days: -" + studyScoreLost + ")", game.skin, "button")).padBottom(10);
        } else {
            scoresTable.add(new Label(String.valueOf(hoursStudied), game.skin, "button")).padBottom(10);
        }
        scoresTable.row();
        scoresTable.add(new Label("Recreational Score", game.skin, "interaction")).padBottom(5);
        scoresTable.row();
        if(ScoreManager.getRecScoreTracker() != 0){
            scoresTable.add(new Label(String.valueOf(ScoreManager.getTotalRecreationScore()) + " (Bonus Points:" + ScoreManager.getRecScoreTracker() + ")", game.skin, "button")).padBottom(10);
        }else{
            scoresTable.add(new Label(String.valueOf(ScoreManager.getTotalRecreationScore()), game.skin, "button")).padBottom(10);
        }
        scoresTable.row();
        scoresTable.add(new Label("Eating Score", game.skin, "interaction")).padBottom(5);
        scoresTable.row();
        if(ScoreManager.getEatScoreTracker() != 0){
            scoresTable.add(new Label(String.valueOf(ScoreManager.getTotalEatScore()) + " (Bonus Points:" + ScoreManager.getEatScoreTracker() + ")", game.skin, "button")).padBottom(10);
        }else{
            scoresTable.add(new Label(String.valueOf(ScoreManager.getTotalEatScore()), game.skin, "button")).padBottom(10);
        }
        scoresTable.row();
        scoresTable.add(new Label("Final Score", game.skin, "interaction")).padBottom(5);
        scoresTable.row();
        scoresTable.add(new Label(finalScore + "/100", game.skin, "button")).padBottom(10);

        // Exit button
        TextButton exitButton = new TextButton("Main Menu", game.skin);
        gameOverTable.add(exitButton).width(300).padTop(10);
        gameOverTable.row();

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.soundManager.playButton();
                game.soundManager.overworldMusic.stop();
                dispose();
                game.setScreen(new MenuScreen(game));
            }
        });

        // Leaderboard button
        TextButton leaderboardButton = new TextButton("Leaderboard", game.skin);
        gameOverTable.add(leaderboardButton).bottom().width(300).padTop(10);

        leaderboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.soundManager.playButton();
                game.soundManager.overworldMusic.stop();
                dispose();
                game.setScreen(new GetPlayerNameScreen(game, finalScore));
            }
        });

        gameOverWindow.pack();
        gameOverWindow.setSize(600, 600);

        // Centre the window
        gameOverWindow.setX((viewport.getWorldWidth() / 2) - (gameOverWindow.getWidth() / 2));
        gameOverWindow.setY((viewport.getWorldHeight() / 2) - (gameOverWindow.getHeight() / 2));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.blueBackground.draw();

        gameOverStage.act(delta);
        gameOverStage.draw();

        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        gameOverStage.getViewport().update(width, height);
        viewport.update(width, height);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameOverStage);
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
        gameOverStage.dispose();
    }


}