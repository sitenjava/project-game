package com.game.service;

import com.game.data.dto.LogDto;

import java.util.Set;

public interface ILogService
{
    Set<LogDto> findAllByUserId(Integer userId);
    void delete(Integer userId);
    LogDto save(LogDto logDto);
    void  save(LogDto[] logs);
}
