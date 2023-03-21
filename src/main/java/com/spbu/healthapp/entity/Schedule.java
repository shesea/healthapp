package com.spbu.healthapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private int id;

    private LocalDateTime time;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private int durationInDays;

    @JsonIgnore
    @OneToOne(mappedBy = "schedule")
    private ScheduledEntity entity;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
