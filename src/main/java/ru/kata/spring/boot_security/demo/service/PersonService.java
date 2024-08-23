package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Person;

import java.util.List;

@Service
public interface PersonService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<Person> getUsersList();

    Person getUserByID(long id);

    void save(Person person);

    void updateUser(Person updateUser);

    void deleteUser(long id);
}
