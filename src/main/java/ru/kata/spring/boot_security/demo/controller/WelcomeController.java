package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String showIndexPageAdmin() {
        return "index";
    }

    @GetMapping("/user")
    public String showIndexPageUser() {
        return "user";
    }
}
