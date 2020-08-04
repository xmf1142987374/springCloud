package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {
    @Value("${server.port}")
    private String Port;

    @GetMapping("/payment/serverPort")
    public String serverPort(){
        return Port;
    }
}
