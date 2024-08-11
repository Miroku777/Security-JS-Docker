package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getUsersList();

    Optional<Person> getUserByUsername(String userName);

    Person getUserByID(long id);

    @Transactional
    void save(Person person);

    @Transactional
    void updateUser(Person updateUser);

    @Transactional
    void deleteUser(long id);
}
