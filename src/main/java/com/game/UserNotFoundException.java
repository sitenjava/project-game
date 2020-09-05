package com.game;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id) {
        super("User with id " + id + " is not exist!");
    }
}
