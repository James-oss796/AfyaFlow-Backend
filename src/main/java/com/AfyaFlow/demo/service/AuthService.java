package com.AfyaFlow.demo.service;

import com.AfyaFlow.demo.dto.*;
import com.AfyaFlow.demo.mapper.PatientMapper;
import com.AfyaFlow.demo.model.Patient;
import com.AfyaFlow.demo.model.Role;
import com.AfyaFlow.demo.model.User;
import com.AfyaFlow.demo.repository.PatientRepository;
import com.AfyaFlow.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import com.AfyaFlow.demo.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request){
        // Create patient user
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.PATIENT);

        User savedUser = userRepository.save(user);

        //2. create patient
        Patient patient = patientMapper.toEntity(request);
        patient.setUser(savedUser);

        patientRepository.save(patient);

        String token = jwtService.generateAccessToken(
                savedUser.getEmail(),
                savedUser.getRole().name(),
                savedUser.getUserId()
        );

        return new AuthResponse(savedUser.getUserId(), savedUser.getRole().name(), token);
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        String token = jwtService.generateAccessToken(
                user.getEmail(),
                user.getRole().name(),
                user.getUserId()
        );

        return new AuthResponse(user.getUserId(), user.getRole().name(), token);
    }

}