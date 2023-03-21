package com.spbu.healthapp.controller;

import com.spbu.healthapp.dto.ParameterRequest;
import com.spbu.healthapp.entity.Parameter;
import com.spbu.healthapp.exception.UserNotFoundException;
import com.spbu.healthapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ParameterUserService parameterUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private IsCheckedService isCheckedService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/parameter/add/{userId}")
    public ResponseEntity<Parameter> addParameter(@RequestBody ParameterRequest parameterRequest, @PathVariable int userId) throws UserNotFoundException {
        Parameter parameterAdded = parameterService.saveParameter(parameterRequest);
        parameterUserService.saveParameterUser(parameterAdded, userService.getUserById(userId));
        return new ResponseEntity<>(parameterAdded, HttpStatus.CREATED);
    }

    @GetMapping("/parameter/getList/{userId}")
    public List<Parameter> getAllParameterForUser(@PathVariable int userId) {
        return parameterService.getParameterForUser(userId);
    }

    @GetMapping("/parameter/getByDate/{userId}&{date}")
    public List<Parameter> getParameterByDate(@PathVariable int userId, @PathVariable String date) {
        return parameterService.getParameterByDateForUser(date, userId);
    }

    @PostMapping("/parameter/add/pressure/{userId}")
    public ResponseEntity<Parameter> addPressure(@RequestBody ParameterRequest parameterRequest, @PathVariable int userId) throws UserNotFoundException {
        parameterRequest.setName("Давление");
        parameterRequest.setDefault(true);
        Parameter parameterAdded = parameterService.saveParameter(parameterRequest);
        parameterUserService.saveParameterUser(parameterAdded, userService.getUserById(userId));
        return new ResponseEntity<>(parameterAdded, HttpStatus.CREATED);
    }

    @PostMapping("/parameter/add/mood/{userId}")
    public ResponseEntity<Parameter> addMood(@RequestBody ParameterRequest parameterRequest, @PathVariable int userId) throws UserNotFoundException {
        parameterRequest.setName("Настроение");
        parameterRequest.setDefault(true);
        Parameter parameterAdded = parameterService.saveParameter(parameterRequest);
        parameterUserService.saveParameterUser(parameterAdded, userService.getUserById(userId));
        return new ResponseEntity<>(parameterAdded, HttpStatus.CREATED);
    }

    @PostMapping("/parameter/add/temperature/{userId}")
    public ResponseEntity<Parameter> addTemperature(@RequestBody ParameterRequest parameterRequest, @PathVariable int userId) throws UserNotFoundException {
        parameterRequest.setName("Температура");
        parameterRequest.setDefault(true);
        Parameter parameterAdded = parameterService.saveParameter(parameterRequest);
        parameterUserService.saveParameterUser(parameterAdded, userService.getUserById(userId));
        return new ResponseEntity<>(parameterAdded, HttpStatus.CREATED);
    }

    @PostMapping("/parameter/check/{parameterId}&{date}")
    public void check(@PathVariable int parameterId, @PathVariable String date) {
        isCheckedService.check(parameterService.getParameterById(parameterId), date);
    }

    @PostMapping("/parameter/uncheck/{parameterId}&{date}")
    public void uncheck(@PathVariable int parameterId, @PathVariable String date) {
        isCheckedService.uncheck(parameterService.getParameterById(parameterId), date);
    }
}
