package com.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User with id " + id + " is not exist");
    }

    public UserNotFoundException(String str, int type) {
        super(type == 1 ? "User with username '" + str + "' is not exist"
                : "Users with role '" + str + "' are not exist");
    }

    public UserNotFoundException(boolean active) {
        super("Users with status active " + active + " are not exist");
    }
}
