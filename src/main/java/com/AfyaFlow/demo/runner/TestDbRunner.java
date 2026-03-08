package com.AfyaFlow.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.AfyaFlow.demo.entity.TestConnection;
import com.AfyaFlow.demo.repository.TestConnectionRepo;

@Component
public class TestDbRunner implements CommandLineRunner {

    @Autowired
    private TestConnectionRepo repo;

    @Override
    public void run(String... args) throws Exception {
        TestConnection t = new TestConnection();
        t.setMessage("Database is working!");
        repo.save(t);

        System.out.println("Saved test message: " + repo.findAll().get(0).getMessage());
    }
}