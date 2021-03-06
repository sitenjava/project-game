package com.game.data.repository;

import com.game.data.entities.Image;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    void deleteById(Integer id);

    @Query(value = "select i from Image i where i.game.id=:gameId and (:active is null or i.active=:active)" +
            " and (:activePlay is null or i.activePlay=:activePlay)")
    List<Image> findAll(@Param("gameId") Integer gameId, @Param("active") Boolean active,
                        @Param("activePlay") Boolean activePlay , Pageable pageable);

    //    @Query(value = "update Image set (:active is null or active=:active) or (:activePlay is null or activePlay=:activePlay) where id=:id")
//    void updateActive(@Param("id") Integer id , @Param("active") Boolean active ,
//                      @Param("activePlay") Boolean activePlay);
    @Modifying
    @Query(value = "update image_lat_hinh set active=:active where id=:id", nativeQuery = true)
    void updateActive(@Param("id") Integer id, @Param("active") Boolean active);

    @Modifying
    @Query(value = "update image_lat_hinh set active_play=:activePlay where id=:id", nativeQuery = true)
    void updateActivePlay(@Param("id") Integer id, @Param("activePlay") Boolean activePlay);
}
