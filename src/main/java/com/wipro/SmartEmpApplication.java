package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SmartEmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartEmpApplication.class, args);
    }
}
