package com.game.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends BaseEntity
{
    @Column( name = "username")
    private String username;
    @Column( name = "password")
    private String password;
    @Column( name = "email")
    private String email;
    @Column( name = "name")
    private String name;
    @Column(name ="active")
    private Boolean active;

    public User() {}

    public User(String username, String password, String email, String name, boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
