package com.AfyaFlow.demo.service;


import com.AfyaFlow.demo.dto.PatientRequest;
import com.AfyaFlow.demo.dto.PatientResponse;
import com.AfyaFlow.demo.mapper.PatientMapper;
import com.AfyaFlow.demo.model.Patient;
import com.AfyaFlow.demo.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }


    @Override
    public PatientResponse createPatient(PatientRequest request) {
        Patient patient = patientMapper.toEntity(request);
        Patient saved = patientRepository.save(patient);
        return PatientMapper.toResponse(saved);
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return PatientMapper.toResponse(patient);
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePatient(Long id) {
        if(!patientRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        patientRepository.deleteById(id);
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        Patient exists = patientRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        if(request.getFirstName() != null){
            exists.setFirstName(request.getFirstName());
        }

        if(request.getLastName() != null){
            exists.setLastName(request.getLastName());
        }

        if(request.getPhoneNumber() != null){
            exists.setPhoneNumber(request.getPhoneNumber());
        }

        if(request.getGender() != null){
            exists.setGender(request.getGender());
        }

        if(request.getDob() != null){
            exists.setDob(request.getDob());
        }

        if(request.getAddress() != null){
            exists.setAddress(request.getAddress());
        }

        Patient updated = patientRepository.save(exists);


        return PatientMapper.toResponse(updated);
    }
}
