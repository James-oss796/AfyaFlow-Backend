package com.AfyaFlow.demo.repository;

import com.AfyaFlow.demo.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    List<Queue> findByDepartmentId(Long departmentId);
    List<Queue> findByDoctorId(Long doctorId);
}