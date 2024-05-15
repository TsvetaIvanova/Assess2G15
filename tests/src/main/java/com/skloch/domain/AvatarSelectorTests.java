package com.skloch.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvatarSelectorTests {
    private AvatarSelector avatarSelector;

    @Before
    public void setUp() {
        avatarSelector = new AvatarSelector();
    }

    @Test
    public void testSelectAvatar1() {
        avatarSelector.selectAvatar(1);
        assertEquals(1, avatarSelector.getSelectedAvatar());
    }

    @Test
    public void testSelectAvatar2() {
        avatarSelector.selectAvatar(2);
        assertEquals(2, avatarSelector.getSelectedAvatar());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectInvalidAvatar() {
        avatarSelector.selectAvatar(3);
    }
}
