package com.game.data.dto;

public class RankDto extends BaseDto<RankDto>
{
    private Integer time;
    private Integer score;
    private UserDto user;
    private GameDto game;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }
}
