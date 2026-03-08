package com.AfyaFlow.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer queueNumber;

    private LocalDate queueDate;

    private String status;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Department department;

}