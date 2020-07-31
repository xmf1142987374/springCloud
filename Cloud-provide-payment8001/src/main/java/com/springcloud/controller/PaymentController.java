package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    /*
     * 服务信息发现
     * */
    @Resource
    private DiscoveryClient discoveryClient;
    /*
    * 负载均衡
    * */
    @Value("${server.port}")
    private String SERVERPORT;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody  Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果"+result);
        if (result>0){
            return  new CommonResult(200,"插入数据成功"+"serverPort:"+SERVERPORT,payment);
        }else {
            return  new CommonResult(444,"插入数据失败"+"serverPort:"+SERVERPORT,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果"+payment);
        if (payment!=null){
            return  new CommonResult(200,"查询数据成功"+"serverPort:"+SERVERPORT,payment);
        }else {
            return  new CommonResult(444,"查询数据失败，查询ID："+id+"serverPort:"+SERVERPORT,null);
        }
    }
    /*
     * 服务信息发现
     * */
    @GetMapping(value ="/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******Service:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getHost()+"\t"+instance.getUri()+"\t"+instance.getPort()+"\t"+instance.getInstanceId());
        }
        return this.discoveryClient;
    }

    /*
     * 超时测试
     * */
    @GetMapping(value ="/payment/timeOut")
    public CommonResult timeOut(){
        long time = 3000;

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new CommonResult(200,"SUCCESS","time_out:"+(time/1000)+"s");
    }
}
