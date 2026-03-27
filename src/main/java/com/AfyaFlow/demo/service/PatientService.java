package com.AfyaFlow.demo.service;

import com.AfyaFlow.demo.dto.PatientRequest;
import com.AfyaFlow.demo.dto.PatientResponse;

import java.util.List;

public interface PatientService {

    PatientResponse createPatient(PatientRequest request);

    PatientResponse getPatientById(Long id);

    List<PatientResponse> getAllPatients();

    void deletePatient(Long id);

    PatientResponse updatePatient(Long id, PatientRequest request);
}