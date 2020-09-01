package com.game.service;

import com.game.data.entities.Game;

import java.util.List;
import java.util.Set;

public interface IGameService
{
    // Get game by game_id
    Game findById(Integer id);
    // get list game by category_id
    List<Game> findAllByCategoryId(Integer categoryId , Boolean active);
}
