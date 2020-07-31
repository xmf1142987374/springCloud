package com.springcloud.service;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {
    public int create(Payment payment);//创建流水
    public Payment getPaymentById(@Param("id") Long id);//根据id查找流水
}
