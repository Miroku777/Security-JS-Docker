package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final PeopleRepository peopleRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder,
                               PeopleRepository peopleRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.peopleRepository = peopleRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Set<Role> roles = new HashSet<>();

        if (person.getUsername().equalsIgnoreCase("admin")) {
            Role roleAdmin = roleRepository.findByRoleName("ROLE_ADMIN");
            Role roleAdminUser = roleRepository.findByRoleName("ROLE_USER");
            roles.add(roleAdmin);
            roles.add(roleAdminUser);
        } else {
            Role roleUser = roleRepository.findByRoleName("ROLE_USER");
            roles.add(roleUser);
        }
        person.setRoles(roles);
        peopleRepository.save(person);

    }
}
