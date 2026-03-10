package com.AfyaFlow.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AfyaFlow.demo.model.Department;
import com.AfyaFlow.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department createDepartment(Department department) {
        return repository.save(department);
    }

    public List<Department> getDepartments() {
        return repository.findAll();
    }

    public Department getDepartment(Long id) {
        return repository.findById(id).orElse(null);
    }
}