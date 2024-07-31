package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = peopleRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(person.getUsername(),
                person.getPassword(), mapRolesToAuthorities(person.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public List<Person> getUsersList() {
        return peopleRepository.findAll();
    }

    @Override
    public Person getUserByUsername(String userName) {
        return peopleRepository.findByUsername(userName);
    }

    @Override
    public Person getUserByID(long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Person user) {
        peopleRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(long id, Person personUpdate) {
        Person person = getUserByUsername(personUpdate.getUsername());
        if (person != null && person.getId() != personUpdate.getId()) {
            return;
        }
        personUpdate.setId(id);
        peopleRepository.save(personUpdate);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        peopleRepository.deleteById(id);
    }
}


