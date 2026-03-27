package com.AfyaFlow.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AfyaFlow.demo.model.Queue;
import com.AfyaFlow.demo.service.QueueService;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService service;

    public QueueController(QueueService service){
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('RECEPTIONIST','ADMIN')")
    public Queue addToQueue(@RequestBody Queue queue){
        return service.addToQueue(queue);
    }

    @GetMapping
    public List<Queue> getQueue(){
        return service.getQueue();
    }

    @PatchMapping("/{id}/call")
    @PreAuthorize("hasAnyRole('DOCTOR','RECEPTIONIST','ADMIN')")
    public Queue callPatient(@PathVariable Long id) {
        return service.callQueue(id);
    }

    @PatchMapping("/{id}/complete")
    @PreAuthorize("hasAnyRole('DOCTOR','RECEPTIONIST','ADMIN')")
    public Queue completePatient(@PathVariable Long id) {
        return service.completeQueue(id);
    }

    @PatchMapping("/{id}/missed")
    @PreAuthorize("hasAnyRole('DOCTOR','RECEPTIONIST','ADMIN')")
    public Queue missedPatient(@PathVariable Long id) {
        return service.missedQueue(id);
    }

}