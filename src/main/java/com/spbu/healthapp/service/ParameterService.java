package com.spbu.healthapp.service;

import com.spbu.healthapp.dto.ParameterRequest;
import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.entity.Parameter;
import com.spbu.healthapp.entity.ParameterUser;
import com.spbu.healthapp.entity.Schedule;
import com.spbu.healthapp.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository repository;

    @Autowired
    private ParameterUserService parameterUserService;

    @Autowired
    private IsCheckedService isCheckedService;

    public Parameter saveParameter(ParameterRequest parameterRequest) {
        String timeToParse = parameterRequest.getStartDay() + " " + parameterRequest.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(timeToParse, formatter);
        LocalDate date = LocalDate.parse(parameterRequest.getStartDay());

        int duration = parameterRequest.getDuration();

        Parameter parameter = new Parameter();
        Schedule schedule = new Schedule(
                0, dateTime, date, date.plusDays(duration - 1), duration, parameter
        );

        parameter.setName(parameterRequest.getName());
        parameter.setDefault(parameterRequest.isDefault());
        parameter.setSchedule(schedule);

        return repository.save(parameter);
    }

    public List<Parameter> getParameter() {
        return repository.findAll();
    }

    public Parameter getParameterById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteParameter(int id) {
        repository.deleteById(id);
        return "parameter removed: " + id;
    }

    public List<Parameter> getParameterForUser(int userId) {
        List<ParameterUser> parameterUsers = parameterUserService.getParameterForUser(userId);
        List<Parameter> parameter = new ArrayList<>();
        parameterUsers.forEach(parameterUser -> parameter.add(parameterUser.getParameter()));
        return parameter;
    }

    public List<Parameter> getParameterByDateForUser(String date, int userId) {
        List<Integer> ids = repository.getParameterByDate(date, userId);
        List<Parameter> parameters = new ArrayList<>();
        ids.forEach(id -> {
            parameters.add(getParameterById(id));
            getParameterById(id).setChecked(isCheckedService.getCheckedByDate(
                    id, date + " " + getParameterById(id).getSchedule().getTime().toLocalTime()));
        });
        return parameters;
    }

    public Parameter getPressureParamForUser(int userId) {
        List<Parameter> paramsForUser = getParameterForUser(userId);
        Parameter pressure = null;
        for (int i = 0; i < paramsForUser.size(); i++) {
            Parameter param = paramsForUser.get(i);
            if (Objects.equals(param.getName(), "Давление") && param.isDefault()) {
                pressure = param;
            }
        }
        return pressure;
    }
}
