package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
// @DefaultProperties(defaultFallback = "errorHandler")
public class OrderHiystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService ;

    // @HystrixCommand
    @GetMapping("/consumer/hystrix/ok/{id}")
    String paymentInfo(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo(id);
    }

    // @HystrixCommand
    @GetMapping("/consumer/hystrix/timeout/{id}")
    String timeOut(@PathVariable("id") Integer id){
        return paymentHystrixService.timeOut(id);
    }

    @GetMapping("consumer/payment/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id){
        return paymentHystrixService.circuitBreaker(id);
    }

    // public String  errorHandler(){
    //     return "消费异常：";
    // }
}
