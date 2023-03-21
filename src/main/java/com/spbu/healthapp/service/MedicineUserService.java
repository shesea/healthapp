package com.spbu.healthapp.service;

import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.entity.MedicineUser;
import com.spbu.healthapp.entity.MedicineUserId;
import com.spbu.healthapp.entity.User;
import com.spbu.healthapp.repository.MedicineUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineUserService {
    @Autowired
    private MedicineUserRepository repository;

    public MedicineUser saveMedicineUser(Medicine medicine, User user) {
        MedicineUser medicineUser = new MedicineUser(medicine, user);
        return repository.save(medicineUser);
    }

    public List<MedicineUser> getMedicineUser() {
        return repository.findAll();
    }

    public List<MedicineUser> getMedicineForUser(int userId) {
        return repository.findByUserId(userId);
    }

    public MedicineUser getMedicineUserById(int medicineId, int userId) {
        return repository.findByMedicineIdAndUserId(medicineId, userId);
    }

    public void deleteMedicineUser(MedicineUserId id) {
        repository.deleteById(id);
    }
}
