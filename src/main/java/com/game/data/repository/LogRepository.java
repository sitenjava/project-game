package com.game.data.repository;

import com.game.data.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface LogRepository extends JpaRepository<Log, Integer>
{
    @Query(value = "delete from Log l where l.user.id = :userid and l.image.game.id = :gameId")
    void deleteAllByUserId(@Param("userid") Integer userid,
                           @Param("gameId") Integer gameId);

    @Query(value = "select l from Log l where l.user.id = :userid and l.image.game.id = :gameId")
    List<Log> findAllByUserIdAndGameId(@Param("userid") Integer userid ,
                                       @Param("gameId") Integer gameId);
}
