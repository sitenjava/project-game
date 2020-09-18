package com.game.data.repository;

import com.game.data.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query(value = "select n from Notification n where n.user.id =:userId")
    List<Notification> findAllByUserId(@Param("userId") Integer userId);
    @Modifying
    @Query(value = "update notification set active=:active where id=:id" , nativeQuery = true)
    void active(@Param("active") Boolean active,
                @Param("id") Integer id);
    @Query(value = "select count(n) from Notification n where n.active=:active and n.user.id=:userId ")
    Integer count(@Param("active") Boolean active,
                  @Param("userId") Integer userId);
}
