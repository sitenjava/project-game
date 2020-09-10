package com.game.common.Converters;

import com.game.data.dto.GameDto;
import com.game.data.entities.Game;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class GameConverter
{
    private static GameConverter converter = null;
    private ModelMapper modelMapper = new ModelMapper();

    public static GameConverter getInstance()
    {
        if (converter == null)
            converter = new GameConverter();
        return converter;
    }

    private GameConverter()
    {

    }
    public GameDto toDto(Game game)
    {
        GameDto gameDto = modelMapper.map(game,GameDto.class);
        return gameDto;
    }
    public Game toEntity(GameDto gameDto)
    {
        Game game = modelMapper.map(gameDto,Game.class);
        return game;
    }
    public List<GameDto> toDto(List<Game> games)
    {
        List<GameDto> list = new ArrayList<>();
        if (games.size()==0)
            return null;
        for (Game game : games) {
            list.add(toDto(game));
        }
        return list;
    }
    public List<Game> toEntity(List<GameDto> games)
    {
        List<Game> list = new ArrayList<>();
        if (games.size()==0)
            return null;
        for (GameDto game : games) {
            list.add(toEntity(game));
        }
        return list;
    }
}
