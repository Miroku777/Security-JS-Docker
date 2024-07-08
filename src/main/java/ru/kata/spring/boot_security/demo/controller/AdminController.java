package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.service.PersonDetailsService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import javax.validation.Valid;

@Controller
public class AdminController {

    private final PersonDetailsService personDetailsService;
    private final RoleService roleService;
    @Autowired
    public AdminController(PersonDetailsService personDetailsService, RoleService roleService) {
        this.personDetailsService = personDetailsService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("people", personDetailsService.getUsersList());
        return "users/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("person", personDetailsService.getUserByID(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/editUser";
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                             @RequestParam("id") long id) {
        if (bindingResult.hasErrors()) {
            return "users/editUser";
        }
        personDetailsService.updateUser(id, person);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") long id) {
        personDetailsService.deleteUser(id);
        return "redirect:/admin";
    }
}
