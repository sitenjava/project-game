package com.game.data.repository;

import com.game.data.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface LogRepository extends JpaRepository<Log, Integer>
{
    @Modifying
    @Query(value = "delete from log where user_id = :userid" , nativeQuery = true)
    void deleteAllByUserId(@Param("userid") Integer userid);

    @Modifying
    @Query(value = "select * from log where user_id = :userid" , nativeQuery = true)
    Set<Log> findAllByUserId(@Param("userid") Integer userid);
}
