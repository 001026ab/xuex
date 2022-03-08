package com.example.gateway11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Gateway11Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway11Application.class, args);
    }

}
