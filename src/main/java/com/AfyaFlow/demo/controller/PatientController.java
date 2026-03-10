package com.AfyaFlow.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AfyaFlow.demo.model.Patient;
import com.AfyaFlow.demo.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        // Basic validation
        if (patient.getName() == null || patient.getPhone() == null) {
            return ResponseEntity.badRequest().body("Name and phone are required");
        }

        Patient saved = service.registerPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Patient> getAll(){
        return service.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Patient patient = service.getPatient(id);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/patients/{id}")
    public void delete(@PathVariable Long id){
        service.deletePatient(id);
    }
}