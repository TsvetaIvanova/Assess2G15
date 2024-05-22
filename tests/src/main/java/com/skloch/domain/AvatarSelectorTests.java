package com.skloch.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class added version 1.1
 * This class contains unit tests for the AvatarSelector helper class,
 * that contains the logic for the selection of avatars in the beginning of the game.
 * It verifies the correct selection of avatars and handling of invalid selections.
 */

public class AvatarSelectorTests {
    private AvatarSelector avatarSelector;

    /**
     * Sets up the test environment by initializing the AvatarSelector instance.
     */

    @Before
    public void setUp() {
        avatarSelector = new AvatarSelector();
    }

    /**
     * Tests selecting the first avatar.
     * Verifies that the selected avatar is correctly set to 1.
     */

    @Test
    public void testSelectAvatar1() {
        avatarSelector.selectAvatar(1);
        assertEquals(1, avatarSelector.getSelectedAvatar());
    }

    /**
     * Tests selecting the second avatar.
     * Verifies that the selected avatar is correctly set to 2.
     */

    @Test
    public void testSelectAvatar2() {
        avatarSelector.selectAvatar(2);
        assertEquals(2, avatarSelector.getSelectedAvatar());
    }

    /**
     * Tests selecting an invalid avatar.
     * Expects an IllegalArgumentException to be thrown.
     */

    @Test(expected = IllegalArgumentException.class)
    public void testSelectInvalidAvatar() {
        avatarSelector.selectAvatar(3);
    }
}
