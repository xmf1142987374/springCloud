package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Controller {
    @Value("${serverUrl}")
    private String HTTPURL;

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/serverPort")
    public String serverPort(){
        return restTemplate.getForObject(HTTPURL, String.class);
    }
}
