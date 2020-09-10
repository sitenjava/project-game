package com.game.data.repository;

import com.game.data.entities.Rank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Integer>
{
    @Query(value = "Select r from Rank r where r.game.id=:gameId")
    List<Rank> findAllByGameId(Integer gameId , Pageable pageable);
}
