package com.AfyaFlow.demo.repository;

import com.AfyaFlow.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientPatientId(Long patientId);
}