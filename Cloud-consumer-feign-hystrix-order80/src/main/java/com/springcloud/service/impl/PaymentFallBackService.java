package com.springcloud.service.impl;

import com.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService {
    @Override
    public String paymentInfo(Integer id) {
        return "paymentInfo:error";
    }

    @Override
    public String timeOut(Integer id) {
        return  "timeOut:error";
    }

    @Override
    public String circuitBreaker(Integer id) {
        return "circuitBreaker:error";
    }
}
