package com.game.common.Converters;

import com.game.data.dto.GameDto;
import com.game.data.entities.Game;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameConverter
{
    private static GameConverter converter = null;
    private ModelMapper modelMapper = new ModelMapper();

    public static GameConverter getInstance()
    {
        if (converter == null)
            return new GameConverter();
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
    public List<GameDto> toDto(List<Game> games)
    {
        List<GameDto> list = new ArrayList<>();
        for (Game game : games) {
            list.add(toDto(game));
        }
        return list;
    }
}
