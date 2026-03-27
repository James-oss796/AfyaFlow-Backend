package com.AfyaFlow.demo.mapper;

import com.AfyaFlow.demo.dto.PatientRequest;
import com.AfyaFlow.demo.dto.PatientResponse;
import com.AfyaFlow.demo.dto.RegisterRequest;
import com.AfyaFlow.demo.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

@Component
public class PatientMapper {

    public  Patient toEntity(RegisterRequest dto){
        Patient patient = new Patient();

        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setDob(dto.getDob());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setPatientCode(generatePatientCode());
        patient.setCreatedAt(LocalDateTime.now());


        return patient;
    }

    public Patient toEntity(PatientRequest dto) {
        Patient patient = new Patient();

        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setDob(dto.getDob());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setPatientCode(generatePatientCode());
        patient.setCreatedAt(LocalDateTime.now());

        return patient;
    }

    public static PatientResponse toResponse(Patient patient){
        PatientResponse dto = new PatientResponse();

        dto.setPatientId(patient.getPatientId());
        dto.setPatientCode(patient.getPatientCode());
        dto.setFullName(patient.getFirstName() + " " + patient.getLastName());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setGender(patient.getGender());
        dto.setAddress(patient.getAddress());
        dto.setCreatedAt(patient.getCreatedAt());

        if(patient.getDob() != null){
            int age = Period.between(patient.getDob(), LocalDate.now()).getYears();
            dto.setAge(age);
        }

        return dto;
    }

    private  String generatePatientCode(){
        return "AFYAFLOW_" + UUID.randomUUID().toString().substring(0,6).toUpperCase();
    }
}
