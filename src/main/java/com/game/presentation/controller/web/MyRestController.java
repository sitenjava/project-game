package com.game.presentation.controller.web;

import com.game.data.entities.User;
import com.game.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/role/{roleName}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String roleName) {
        return new ResponseEntity<>(userService.getUsersByRole(roleName), HttpStatus.OK);
    }

    @GetMapping("/user/active/{status}")
    public ResponseEntity<List<User>> getUsersByStatus(@PathVariable boolean status) {
        return new ResponseEntity<>(userService.getUsersByStatus(status), HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User userDetails) {
        return new ResponseEntity<>(userService.updateUser(id, userDetails), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("You have deleted user with id " + id, HttpStatus.OK);
    }

    @DeleteMapping("/user/username/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>("You have deleted user with username '" + username + "'", HttpStatus.OK);
    }

}
