package com.spbu.healthapp.service;

import com.spbu.healthapp.entity.*;
import com.spbu.healthapp.repository.ParameterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterUserService {
    @Autowired
    private ParameterUserRepository repository;

    public ParameterUser saveParameterUser(Parameter parameter, User user) {
        ParameterUser parameterUser = new ParameterUser(parameter, user);
        return repository.save(parameterUser);
    }

    public List<ParameterUser> getParameterForUser(int userId) {
        return repository.findByUserId(userId);
    }
}
