package com.AfyaFlow.demo.service;

import com.AfyaFlow.demo.model.Role;
import com.AfyaFlow.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role createRole(Role role) {
        return repository.save(role);
    }

    public List<Role> getRoles() {
        return repository.findAll();
    }
}