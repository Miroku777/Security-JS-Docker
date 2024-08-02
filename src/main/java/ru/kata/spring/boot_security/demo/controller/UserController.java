package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.service.PersonService;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final PersonService personService;

    @Autowired
    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String showUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model, Principal principal) {

        model.addAttribute("isAdmin", userDetails.getAuthorities().stream()
                .anyMatch(admin -> admin.getAuthority().equals("ROLE_ADMIN")));

        Person person = personService.getUserByUsername(principal.getName());
        model.addAttribute("person", person);

        return "users/user";
    }
}
