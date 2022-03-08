package com.example.prod12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.example.**"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.example.**"})
@SpringBootApplication
public class Prod12Application {

    public static void main(String[] args) {
        SpringApplication.run(Prod12Application.class, args);
    }

}
