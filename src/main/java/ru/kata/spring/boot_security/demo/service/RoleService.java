package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;

public interface RoleService {

    @Transactional
    void save(Role role);

    Role getByName(String name);
}
