package com.game.presentation.controller.web;

import com.game.data.entities.User;
import com.game.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("index")
    public String homePage() {
        return "index";
    }

    @GetMapping("account")
    public String accountPage(Model model, Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("curUser", user);
        return "account";
    }
}
