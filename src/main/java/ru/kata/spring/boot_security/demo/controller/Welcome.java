package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.util.PersonValidator;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import javax.validation.Valid;

@Controller
public class Welcome {

    @GetMapping({"/", "/index"})
        public String sayWelcome(ModelMap model) {
            model.addAttribute("messages");
            return "admin/hello";
        }
    }
}
