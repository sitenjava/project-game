package com.game.service.impl;

import com.game.data.entities.Game;
import com.game.data.repository.GameRepository;
import com.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GameService implements IGameService
{
    @Autowired
    private GameRepository gameRepository;
    @Override
    public Game findById(Integer id) {
        return null;
    }

    @Override
    public List<Game> findAllByCategoryId(Integer categoryId , Boolean active) {
        if (categoryId == null && active == null)
            return gameRepository.findAll();
        if (categoryId == null && active != null)
            return gameRepository.findByActive(active);
        if (categoryId != null && active == null)
            return gameRepository.findByCategoryId(categoryId);
        return gameRepository.findByCategoryIdAndActive(categoryId,active);
    }
}
