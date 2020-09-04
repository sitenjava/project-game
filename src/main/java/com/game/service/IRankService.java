package com.game.service;

import com.game.data.dto.RankDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IRankService
{
    RankDto save(RankDto rankDto);
    List<RankDto> findAll(Integer gameId , Pageable pageable);
}
