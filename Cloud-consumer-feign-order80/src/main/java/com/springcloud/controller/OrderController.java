package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private Service service;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return service.getPayment(id);
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return service.create(payment);
    }

    @GetMapping("/consumer/payment/timeOut")
    public CommonResult timeOut(){
        return service.timeOut();
    }

    @GetMapping("/consumer/payment/discovery")
    public Object discovery(){
        return service.discovery();
    }

    @GetMapping("consumer/payment/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id){
        return service.circuitBreaker(id);
    }



}
