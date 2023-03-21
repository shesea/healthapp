package com.spbu.healthapp.service;

import com.spbu.healthapp.additional.Parser;
import com.spbu.healthapp.entity.IsChecked;
import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.entity.Parameter;
import com.spbu.healthapp.repository.IsCheckedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class IsCheckedService {
    @Autowired
    private IsCheckedRepository repository;

    public void check(Medicine medicine, String date) {
        String timeToParse = date + " " + medicine.getSchedule().getTime().toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(timeToParse, formatter);
        IsChecked isChecked = repository.getCheckedByDate(medicine.getId(), timeToParse);
        if (isChecked != null) {
            isChecked.setChecked(!isChecked.isChecked());
        } else {
            isChecked = new IsChecked(0, true, dateTime, medicine);
        }
        repository.save(isChecked);
    }

    /*public void uncheck(Medicine medicine, String date) {
        String datetime = date + " " + medicine.getSchedule().getTime().toLocalTime();
        IsChecked isChecked = repository.getCheckedByDate(medicine.getId(), datetime);
        isChecked.setChecked(false);
        repository.save(isChecked);
    }*/

    public void check(Parameter parameter, String date) {
        String timeToParse = date + " " + parameter.getSchedule().getTime().toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(timeToParse, formatter);
        IsChecked isChecked = repository.getCheckedByDate(parameter.getId(), timeToParse);
        if (isChecked != null) {
            isChecked.setChecked(true);
        } else {
            isChecked = new IsChecked(0, true, dateTime, parameter);
        }
        repository.save(isChecked);
    }

    public void uncheck(Parameter parameter, String date) {
        String datetime = date + " " + parameter.getSchedule().getTime().toLocalTime();
        IsChecked isChecked = repository.getCheckedByDate(parameter.getId(), datetime);
        isChecked.setChecked(false);
        repository.save(isChecked);
    }

    public boolean getCheckedByDate(int id, String datetime) {
        IsChecked isChecked = repository.getCheckedByDate(id, datetime);
        if (isChecked != null){
            return isChecked.isChecked();
        } else {
            return false;
        }
    }

}