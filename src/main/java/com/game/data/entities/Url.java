package com.game.data.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "url", uniqueConstraints = @UniqueConstraint(columnNames = "link"))
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "link")
    private String link;

    @OneToMany(mappedBy = "url")
    private Set<Redirection> redirections;

    public Url() {
    }

    public Url(String link) {
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
