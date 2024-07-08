package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

public interface RoleService {

    @Transactional
    void save(Role role);

    Set<Role> getAllRoles();

    Role getRoleById(long id);
}
