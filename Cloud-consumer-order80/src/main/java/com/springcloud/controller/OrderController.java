package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    public static final  String HTTPURL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create( Payment payment){
        return restTemplate.postForObject(HTTPURL+"/payment/create", payment, CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(HTTPURL+"/payment/get/"+id, CommonResult.class);
    }

}
