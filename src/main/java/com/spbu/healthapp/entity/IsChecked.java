package com.spbu.healthapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IsChecked {
    @Id
    @GeneratedValue
    private int id;

    private boolean isChecked;
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private ScheduledEntity scheduledEntity;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
