package com.springcloud.service;

import com.springcloud.service.impl.PaymentFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value ="cloud-provider-hystrix-payment",fallback = PaymentFallBackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String timeOut(@PathVariable("id") Integer id);

    @GetMapping("/payment/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id);//熔断测试
}
