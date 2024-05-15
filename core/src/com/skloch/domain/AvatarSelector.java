package com.skloch.domain;

public class AvatarSelector {
    private int selectedAvatar;

    public int getSelectedAvatar() {
        return selectedAvatar;
    }

    public void selectAvatar(int avatarChoice) {
        if (avatarChoice == 1 || avatarChoice == 2) {
            selectedAvatar = avatarChoice;
        } else {
            throw new IllegalArgumentException("Invalid avatar choice. Only 1 or 2 are valid choices.");
        }
    }
}
