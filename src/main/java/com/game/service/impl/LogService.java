package com.game.service.impl;

import com.game.common.Converters.LogConverter;
import com.game.data.dto.LogDto;
import com.game.data.entities.Log;
import com.game.data.repository.LogRepository;
import com.game.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class LogService implements ILogService
{
    @Autowired
    private LogRepository logRepository;
    public static LogConverter logConverter = LogConverter.getInstance();

    @Override
    public Set<LogDto> findAllByUserId(Integer userId) {
        Set<Log> logs = logRepository.findAllByUserId(userId);
        return logConverter.toDto(logs);
    }

    @Override
    @Transactional
    public void delete(Integer userId)
    {
        logRepository.deleteAllByUserId(userId);
    }

    @Override
    @Transactional
    public LogDto save(LogDto logDto)
    {
        Log log = logConverter.toEntity(logDto);
        return logConverter.toDto(logRepository.save(log));
    }

    @Override
    @Transactional
    public void save(LogDto[] logs)
    {
        for (LogDto log : logs)
        {
            log = save(log);
        }
    }
}
