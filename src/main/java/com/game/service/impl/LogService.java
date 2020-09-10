package com.game.service.impl;

import com.game.common.Converters.LogConverter;
import com.game.common.Utils.SecurityUtils;
import com.game.data.dto.LogDto;
import com.game.data.entities.Log;
import com.game.data.entities.User;
import com.game.data.repository.GameRepository;
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
    @Autowired
    private GameRepository gameRepository;
    public static LogConverter logConverter = LogConverter.getInstance();

    @Override
    public Set<LogDto> findAllByUserIdAndGameId(Integer userId , Integer gameId) {
        Set<User> users = gameRepository.findUsersByGameId(gameId);
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            return null;
        Set<Log> logs = logRepository.findAllByUserIdAndGameId(userId,gameId);
        return logConverter.toDto(logs);
    }

    @Override
    @Transactional
    public void delete(Integer userId , Integer gameId)
    {
        Set<User> users = gameRepository.findUsersByGameId(gameId);
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            return;
        logRepository.deleteAllByUserId(userId,gameId);
    }

    @Override
    @Transactional
    public LogDto save(LogDto logDto)
    {
        Log log = logConverter.toEntity(logDto);
        return logConverter.toDto(logRepository.save(log));
    }

}
