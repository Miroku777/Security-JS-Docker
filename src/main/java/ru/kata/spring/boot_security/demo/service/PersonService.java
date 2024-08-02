package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

public interface PersonService {

    List<Person> getUsersList();

    Person getUserByUsername(String userName);

    Person getUserByID(long id);

    @Transactional
    void register(Person person, Set<Role> role);

    @Transactional
    void save(Person person);

    @Transactional
    void updateUser(long id, Person updateUser);

    @Transactional
    void deleteUser(long id);
}
