package com.game.service.impl;

import com.game.common.MessageConstants;
import com.game.common.utils.Converter;
import com.game.common.exception.APIException;
import com.game.common.utils.SecurityUtils;
import com.game.data.dto.LogDto;
import com.game.data.entities.Log;
import com.game.data.entities.User;
import com.game.data.repository.GameRepository;
import com.game.data.repository.LogRepository;
import com.game.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class LogService implements ILogService
{
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private GameRepository gameRepository;
    private final Converter<LogDto,Log> logConverter = new Converter<>(LogDto.class,Log.class);


    @Override
    public List<LogDto> findAllByUserIdAndGameId(Integer userId , Integer gameId) {
        Set<User> users = gameRepository.findUsersByGameId(gameId);
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        List<Log> logs = logRepository.findAllByUserIdAndGameId(userId,gameId);
        return logConverter.toDto(logs);
    }

    @Override
    @Transactional
    public void delete(Integer userId , Integer gameId)
    {
        Set<User> users = gameRepository.findUsersByGameId(gameId);
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
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
