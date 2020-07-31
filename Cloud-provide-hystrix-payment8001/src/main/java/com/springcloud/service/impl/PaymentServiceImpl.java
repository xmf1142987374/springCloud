package com.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    @HystrixCommand
    public String paymentInfo(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @Override
    @HystrixCommand(fallbackMethod = "timeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })
    public String timeOut(Integer id) {

        String time1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int timeNumber = 3;
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" +
                "O(∩_∩)O哈哈~  耗时(秒)" + timeNumber+"进入时间："+time1+"结束时间："+time2;
    }

    @Override
    @HystrixCommand(fallbackMethod ="paymentCircuitBreaker_fallback"
                        ,commandProperties = {
                            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
                         }
                    )
    public String paymentCircuitBreaker(Integer id) {
        if (id<0){
            throw new RuntimeException("id不能为负数");
        }
        String sn = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功，流水号："+sn;
    }

    public String timeOutHandler(Integer id){
        return "调用异常："+id;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，异常id";
    }
}
