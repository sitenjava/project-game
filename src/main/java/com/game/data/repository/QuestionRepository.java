package com.game.data.repository;

import com.game.data.entities.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Query(value = "select q from Question q where q.game.id =:gameId and q.active=:active")
    List<Question> findAllByGameIdAndActive(@Param("gameId") Integer gameId,
                                    @Param("active") Boolean active);
    @Query(value = "select q from Question q where q.game.id =:gameId")
    List<Question> findAllByGameId(@Param("gameId") Integer gameId, Pageable pageable);

}
