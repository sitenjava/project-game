package com.game.service;

import java.util.List;

import com.game.data.dto.UserResultDto;

public interface IUserResultService {
	List<UserResultDto> findAllByUserIdAndGameId(Integer userId , Integer gameId);
	UserResultDto add(UserResultDto userResultDto);
    UserResultDto update(UserResultDto userResultDto);
    void delete(Integer userId  , Integer gameId);
    
    
}
