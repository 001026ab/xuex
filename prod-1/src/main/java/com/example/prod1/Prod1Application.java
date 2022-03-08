package com.example.prod1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.example.**"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.example.**"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Prod1Application {

    public static void main(String[] args) {
        SpringApplication.run(Prod1Application.class, args);
    }

}
