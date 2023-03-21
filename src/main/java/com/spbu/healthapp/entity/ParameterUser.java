package com.spbu.healthapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parameter_user")
public class ParameterUser {
    @EmbeddedId
    private ParameterUserId id;

    @ManyToOne
    @MapsId("parameterId")
    @JoinColumn(name = "parameter_id")
    private Parameter parameter;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    public ParameterUser(Parameter parameter, User user) {
        this.id = new ParameterUserId(parameter.getId(), user.getId());
        this.parameter = parameter;
        this.user = user;
    }
}