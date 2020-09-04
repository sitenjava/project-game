package com.game.data.repository;

import com.game.data.entities.Redirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedirectionRepository extends JpaRepository<Redirection, Integer> {
    @Query("SELECT u.link FROM Url u " +
            "JOIN Redirection re ON re.url.id = u.id " +
            "JOIN Action act ON act.id = re.action.id " +
            "JOIN Role r ON r.id = re.role.id " +
            "WHERE r.name = :name AND act.method = :method")
    List<String> getLinks(@Param("name") String name, @Param("method") String method);
}
