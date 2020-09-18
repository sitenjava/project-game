package com.game.presentation.controller.admin;

import com.game.data.entities.User;
import com.game.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(iUserService.getUserById(userId));
    }
}
