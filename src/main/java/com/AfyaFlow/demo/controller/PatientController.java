package com.AfyaFlow.demo.controller;

import java.util.List;

import com.AfyaFlow.demo.dto.PatientRequest;
import com.AfyaFlow.demo.dto.PatientResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AfyaFlow.demo.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest request){
        return ResponseEntity.ok(patientService.createPatient(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request){
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }



}