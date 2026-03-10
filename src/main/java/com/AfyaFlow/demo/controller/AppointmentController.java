package com.AfyaFlow.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AfyaFlow.demo.model.Appointment;
import com.AfyaFlow.demo.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service){
        this.service = service;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return service.createAppointment(appointment);
    }

    @GetMapping
    public List<Appointment> getAppointments(){
        return service.getAppointments();
    }
}