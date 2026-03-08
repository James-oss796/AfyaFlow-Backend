package com.AfyaFlow.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AfyaFlow.demo.entity.TestConnection;


public interface TestConnectionRepo extends JpaRepository<TestConnection, Integer> {}