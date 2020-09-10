
package com.game.service.impl;

import com.game.CustomUserDetails;
import com.game.UserNotFoundException;
import com.game.data.dto.UserRegistrationDto;
import com.game.data.entities.Role;
import com.game.data.entities.User;
import com.game.data.repository.RoleRepository;
import com.game.data.repository.UserRepository;
import com.game.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserRegistrationDto userRegDto) {
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleByName(userRegDto.getUsername().equals("admin") ? "ADMIN" : "USER"));

        User user = new User(userRegDto.getUsername(), passwordEncoder.encode(userRegDto.getPassword()),
                userRegDto.getEmail(), userRegDto.getName(), true, roles);
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username, 1);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByStatus(boolean active) {
        List<User> users = userRepository.findUserByStatus(active);

        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException(active);
        }

        return users;
    }

    @Override
    public List<User> getUsersByRole(String roleName) {

        List<User> users = userRepository.findUsersByRole(roleName);

        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException(roleName, 2);
        }

        return users;
    }

    @Override
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setActive(userDetails.getActive());
        user.setRoles(userDetails.getRoles());

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        userRepository.delete(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username, 1);
        }

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetails(user);
    }

}
