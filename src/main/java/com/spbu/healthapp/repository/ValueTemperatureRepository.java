package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.ValuePressure;
import com.spbu.healthapp.entity.ValueTemperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ValueTemperatureRepository extends JpaRepository<ValueTemperature, Integer> {
    ValueTemperature findByParameterIdAndCheckedAt(int parameterId, LocalDateTime datetime);
}
