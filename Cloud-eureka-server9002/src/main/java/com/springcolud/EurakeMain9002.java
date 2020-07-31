package com.springcolud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurakeMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(EurakeMain9002.class, args);
    }
}
