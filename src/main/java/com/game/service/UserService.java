package com.game.service;

import com.game.data.dto.UserRegistrationDto;
import com.game.data.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
