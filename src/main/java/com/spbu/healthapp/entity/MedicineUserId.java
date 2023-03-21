package com.spbu.healthapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MedicineUserId implements Serializable {
    @Column(name = "medicine_id")
    private int medicineId;

    @Column(name = "user_id")
    private int userId;

}
