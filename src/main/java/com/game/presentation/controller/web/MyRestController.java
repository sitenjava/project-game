package com.game.presentation.controller.web;

import com.game.data.entities.User;
import com.game.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test1")
    public String test1() {
        return "API Test 1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "API Test 2";
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userRepository.findUserById(id);
        return ResponseEntity.ok(user);
    }

}
