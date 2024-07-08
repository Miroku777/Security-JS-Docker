package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.PersonDetailsService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private PersonDetailsService personDetailsService;

    private RoleService roleService;

    @Autowired
    public void setUserService(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Autowired
    public void setRoleDAO(RoleService roleService) {
        this.roleService = roleService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    private void postConstruct() {
        Role adminRole = new Role(1, "ROLE_ADMIN");
        Role userRole = new Role(2, "ROLE_USER");
        roleService.save(adminRole);
        roleService.save(userRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        /*Set<Role> set = new HashSet<>(Arrays.asList(adminRole, userRole));*/
        Set<Role> allRoles = Set.of(adminRole, userRole);
        Person admin = new Person(1, "admin", getPasswordEncoder().encode("admin"), allRoles);
        Person user = new Person(2, "user", getPasswordEncoder().encode("user"), userRoles);
        personDetailsService.save(admin);
        personDetailsService.save(user);
    }

}
