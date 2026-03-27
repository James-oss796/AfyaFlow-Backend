package com.AfyaFlow.demo.dto;

import com.AfyaFlow.demo.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientResponse {
    private Long patientId;
    private String patientCode;
    private String fullName;
    private String phoneNumber;
    private int age;
    private String address;
    private Gender gender;
    private LocalDateTime createdAt;

}
