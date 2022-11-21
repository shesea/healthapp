package com.spbu.healthapp.controller;

import com.spbu.healthapp.entity.User;
import com.spbu.healthapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/user/addList")
    public List<User> addUsers(@RequestBody List<User> users) {
        return service.saveUsers(users);
    }

    @GetMapping("/user/getList")
    public List<User> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/getById/{id}")
    public User findUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/user/getByEmail/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

}
