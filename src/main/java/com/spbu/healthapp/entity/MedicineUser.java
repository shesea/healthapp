package com.spbu.healthapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "medicine_user")
public class MedicineUser {
    @EmbeddedId
    private MedicineUserId id;

    @ManyToOne
    @MapsId("medicineId")
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    public MedicineUser(Medicine medicine, User user) {
        this.id = new MedicineUserId(medicine.getId(), user.getId());
        this.medicine = medicine;
        this.user = user;
    }

}
