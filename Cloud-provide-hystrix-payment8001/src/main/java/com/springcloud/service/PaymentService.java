package com.springcloud.service;

public interface PaymentService {
    String paymentInfo(Integer id);
    String timeOut(Integer id);
    String paymentCircuitBreaker(Integer id);
}
