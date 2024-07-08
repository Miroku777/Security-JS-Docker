package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Person;

import java.util.List;

public interface PersonDetailsService {

    List<Person> getUsersList();

    Person getUserByUsername(String userName);

    Person getUserByID(long id);

    @Transactional
    void save(Person user);

    @Transactional
    void updateUser(long id, Person updateUser);

    @Transactional
    void deleteUser(long id);
}
