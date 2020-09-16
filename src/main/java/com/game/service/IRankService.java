package com.game.service;

import com.game.data.dto.RankDto;

import java.util.List;

public interface IRankService
{
    RankDto save(RankDto rankDto);
    List<RankDto> findAll(Integer gameId , Integer page , Integer limit);
}
