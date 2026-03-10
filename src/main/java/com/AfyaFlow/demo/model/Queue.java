package com.AfyaFlow.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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
    private Doctor doctor;

    @ManyToOne
    private Department department;

}