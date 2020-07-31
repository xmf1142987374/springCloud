package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String SERVERPORT;

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo(id);
        log.info("*****result:" + result);
        return result;
    }

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String timeOut(@PathVariable("id") Integer id) {
        String result = paymentService.timeOut(id);
        log.info("*****result:" + result);
        return result;

    }


    /**
     * 熔断测试
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/circuit/{id}")
    public String circuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*****result:" + result);
        return result;

    }

}
