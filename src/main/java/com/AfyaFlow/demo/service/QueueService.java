package com.AfyaFlow.demo.service;

import com.AfyaFlow.demo.model.Queue;
import com.AfyaFlow.demo.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    public static final String STATUS_WAITING = "waiting";
    public static final String STATUS_CALLED = "called";
    public static final String STATUS_COMPLETED = "completed";
    public static final String STATUS_MISSED = "missed";

    private final QueueRepository repository;

    public QueueService(QueueRepository repository) {
        this.repository = repository;
    }

    public Queue addToQueue(Queue queue) {
        return repository.save(queue);
    }

    public List<Queue> getQueue() {
        return repository.findAll();
    }

    public List<Queue> getQueueByDoctor(Long doctorId) {
        return repository.findByDoctorId(doctorId);
    }

    public Queue callQueue(Long queueId) {
        return updateStatus(queueId, STATUS_WAITING, STATUS_CALLED);
    }

    public Queue completeQueue(Long queueId) {
        return updateStatus(queueId, STATUS_CALLED, STATUS_COMPLETED);
    }

    public Queue missedQueue(Long queueId) {
        return updateStatus(queueId, STATUS_CALLED, STATUS_MISSED);
    }

    private Queue updateStatus(Long queueId, String expectedCurrentStatus, String newStatus) {
        Queue queue = repository.findById(queueId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Queue item not found"
                ));

        String current = queue.getStatus();
        if (current == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.CONFLICT,
                    "Queue item has no status"
            );
        }

        if (!expectedCurrentStatus.equals(current)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.CONFLICT,
                    "Invalid status transition. Current: " + current
            );
        }

        queue.setStatus(newStatus);
        return repository.save(queue);
    }
}