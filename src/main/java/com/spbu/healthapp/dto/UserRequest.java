package com.spbu.healthapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    @Email(message = "invalid email address")
    private String email;
    @NotBlank(message = "name shouldn't be blank")
    private String name;
    @Pattern(regexp = "^.{6,}$", message = "password should be at least 6 characters")
    private String password;
}
