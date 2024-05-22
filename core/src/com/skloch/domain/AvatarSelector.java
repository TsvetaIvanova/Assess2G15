package com.skloch.domain;

/**
 * //NEW//-TEAM15-IMPLEMENTATION: class extended in version 1.1
 * This class abstracts the logic for selection of an avatar in the game.
 * It ensures that only valid avatars are selected.
 */

public class AvatarSelector {
    private int selectedAvatar;

    public int getSelectedAvatar() {
        return selectedAvatar;
    }
    /**
     * This class abstracts the logic for selection of an avatar in the game.
     * It ensures that only valid avatars are selected.
     * @param avatarChoice "avatar1" for the more masculine character, "avatar2" for the more feminine character,
     *                     player animations are packed in the player_sprites atlas
     * any other selection would throw an exception
     */
    public void selectAvatar(int avatarChoice) {
        if (avatarChoice == 1 || avatarChoice == 2) {
            selectedAvatar = avatarChoice;
        } else {
            throw new IllegalArgumentException("Invalid avatar choice. Only 1 or 2 are valid choices.");
        }
    }
}
