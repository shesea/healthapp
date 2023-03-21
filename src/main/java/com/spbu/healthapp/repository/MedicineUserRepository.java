package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.entity.MedicineUser;
import com.spbu.healthapp.entity.MedicineUserId;
import com.spbu.healthapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineUserRepository extends JpaRepository<MedicineUser, MedicineUserId> {
    MedicineUser findByMedicineIdAndUserId(int medicineId, int userId);

    List<MedicineUser> findByUserId(int userId);
}
