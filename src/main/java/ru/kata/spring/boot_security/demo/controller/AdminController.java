package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.service.PersonService;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;
    private final RoleService roleService;
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    @Autowired
    public AdminController(PersonService personService, RoleService roleService, RegistrationService registrationService, PersonValidator personValidator) {
        this.personService = personService;
        this.roleService = roleService;
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "admin/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person")
                                      @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/registration";
        }
        registrationService.register(person);
        return "redirect:/admin/login";
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("people", personService.getUsersList());
        return "admin/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("person", personService.getUserByID(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/editUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                             @RequestParam("id") long id) {
        if (bindingResult.hasErrors()) {
            return "admin/editUser";
        }
        personService.updateUser(id, person);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        personService.deleteUser(id);
        return "redirect:/admin";
    }
}
