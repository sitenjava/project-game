package com.game.data.dto;

import com.game.data.entities.Image;

public class ImageDto extends BaseDto<ImageDto>
{
    private String link;
    private Boolean activePlay;
    private Boolean active;
    private Integer value;
    private GameDto game;

    public static ImageDto from(String link)
    {
        ImageDto imageDto = new ImageDto();
        imageDto.setLink(link);
        return imageDto;
    }
    public ImageDto id(Integer id)
    {
        this.id = id;
        return this;
    }
    public ImageDto value(Integer value)
    {
        this.value = value;
        return this;
    }

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
