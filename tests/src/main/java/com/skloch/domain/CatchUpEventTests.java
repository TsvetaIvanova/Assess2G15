package com.skloch.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatchUpEventTests {
    private StudyManager studyManager;

    @Before
    public void setUp() {

        studyManager = new StudyManager();
    }

    @Test
    public void testCatchUpEvent_Option1() {

        studyManager.setEnergy(100);


        int day = 3;
        String option = "1";
        int hours = 3;


        studyManager.catchUpEvent(day, hours, option);

        // Verify outcomes
        assertEquals(70, studyManager.getEnergy());
        assertEquals(3, studyManager.getStudyHours());
        assertEquals(2, studyManager.getDaysStudied()[2]);
        assertTrue(studyManager.isCatchUp());
    }

    @Test
    public void testCatchUpEvent_Option2() {

        studyManager.setEnergy(100);


        int day = 3;
        String option = "2";
        int hours = 3;


        studyManager.catchUpEvent(day, hours, option);


        assertEquals(70, studyManager.getEnergy());
        assertEquals(3, studyManager.getStudyHours());
        assertEquals(3, studyManager.getDaysStudied()[2]);
        assertFalse(studyManager.isCatchUp());
    }
}
