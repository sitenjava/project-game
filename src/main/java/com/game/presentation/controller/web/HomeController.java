package com.game.presentation.controller.web;

import com.game.CustomUserDetails;
import com.game.common.Utils.SecurityUtils;
import com.game.data.dto.ImageDto;
import com.game.data.entities.Game;
import com.game.data.entities.User;
import com.game.data.repository.GameRepository;
import com.game.data.repository.UserRepository;
import com.game.service.IGameService;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private IImageService iImageService;
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
