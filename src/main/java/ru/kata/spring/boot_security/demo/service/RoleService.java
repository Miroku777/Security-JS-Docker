package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

@Service
public interface RoleService {

    void save(Role role);

    List<Role> findAll();

    Role getByName(String name);
}
