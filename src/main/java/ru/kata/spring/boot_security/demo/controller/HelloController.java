package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.service.PersonDetailsService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public HelloController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping({"/", "/index"})
    public String sayWelcome(ModelMap model) {
        model.addAttribute("messages");
        return "hello";
    }

    @GetMapping("/user")
    public String showUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model, Principal principal) {

        model.addAttribute("isAdmin", userDetails.getAuthorities().stream()
                .anyMatch(admin -> admin.getAuthority().equals("ROLE_ADMIN")));

        Person person = personDetailsService.getUserByUsername(principal.getName());
        model.addAttribute("person", person);

        return "users/user";
    }
}
