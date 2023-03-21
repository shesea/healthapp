package com.spbu.healthapp.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spbu.healthapp.additional.Parser;
import com.spbu.healthapp.dto.ValueCustomRequest;
import com.spbu.healthapp.dto.ValueMoodRequest;
import com.spbu.healthapp.dto.ValuePressureRequest;
import com.spbu.healthapp.dto.ValueTemperatureRequest;
import com.spbu.healthapp.entity.*;
import com.spbu.healthapp.exception.UserNotFoundException;
import com.spbu.healthapp.service.IsCheckedService;
import com.spbu.healthapp.service.ParameterService;
import com.spbu.healthapp.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ValueController {
    @Autowired
    private ValueService service;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private IsCheckedService isCheckedService;
    @Autowired
    private ValueService valueService;

    @PostMapping("/value/pressure/add/{paramId}")
    public ResponseEntity<ValuePressure> savePressure(@RequestBody ValuePressureRequest request, @PathVariable int paramId) {
        isCheckedService.check(parameterService.getParameterById(paramId), request.getDate());
        return new ResponseEntity<>(service.savePressure(request, paramId), HttpStatus.CREATED);
    }

    @GetMapping("/value/pressure/getByDate/{parameterId}&{date}&{time}")
    public ValueEntity getPressureByDate(@PathVariable int parameterId, @PathVariable String date, @PathVariable String time) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(date, time);
        return valueService.getPressureByDate(parameterId, dateTime);
    }

    @PostMapping("/value/mood/add/{paramId}")
    public ResponseEntity<ValueMood> saveMood(@RequestBody ValueMoodRequest request, @PathVariable int paramId) {
        isCheckedService.check(parameterService.getParameterById(paramId), request.getDate());
        return new ResponseEntity<>(service.saveMood(request, paramId), HttpStatus.CREATED);
    }

    @GetMapping("/value/mood/getByDate/{parameterId}&{date}&{time}")
    public ValueMood getMoodByDate(@PathVariable int parameterId, @PathVariable String date, @PathVariable String time) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(date, time);
        return valueService.getMoodByDate(parameterId, dateTime);
    }

    @PostMapping("/value/temperature/add/{paramId}")
    public ResponseEntity<ValueTemperature> saveTemperature(@RequestBody ValueTemperatureRequest request, @PathVariable int paramId) {
        isCheckedService.check(parameterService.getParameterById(paramId), request.getDate());
        return new ResponseEntity<>(service.saveTemperature(request, paramId), HttpStatus.CREATED);
    }

    @GetMapping("/value/temperature/getByDate/{parameterId}&{date}&{time}")
    public ValueTemperature getTemperatureByDate(@PathVariable int parameterId, @PathVariable String date, @PathVariable String time) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(date, time);
        return valueService.getTemperatureByDate(parameterId, dateTime);
    }

    @PostMapping("/value/custom/add/{paramId}")
    public ResponseEntity<ValueCustom> saveCustom(@RequestBody ValueCustomRequest request, @PathVariable int paramId) {
        isCheckedService.check(parameterService.getParameterById(paramId), request.getDate());
        return new ResponseEntity<>(service.saveCustom(request, paramId), HttpStatus.CREATED);
    }

    @GetMapping("/value/custom/getByDate/{parameterId}&{date}&{time}")
    public ValueCustom getCustomByDate(@PathVariable int parameterId, @PathVariable String date, @PathVariable String time) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(date, time);
        return valueService.getCustomByDate(parameterId, dateTime);
    }
}
