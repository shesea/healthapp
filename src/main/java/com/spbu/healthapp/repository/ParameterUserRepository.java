package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.MedicineUser;
import com.spbu.healthapp.entity.Parameter;
import com.spbu.healthapp.entity.ParameterUser;
import com.spbu.healthapp.entity.ParameterUserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParameterUserRepository extends JpaRepository<ParameterUser, ParameterUserId> {
    ParameterUser findByParameterIdAndUserId(int parameterId, int userId);

    List<ParameterUser> findByUserId(int userId);
}
