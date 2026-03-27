package com.AfyaFlow.demo.dto;

import com.AfyaFlow.demo.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequest {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private LocalDate dob;


}
