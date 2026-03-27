package com.AfyaFlow.demo.dto;


import com.AfyaFlow.demo.model.Gender;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(min = 8, max = 72)
    private String password;

    @NotNull
    @Past
    private LocalDate dob;

    @NotNull
    private Gender gender;

    @NotBlank
    private String address;
}
