package com.game.service;

import com.game.data.dto.GameDto;

import java.util.List;

public interface IGameService
{
    // Get game by game_id
    GameDto add(GameDto gameDto);
    GameDto update(GameDto gameDto);
    GameDto findById(Integer id);
    // get list game by category_id
    List<GameDto> findAll(Integer integer , Boolean active ,String orderBy , String sortDir ,
                          Integer page , Integer limit);
}
