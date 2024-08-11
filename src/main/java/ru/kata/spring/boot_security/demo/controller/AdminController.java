package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Person;
import ru.kata.spring.boot_security.demo.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllUsers() {
        return new ResponseEntity<>(personService.getUsersList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(personService.getUserByID(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> performRegistration(@RequestBody @Valid Person person) {
        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        }
        personService.updateUser(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        personService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
