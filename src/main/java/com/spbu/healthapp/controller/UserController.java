package com.spbu.healthapp.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spbu.healthapp.dto.UserRequest;
import com.spbu.healthapp.entity.User;
import com.spbu.healthapp.exception.UserExistsException;
import com.spbu.healthapp.exception.UserNotFoundException;
import com.spbu.healthapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserRequest userRequest) throws UserExistsException {
        return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/user/signIn")
    public ResponseEntity<User> signIn(@RequestBody UserRequest userRequest) throws Exception {
        return new ResponseEntity<>(service.login(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/user/getList")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/user/getById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/user/getByEmail/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody ObjectNode objectNode) throws UserNotFoundException {
        return ResponseEntity.ok(service.updateUser(objectNode.get("id").asInt(), objectNode.get("name").asText()));
    }

    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }

}
