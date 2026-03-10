package com.AfyaFlow.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
    public Queue addToQueue(@RequestBody Queue queue){
        return service.addToQueue(queue);
    }

    @GetMapping
    public List<Queue> getQueue(){
        return service.getQueue();
    }

}