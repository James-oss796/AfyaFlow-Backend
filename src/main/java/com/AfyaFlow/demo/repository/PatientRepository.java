package com.AfyaFlow.demo.repository;

import com.AfyaFlow.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByUser_Email(String email);

    // Optional: find by patient code
    Optional<Patient> findByPatientCode(String patientCode);
}