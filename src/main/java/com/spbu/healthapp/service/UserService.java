package com.spbu.healthapp.service;

import com.spbu.healthapp.entity.User;
import com.spbu.healthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "user removed: " + id;
    }

    public User updateUser(User user) {
        User userToChange = repository.findById(user.getId()).orElse(null);
        userToChange.setFirstName(user.getFirstName());
        userToChange.setLastName(user.getLastName());
        userToChange.setPatronymic(user.getPatronymic());
        userToChange.setEmail(user.getEmail());
        userToChange.setPassword(user.getPassword());
        return repository.save(userToChange);
    }
}
