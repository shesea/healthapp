package com.spbu.healthapp.controller;

import com.spbu.healthapp.entity.Parameter;
import com.spbu.healthapp.entity.ScheduledEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ScheduledEntityController {
    @Autowired
    private ParameterController parameterController;

    @Autowired
    private MedicineController medicineController;

    @GetMapping("/all/getSorted/{userId}&{date}")
    public List<ScheduledEntity> getAllSortedByDate(@PathVariable int userId, @PathVariable String date) {
        List<ScheduledEntity> arr = new ArrayList<>(parameterController.getParameterByDate(userId, date));
        arr.addAll(medicineController.getMedicineByDate(userId, date));
        arr.sort(Comparator.comparing(s -> s.getSchedule().getTime().toLocalTime()));
        return arr;
    }
}
