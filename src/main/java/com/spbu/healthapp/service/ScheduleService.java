package com.spbu.healthapp.service;

import com.spbu.healthapp.entity.Schedule;
import com.spbu.healthapp.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    public Schedule saveSchedule(Schedule schedule) {
        return repository.save(schedule);
    }
}
