package com.AfyaFlow.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AfyaFlow.demo.model.Department;
import com.AfyaFlow.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service = service;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department){
        return service.createDepartment(department);
    }

    @GetMapping
    public List<Department> getDepartments(){
        return service.getDepartments();
    }

}