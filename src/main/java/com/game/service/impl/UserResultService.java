package com.game.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.MessageConstants;
import com.game.common.exception.APIException;
import com.game.common.utils.Converter;
import com.game.common.utils.SecurityUtils;
import com.game.data.dto.UserResultDto;
import com.game.data.entities.User;
import com.game.data.entities.UserResult;
import com.game.data.repository.GameRepository;
import com.game.data.repository.UserResultRepository;
import com.game.service.IUserResultService;

@Service
public class UserResultService implements IUserResultService {
	@Autowired
	private UserResultRepository userResultRepository;
	
	@Autowired
	private GameRepository gameRepository;
    private final Converter<UserResultDto,UserResult> userResultConverter = new Converter<>(UserResultDto.class,UserResult.class);
    
    @Override
    public List<UserResultDto> findAllByUserIdAndGameId(Integer userId , Integer gameId) {
        Set<User> users = gameRepository.findUsersByGameId(gameId);
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        List<UserResult> userResults = userResultRepository.findAllByUserIdAndGameId(userId,gameId);
        return userResultConverter.toDto(userResults);
    }
    
    @Override
    @Transactional
    public UserResultDto add(UserResultDto userResultDto) {
    	UserResult userResult = userResultConverter.toEntity(userResultDto);
        return userResultConverter.toDto(userResultRepository.save(userResult));
    }
    
    @Override
    @Transactional
    public UserResultDto update(UserResultDto userResultDto) {
    	UserResult userResult = userResultConverter.toEntity(userResultDto);
        return userResultConverter.toDto(userResultRepository.save(userResult));
    }
    
    @Override
    @Transactional
    public void delete(Integer userId , Integer gameId) {
    	Set<User> users = gameRepository.findUsersByGameId(gameId);
    	if (!SecurityUtils.getInstance().isGamePlayer(users))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        userResultRepository.deleteAllByUserId(userId,gameId);
    }
}
