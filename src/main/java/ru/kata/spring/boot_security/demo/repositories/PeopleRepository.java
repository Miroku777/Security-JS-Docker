package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Person;


import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Long> {
    Person findByUsername(String userName);
}