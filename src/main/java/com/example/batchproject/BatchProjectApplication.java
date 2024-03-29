package com.example.batchproject;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchProjectApplication.class, args);
    }

}
