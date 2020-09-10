package com.game.data.repository;

import com.game.data.entities.Game;
import com.game.data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GameRepository extends JpaRepository<Game,Integer>
{
    @Query(value = "select s from Game s where (:categoryId is null or s.category.id=:categoryId)" +
            "and (:active is null or s.active=:active)")
    List<Game> findAll(@Param("categoryId") Integer categoryId, @Param("active") Boolean active,
                       Pageable pageable);
    @Query(value = "select g.users from Game g where g.id = :gameId")
    Set<User> findUsersByGameId(Integer gameId);
}
