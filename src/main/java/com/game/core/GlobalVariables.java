package com.game.core;

import com.game.data.entities.User;

public class GlobalVariables {
    private static GlobalVariables instance;
    private User currentUser;

    private GlobalVariables() {}

    public static GlobalVariables getInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
