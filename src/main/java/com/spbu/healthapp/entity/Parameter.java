package com.spbu.healthapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parameter extends ScheduledEntity {
    @Id
    @GeneratedValue
    private int id;

    @Transient
    private String type = "symptom";

    @Transient
    private boolean isChecked = false;
    private String name;
    private boolean isDefault;

    @JsonIgnore
    @OneToMany(mappedBy = "parameter")
    private Set<ParameterUser> parameterUsers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "parameter")
    private List<ValueEntity> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
