
package com.game.service.impl;

import com.game.CustomUserDetails;
import com.game.data.dto.UserRegistrationDto;
import com.game.data.entities.Role;
import com.game.data.entities.User;
import com.game.data.repository.RoleRepository;
import com.game.data.repository.UserRepository;
import com.game.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

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
        User user = new User(userRegDto.getUsername(), passwordEncoder.encode(userRegDto.getPassword()),
                userRegDto.getEmail(), userRegDto.getName(), true,
                Collections.singletonList(roleRepository.findRoleByName(
                        userRegDto.getUsername().equals("admin") ? "ADMIN" : "USER")
                ));
        return userRepository.save(user);
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
