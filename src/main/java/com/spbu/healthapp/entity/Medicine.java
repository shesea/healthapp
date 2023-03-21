package com.spbu.healthapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine extends ScheduledEntity {
    @Id
    @GeneratedValue
    private int id;

    @Transient
    private String type = "medicine";

    @Transient
    private boolean isChecked = false;

    private String name;

    private String units;

    private int numberPerUse;

    @JsonIgnore
    @OneToMany(mappedBy = "medicine")
    private Set<MedicineUser> medicineUsers = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getNumberPerUse() {
        return numberPerUse;
    }

    public void setNumberPerUse(int numberPerUse) {
        this.numberPerUse = numberPerUse;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
