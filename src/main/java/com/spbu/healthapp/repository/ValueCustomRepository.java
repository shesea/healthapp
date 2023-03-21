package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.ValueCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ValueCustomRepository extends JpaRepository<ValueCustom, Integer> {
    ValueCustom findByParameterIdAndCheckedAt(int parameterId, LocalDateTime datetime);
}
