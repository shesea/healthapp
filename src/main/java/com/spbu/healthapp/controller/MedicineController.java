package com.spbu.healthapp.controller;

import com.spbu.healthapp.dto.MedicineRequest;
import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.exception.UserNotFoundException;
import com.spbu.healthapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineUserService medicineUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private IsCheckedService isCheckedService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/medicine/add/{userId}")
    public ResponseEntity<Medicine> addMedicine(@RequestBody MedicineRequest medicineRequest, @PathVariable int userId) throws UserNotFoundException {
        Medicine medicineAdded = medicineService.saveMedicine(medicineRequest);
        medicineUserService.saveMedicineUser(medicineAdded, userService.getUserById(userId));
        return new ResponseEntity<>(medicineAdded, HttpStatus.CREATED);
    }

    @GetMapping("/medicine/getList/{userId}")
    public List<Medicine> getAllMedicineForUser(@PathVariable int userId) {
        return medicineService.getMedicineForUser(userId);
    }

    @GetMapping("/medicine/getByDate/{userId}&{date}")
    public List<Medicine> getMedicineByDate(@PathVariable int userId, @PathVariable String date) {
        return medicineService.getMedicineByDateForUser(date, userId);
    }

    @GetMapping("/medicine/getCurrent/{userId}&{date}")
    public List<Medicine> getCurrentMedicine(@PathVariable int userId, @PathVariable String date) {
        List<Medicine> arr = medicineService.getCurrentMedicineForUser(date, userId);
        Comparator<Medicine> comparator = Comparator.comparing(s -> s.getSchedule().getFirstDay());
        comparator = comparator.thenComparing(s -> s.getSchedule().getTime());
        comparator = comparator.thenComparing(s -> s.getSchedule().getLastDay());

        arr.sort(comparator);
        return arr;
    }

    @GetMapping("/medicine/getArchive/{userId}&{date}")
    public List<Medicine> getArchiveMedicine(@PathVariable int userId, @PathVariable String date) {

        List<Medicine> arr = medicineService.getMedicineBeforeDateForUser(date, userId);
        Comparator<Medicine> comparator = Comparator.comparing(s -> s.getSchedule().getFirstDay());
        comparator = comparator.thenComparing(s -> s.getSchedule().getLastDay());
        comparator = comparator.thenComparing(s -> s.getSchedule().getTime());

        arr.sort(comparator);
        return arr;
    }

    @PostMapping("/medicine/check/{medicineId}&{date}")
    public void check(@PathVariable int medicineId, @PathVariable String date) {
        isCheckedService.check(medicineService.getMedicineById(medicineId), date);
    }

    /*@PostMapping("/medicine/uncheck/{medicineId}&{date}")
    public void uncheck(@PathVariable int medicineId, @PathVariable String date) {
        isCheckedService.uncheck(medicineService.getMedicineById(medicineId), date);
    }*/



}
