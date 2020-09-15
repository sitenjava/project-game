package com.game.data.repository;

import com.game.data.dto.ImageDto;
import com.game.data.entities.Image;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    void deleteById(Integer id);

    @Query(value = "select i from Image i where i.game.id=:gameId and (:active is null or i.active=:active)" +
            " and (:activePlay is null or i.activePlay=:activePlay)")
    List<Image> findAll(@Param("gameId") Integer gameId, @Param("active") Boolean active,
                        @Param("activePlay") Boolean activePlay , Pageable pageable);
    @Query(value = "select i.id,i.value,i.link from Image i where i.game.id=:gameId and i.activePlay=true and i.active=true")
    List<Object> getLinkImages(@Param("gameId") Integer gameId);
    @Modifying
    @Query(value = "update image_lat_hinh set active=:active where id=:id", nativeQuery = true)
    void updateActive(@Param("id") Integer id, @Param("active") Boolean active);

    @Modifying
    @Query(value = "update image_lat_hinh set active_play=:activePlay where id=:id", nativeQuery = true)
    void updateActivePlay(@Param("id") Integer id, @Param("activePlay") Boolean activePlay);

    @Query(value = "select i.link from Image i where i.id=:id")
    String getLinkImage(@Param("id") Integer id);
}
