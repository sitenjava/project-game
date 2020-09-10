package com.game.service;

import com.game.data.dto.LogDto;

import java.util.Set;

public interface ILogService
{
    Set<LogDto> findAllByUserIdAndGameId(Integer userId ,Integer gameId);
    void delete(Integer userId  , Integer gameId);
    LogDto save(LogDto logDto);
}
