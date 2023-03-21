package com.spbu.healthapp.service;

import com.spbu.healthapp.dto.MedicineRequest;
import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.entity.MedicineUser;
import com.spbu.healthapp.entity.Schedule;
import com.spbu.healthapp.entity.User;
import com.spbu.healthapp.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository repository;

    @Autowired
    private MedicineUserService medicineUserService;

    @Autowired
    private IsCheckedService isCheckedService;

    public Medicine saveMedicine(MedicineRequest medicineRequest) {
        String timeToParse = medicineRequest.getStartDay() + " " + medicineRequest.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(timeToParse, formatter);
        LocalDate date = LocalDate.parse(medicineRequest.getStartDay());

        int duration = medicineRequest.getDuration();

        Medicine medicine = new Medicine();
        Schedule schedule = new Schedule(
                0, dateTime, date, date.plusDays(duration - 1), duration, medicine
        );

        medicine.setName(medicineRequest.getName());
        medicine.setUnits(medicineRequest.getUnits());
        medicine.setNumberPerUse(medicineRequest.getPerUse());
        medicine.setSchedule(schedule);

        return repository.save(medicine);
    }

    public List<Medicine> getMedicine() {
        return repository.findAll();
    }

    public Medicine getMedicineById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteMedicine(int id) {
        repository.deleteById(id);
        return "medicine removed: " + id;
    }

    public List<Medicine> getMedicineForUser(int userId) {
        List<MedicineUser> medicineUsers = medicineUserService.getMedicineForUser(userId);
        List<Medicine> medicine = new ArrayList<>();
        medicineUsers.forEach(medicineUser -> medicine.add(medicineUser.getMedicine()));
        return medicine;
    }

//    public List<Medicine> getMedicineByDateForUser(LocalDate date, int userId) {
//        return repository.getMedicineByDate(Datedate);
//    }


    public List<Medicine> getMedicineByDateForUser(String date, int userId) {
        List<Integer> ids = repository.getMedicineByDate(date, userId);
        List<Medicine> medicine = new ArrayList<>();
        ids.forEach(id -> {
            getMedicineById(id).setChecked(isCheckedService.getCheckedByDate(
                    id, date + " " + getMedicineById(id).getSchedule().getTime().toLocalTime()));
            medicine.add(getMedicineById(id));
        });
        return medicine;
     }

    public List<Medicine> getMedicineBeforeDateForUser(String date, int userId) {
        List<Integer> ids = repository.getMedicineBeforeDate(date, userId);
        List<Medicine> medicine = new ArrayList<>();
        ids.forEach(id -> medicine.add(getMedicineById(id)));
        return medicine;
    }

    public List<Medicine> getCurrentMedicineForUser(String date, int userId) {
        List<Integer> ids = repository.getCurrentMedicine(date, userId);
        List<Medicine> medicine = new ArrayList<>();
        ids.forEach(id -> medicine.add(getMedicineById(id)));
        return medicine;
    }
}
