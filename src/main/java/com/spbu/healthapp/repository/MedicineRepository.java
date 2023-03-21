package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    @Query(value = "SELECT medicine_id FROM (" +
            "SELECT * FROM medicine_user mu " +
            "JOIN medicine m ON mu.medicine_id = m.id " +
            "WHERE mu.user_id = :userId) x " +
            "JOIN schedule s ON x.schedule_id = s.id " +
            "WHERE s.first_day <= :date AND s.last_day >= :date",
            nativeQuery = true)
    List<Integer> getMedicineByDate(@Param("date") String date, @Param("userId") int userId);

    @Query(value = "SELECT medicine_id FROM (" +
            "SELECT * FROM medicine_user mu " +
            "JOIN medicine m ON mu.medicine_id = m.id " +
            "WHERE mu.user_id = :userId) x " +
            "JOIN schedule s ON x.schedule_id = s.id " +
            "WHERE s.last_day < :date",
            nativeQuery = true)
    List<Integer> getMedicineBeforeDate(@Param("date") String date, @Param("userId") int userId);

    @Query(value = "SELECT medicine_id FROM (" +
            "SELECT * FROM medicine_user mu " +
            "JOIN medicine m ON mu.medicine_id = m.id " +
            "WHERE mu.user_id = :userId) x " +
            "JOIN schedule s ON x.schedule_id = s.id " +
            "WHERE s.last_day >= :date",
            nativeQuery = true)
    List<Integer> getCurrentMedicine(@Param("date") String date, @Param("userId") int userId);

}
