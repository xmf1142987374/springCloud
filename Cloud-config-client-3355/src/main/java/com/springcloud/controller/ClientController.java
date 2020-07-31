package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ClientController {
    @Value("${config.info}")
    private String configInfo;


    @Value("${eureka.client.service-url.defaultZone}")
    private String test;

    @Value("${config.sb}")
    private String sb;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo+"\t"+test+"\t"+sb;
    }

}
