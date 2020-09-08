package com.game.data.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "action", uniqueConstraints = @UniqueConstraint(columnNames = "method"))
public class Action{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "method")
    private String method;

    @OneToMany(mappedBy = "action")
    private Set<Redirection> redirections;

    public Action() {}

    public Action(String method) {
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
