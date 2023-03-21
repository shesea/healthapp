package com.spbu.healthapp.service;

import com.spbu.healthapp.additional.Parser;
import com.spbu.healthapp.dto.ValueCustomRequest;
import com.spbu.healthapp.dto.ValueMoodRequest;
import com.spbu.healthapp.dto.ValuePressureRequest;
import com.spbu.healthapp.dto.ValueTemperatureRequest;
import com.spbu.healthapp.entity.*;
import com.spbu.healthapp.repository.ValueCustomRepository;
import com.spbu.healthapp.repository.ValueMoodRepository;
import com.spbu.healthapp.repository.ValuePressureRepository;
import com.spbu.healthapp.repository.ValueTemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ValueService {
    @Autowired
    private ValuePressureRepository pressureRepository;

    @Autowired
    private ValueMoodRepository moodRepository;

    @Autowired
    private ValueTemperatureRepository temperatureRepository;

    @Autowired
    private ValueCustomRepository customRepository;

    @Autowired
    private ParameterService parameterService;

    public ValuePressure savePressure(ValuePressureRequest request, int paramId) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(request.getDate(), request.getTime());

        ValuePressure pressure = pressureRepository.findByParameterIdAndCheckedAt(paramId, dateTime);
        if (pressure == null) {
            pressure = new ValuePressure(
                    0,
                    request.getHighPressure(),
                    request.getLowPressure(),
                    request.getPulse(),
                    dateTime
            );
            pressure.setParameter(parameterService.getParameterById(paramId));
        } else {
            pressure.setSystolicValue(request.getHighPressure());
            pressure.setDiastolicValue(request.getLowPressure());
            pressure.setPulseValue(request.getPulse());
        }

        return pressureRepository.save(pressure);
    }

    public ValueEntity getPressureByDate(int parameterId, LocalDateTime datetime) {
        return pressureRepository.findByParameterIdAndCheckedAt(parameterId, datetime);
    }

    public ValueMood saveMood(ValueMoodRequest request, int paramId) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(request.getDate(), request.getTime());

        ValueMood mood = moodRepository.findByParameterIdAndCheckedAt(paramId, dateTime);
        if (mood == null) {
            mood = new ValueMood(
                    0,
                    request.getScore(),
                    dateTime
            );
            mood.setParameter(parameterService.getParameterById(paramId));
        } else {
            mood.setScore(request.getScore());
        }

        return moodRepository.save(mood);
    }

    public ValueMood getMoodByDate(int parameterId, LocalDateTime datetime) {
        return moodRepository.findByParameterIdAndCheckedAt(parameterId, datetime);
    }

    public ValueTemperature saveTemperature(ValueTemperatureRequest request, int paramId) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(request.getDate(), request.getTime());

        ValueTemperature temperature = temperatureRepository.findByParameterIdAndCheckedAt(paramId, dateTime);
        if (temperature == null) {
            temperature = new ValueTemperature(
                    0,
                    request.getIntPart(),
                    request.getFracPart(),
                    dateTime
            );
            temperature.setParameter(parameterService.getParameterById(paramId));
        } else {
            temperature.setIntegerPart(request.getIntPart());
            temperature.setFractionalPart(request.getFracPart());
        }

        return temperatureRepository.save(temperature);
    }

    public ValueTemperature getTemperatureByDate(int parameterId, LocalDateTime datetime) {
        return temperatureRepository.findByParameterIdAndCheckedAt(parameterId, datetime);
    }

    public ValueCustom saveCustom(ValueCustomRequest request, int paramId) {
        LocalDateTime dateTime = Parser.strToLocalDateTime(request.getDate(), request.getTime());

        ValueCustom custom = customRepository.findByParameterIdAndCheckedAt(paramId, dateTime);
        if (custom == null) {
            custom = new ValueCustom(
                    0,
                    request.getValue(),
                    dateTime
            );
            custom.setParameter(parameterService.getParameterById(paramId));
        } else {
            custom.setValue(request.getValue());
        }

        return customRepository.save(custom);
    }

    public ValueCustom getCustomByDate(int parameterId, LocalDateTime datetime) {
        return customRepository.findByParameterIdAndCheckedAt(parameterId, datetime);
    }
}
