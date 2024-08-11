package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PeopleRepository peopleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PeopleRepository peopleRepository, RoleService roleService,
                             PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = peopleRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user.get();
    }

    @Override
    public List<Person> getUsersList() {
        return peopleRepository.findAll();
    }

    @Override
    public Optional<Person> getUserByUsername(String userName) {
        return peopleRepository.findByUsername(userName);
    }

    @Override
    public Person getUserByID(long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }

    @Transactional
    @Override
    public void updateUser(Person personUpdate) {
        Optional<Person> person = getUserByUsername(personUpdate.getUsername());
        Person userFromDB = peopleRepository.findUserById(personUpdate.getId());
        if (personUpdate.getRoles().isEmpty()) {
            personUpdate.setRoles(userFromDB.getRoles());
        } else {
            personUpdate.setRoles(personUpdate.getRoles().stream()
                    .map(role -> roleService.getByName(role.getRoleName()))
                    .collect(Collectors.toSet()));
        }
        peopleRepository.save(personUpdate);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        peopleRepository.deleteById(id);
    }
}


