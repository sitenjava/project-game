package com.game.data.repository;

import com.game.data.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
    @Query("SELECT a FROM Action a WHERE a.method = :method")
    Action findActionByMethod(@Param("method") String method);

    @Query("SELECT a.method FROM Action a")
    List<String> getAllActionMethods();
}
