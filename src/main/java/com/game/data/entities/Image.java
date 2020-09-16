package com.game.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "image_lat_hinh")
public class Image  extends BaseEntity
{
    @Column(name = "link_image")
    private String link;
    @Column(name = "active_play")
    private Boolean activePlay;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "map_value")
    private Integer value;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    public Image() {
    }

    public Image(String link, Boolean active, Boolean activePlay ,Integer value, Game game) {
        this.link = link;
        this.active = active;
        this.value = value;
        this.game = game;
        this.activePlay = activePlay;
    }

    public Boolean getActivePlay() {
        return activePlay;
    }

    public void setActivePlay(Boolean activePlay) {
        this.activePlay = activePlay;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
