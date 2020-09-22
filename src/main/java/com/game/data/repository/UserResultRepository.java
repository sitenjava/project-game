package com.game.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.game.data.entities.Log;
import com.game.data.entities.Question;
import com.game.data.entities.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Integer> {
	@Query(value = "select r from UserResult r where r.user.id = :userid and r.question.game.id = :gameId")
    List<UserResult> findAllByUserIdAndGameId(@Param("userid") Integer userid ,
                                       @Param("gameId") Integer gameId);
	
	@Query(value = "delete from UserResult us where us.user.id = :userid and us.question.game.id = :gameId")
    void deleteAllByUserId(@Param("userid") Integer userid,
                           @Param("gameId") Integer gameId);
}
