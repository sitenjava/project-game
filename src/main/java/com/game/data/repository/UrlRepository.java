package com.game.data.repository;

import com.game.data.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    @Query("SELECT u FROM Url u WHERE u.link = :link")
    Url findUrlByLink(@Param("link") String link);
}
