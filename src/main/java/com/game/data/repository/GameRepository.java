package com.game.data.repository;

import com.game.data.entities.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Integer>
{
    @Query(value = "select s from Game s where (:categoryId is null or s.category.id=:categoryId)" +
            "and (:active is null or s.active=:active)")
    List<Game> findAll(@Param("categoryId") Integer categoryId, @Param("active") Boolean active,
                       Pageable pageable);
}
