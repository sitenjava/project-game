package com.game.common.converters;

import com.game.data.dto.GameDto;
import com.game.data.entities.Game;
import com.game.data.entities.User;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        return modelMapper.map(game,GameDto.class);
    }
    public Game toEntity(GameDto gameDto)
    {
        return modelMapper.map(gameDto,Game.class);
    }
    public List<GameDto> toDto(List<Game> games)
    {
        List<GameDto> list = new ArrayList<>();
        if (games == null || games.isEmpty())
            return null;
        for (Game game : games) {
            list.add(toDto(game));
        }
        return list;
    }
    public List<Game> toEntity(List<GameDto> games)
    {
        List<Game> list = new ArrayList<>();
        if (games == null || games.isEmpty())
            return null;
        for (GameDto game : games) {
            list.add(toEntity(game));
        }
        return list;
    }
}
