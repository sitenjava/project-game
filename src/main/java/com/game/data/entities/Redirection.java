package com.game.data.entities;

import javax.persistence.*;

@Entity
public class Redirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;

    public Redirection() {
    }

    public Redirection(Role role, Url url, Action action) {
        this.role = role;
        this.url = url;
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
