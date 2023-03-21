package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.ValueMood;
import com.spbu.healthapp.entity.ValuePressure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ValueMoodRepository extends JpaRepository<ValueMood, Integer> {
    ValueMood findByParameterIdAndCheckedAt(int parameterId, LocalDateTime datetime);

}
