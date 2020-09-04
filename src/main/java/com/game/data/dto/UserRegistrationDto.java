package com.game.data.dto;

import com.game.core.validator.PasswordMatches;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatches
public class UserRegistrationDto {
    @NotNull
    @Pattern(regexp = "^[\\w]{4,}$",
            message = "Username should have at least 4 characters (include a-z, A-Z, 0-9 and _).")
    private String username;

    @NotNull
    @Pattern(regexp = "^\\S+$",
            message = "Password should have at least 6 characters (include all except white space).")
    private String password;

    @NotNull
    private String retypePassword;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]\\w+@([a-zA-Z]{2,}\\.)+[a-zA-Z]{2,}$",
            message = "Email is not valid.")
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]{2,}$",
            message = "Name is not valid.")
    private String name;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String username, String password, String retypePassword, String email, String name) {
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
        this.email = email;
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
