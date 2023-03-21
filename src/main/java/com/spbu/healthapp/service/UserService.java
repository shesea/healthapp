package com.spbu.healthapp.service;

import com.spbu.healthapp.dto.UserRequest;
import com.spbu.healthapp.entity.Parameter;
import com.spbu.healthapp.entity.Schedule;
import com.spbu.healthapp.entity.User;
import com.spbu.healthapp.exception.UserExistsException;
import com.spbu.healthapp.exception.UserNotExistsException;
import com.spbu.healthapp.exception.UserNotFoundException;
import com.spbu.healthapp.exception.WrongPasswordException;
import com.spbu.healthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ParameterUserService parameterUserService;

    public User saveUser(UserRequest userRequest) throws UserExistsException {
        if (getUserByEmail(userRequest.getEmail()) == null) {
            User user = new User(userRequest.getEmail(), userRequest.getName(), userRequest.getPassword());
            return repository.save(user);
        } else {
            throw new UserExistsException("user with email '" + userRequest.getEmail() + "' already exists");
        }
    }

    public User login(UserRequest userRequest) throws Exception {
        if (getUserByEmail(userRequest.getEmail()) != null) {
            User user = getUserByEmail(userRequest.getEmail());
            if (Objects.equals(user.getPassword(), userRequest.getPassword())) {
                return user;
            } else {
                throw new WrongPasswordException("entered wrong password");
            }
        } else {
            throw new UserNotExistsException("there is no account with this email");
        }
    }


    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) throws UserNotFoundException {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("user not found with id: " + id);
        }
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "user removed: " + id;
    }

    public User updateUser(int userId, String name) throws UserNotFoundException {
        User userToChange = repository.findById(userId).orElse(null);
        if (userToChange != null) {
            userToChange.setName(name);
            return repository.save(userToChange);
        } else {
            throw new UserNotFoundException("user not found with id: " + userId);
        }
    }
}
