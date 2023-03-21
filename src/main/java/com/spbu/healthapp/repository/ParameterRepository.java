package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
    @Query(value = "SELECT parameter_id FROM (SELECT * FROM parameter_user pu JOIN parameter p ON pu.parameter_id = p.id WHERE pu.user_id = :userId) x JOIN schedule s ON x.schedule_id = s.id WHERE s.first_day <= :date AND s.last_day >= :date",
          nativeQuery = true)
    List<Integer> getParameterByDate(@Param("date") String date, @Param("userId") int userId);

    @Query(value = "SELECT p FROM Parameter p WHERE p.name = :name AND p.isDefault = :isDefault")
    Parameter getByNameAndIsDefault(@Param("name") String name, @Param("isDefault") boolean isDefault);
}
