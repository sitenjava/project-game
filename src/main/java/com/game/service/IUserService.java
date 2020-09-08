package com.game.service;

import com.game.data.dto.UserRegistrationDto;
import com.game.data.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);

    User createUser(User user);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    List<User> getUsersByStatus(boolean active);

    List<User> getUsersByRole(String role);

    User updateUser(Integer id, User userDetails);

    void deleteUserById(Integer id);

    void deleteUserByUsername(String username);
}
