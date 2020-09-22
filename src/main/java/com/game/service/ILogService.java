package com.game.service;

import com.game.data.dto.LogDto;

import java.util.List;

public interface ILogService
{
    List<LogDto> findAllByUserIdAndGameId(Integer userId , Integer gameId);
    void delete(Integer userId  , Integer gameId);
    LogDto save(LogDto logDto);
}
