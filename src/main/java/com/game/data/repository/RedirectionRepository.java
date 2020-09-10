package com.game.data.repository;

import com.game.data.entities.Redirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedirectionRepository extends JpaRepository<Redirection, Integer> {

    @Query("SELECT u FROM Redirection u ")
    List<Redirection> getDirections();
}
