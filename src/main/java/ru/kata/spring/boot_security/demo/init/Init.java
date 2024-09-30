package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.PersonService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private PersonService personService;
    private RoleService roleService;

    @Autowired
    public void setUserService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setRoleDAO(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        Role adminRole = new Role(1, "ROLE_ADMIN");
        Role userRole = new Role(2, "ROLE_USER");
        roleService.save(adminRole);
        roleService.save(userRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        Set<Role> allRoles = Set.of(adminRole, userRole);
        Person admin = new Person(1, "admin", "admin", allRoles, "email@mail.ru", 1999);
        Person user = new Person(2, "user", "user", userRoles, "email@gmail.com", 1980);
        personService.save(admin);
        personService.save(user);
    }

}
