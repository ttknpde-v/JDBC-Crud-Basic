package com.java.api.restfulsapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.java.api.modules"})
public class RestfulsapplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulsapplicationApplication.class, args);
    }

}
