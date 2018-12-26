package com.dac.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class HomeController {

    // go to login page
    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
