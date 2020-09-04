package com.game.presentation.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminPage {

    @GetMapping("index")
    public String index() {
        return "admin/index";
    }
}
