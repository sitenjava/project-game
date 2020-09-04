package com.game.data.dto;

public class ImageDto extends BaseDto<ImageDto>
{
    private String link;
    private Boolean activePlay;
    private Boolean active;
    private Integer value;
    private GameDto game;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getActivePlay() {
        return activePlay;
    }

    public void setActivePlay(Boolean activePlay) {
        this.activePlay = activePlay;
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

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }
}
