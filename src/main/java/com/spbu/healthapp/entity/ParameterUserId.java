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
public class ParameterUserId implements Serializable {
    @Column(name = "parameter_id")
    private int parameterId;

    @Column(name = "user_id")
    private int userId;

}
