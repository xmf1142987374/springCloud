package com.springcloud.service;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 调用接口
 * */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface Service {

    @GetMapping("/payment/create")
     CommonResult<Payment> create(Payment payment); //插入新数据

    @GetMapping("/payment/get/{id}")
     CommonResult<Payment> getPayment(@PathVariable("id") Long id);   //查询数据

    @GetMapping(value ="/payment/discovery")
    Object discovery();   //服务发现

    @GetMapping("/payment/timeOut")
    CommonResult timeOut();   //超时测试

    @GetMapping("/payment/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id);//熔断测试
}
