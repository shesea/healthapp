package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.ValueEntity;
import com.spbu.healthapp.entity.ValuePressure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ValuePressureRepository extends JpaRepository<ValuePressure, Integer> {
    ValuePressure findByParameterIdAndCheckedAt(int parameterId, LocalDateTime datetime);
}
