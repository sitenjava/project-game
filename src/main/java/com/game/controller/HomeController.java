package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
    @GetMapping("/trang-chu")
    public ModelAndView homePage()
    {
        ModelAndView mav = new ModelAndView("web/home");
        return mav;
    }
}
