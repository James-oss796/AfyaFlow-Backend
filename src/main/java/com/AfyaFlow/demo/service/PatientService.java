package com.AfyaFlow.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AfyaFlow.demo.model.Patient;
import com.AfyaFlow.demo.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
      public Patient getPatient(Long id){
        return patientRepository.findById(id).orElse(null);
    }

    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }

}