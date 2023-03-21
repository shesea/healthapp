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
public class ValuePressure extends ValueEntity {
    @Id
    @GeneratedValue
    private int id;

    private int systolicValue;
    private int diastolicValue;
    private int pulseValue;

    private LocalDateTime checkedAt;

    public int getSystolicValue() {
        return systolicValue;
    }

    public void setSystolicValue(int systolicValue) {
        this.systolicValue = systolicValue;
    }

    public int getDiastolicValue() {
        return diastolicValue;
    }

    public void setDiastolicValue(int diastolicValue) {
        this.diastolicValue = diastolicValue;
    }

    public int getPulseValue() {
        return pulseValue;
    }

    public void setPulseValue(int pulseValue) {
        this.pulseValue = pulseValue;
    }
}
