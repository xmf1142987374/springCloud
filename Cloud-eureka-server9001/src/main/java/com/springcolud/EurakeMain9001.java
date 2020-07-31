package com.springcolud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurakeMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(EurakeMain9001.class, args);
    }
}
