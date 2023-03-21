package com.spbu.healthapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValueTemperature extends ValueEntity {
    @Id
    @GeneratedValue
    private int id;

    private int integerPart;
    private int fractionalPart;

    private LocalDateTime checkedAt;

    public int getIntegerPart() {
        return integerPart;
    }

    public void setIntegerPart(int integerPart) {
        this.integerPart = integerPart;
    }

    public int getFractionalPart() {
        return fractionalPart;
    }

    public void setFractionalPart(int fractionalPart) {
        this.fractionalPart = fractionalPart;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
