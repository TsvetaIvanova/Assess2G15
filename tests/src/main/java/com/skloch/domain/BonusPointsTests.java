package com.skloch.domain;
import com.skloch.game.ScoreManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(org.example.GdxTestRunner.class)
public class BonusPointsTests {
    @Test
    public void eatingGivesBonusScore(){
        //Checks 3 breakfast
        ScoreManager.dayEatScore[0] += 3;
        ScoreManager.updateEatScore();
        double badScore = ScoreManager.getTotalEatScore();
        ScoreManager.setTotalEatScore(0);
        ScoreManager.dayEatScore[0] += 1;
        ScoreManager.dayEatScore[1] += 1;
        ScoreManager.dayEatScore[2] += 1;
        ScoreManager.updateEatScore();
        double goodScore = ScoreManager.getTotalEatScore();
        Assert.assertTrue(badScore < goodScore);

        //Checks 2 breakfast, 1 lunch
        ScoreManager.setTotalEatScore(0);
        ScoreManager.dayEatScore[0] += 2;
        ScoreManager.dayEatScore[1] += 1;
        ScoreManager.updateEatScore();
        badScore = ScoreManager.getTotalEatScore();
        Assert.assertTrue(badScore < goodScore);
    }

    @Test
    public void recGivesBonusScore(){
        //Checks 3 of same recreation
        ScoreManager.dayRecreationScore[0] += 3;
        ScoreManager.updateRecreationScore();
        double badScore = ScoreManager.getTotalRecreationScore();
        ScoreManager.setTotalRecreationScore(0);
        ScoreManager.dayRecreationScore[0] += 1;
        ScoreManager.dayRecreationScore[1] += 1;
        ScoreManager.dayRecreationScore[2] += 1;
        ScoreManager.updateRecreationScore();
        double goodScore = ScoreManager.getTotalRecreationScore();
        Assert.assertTrue(badScore < goodScore);


        //Checks 2 of a recreation, 1 of another
        ScoreManager.setTotalRecreationScore(0);
        ScoreManager.dayRecreationScore[0] += 2;
        ScoreManager.dayRecreationScore[1] += 1;
        ScoreManager.updateRecreationScore();
        badScore = ScoreManager.getTotalRecreationScore();
        Assert.assertTrue(badScore < goodScore);
    }
}
