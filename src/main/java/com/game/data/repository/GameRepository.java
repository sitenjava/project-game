package com.game.data.repository;

import com.game.data.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface GameRepository extends JpaRepository<Game,Integer>
{
    List<Game> findByCategoryId(Integer categoryId);
    List<Game> findByActive(Boolean active);
    List<Game> findByCategoryIdAndActive(Integer categoryId , Boolean active);
}
