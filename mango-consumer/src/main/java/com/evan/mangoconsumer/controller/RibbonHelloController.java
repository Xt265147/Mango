package com.evan.mangoconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonHelloController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon/call")
    public String call() {
        // 使用RestTemplate调用mango-consumer服务
        //LoadBalancerClient会自动根据服务名进行负载均衡，并返回一个可用的URL
        String callServiceResult = restTemplate.getForObject("http://mango-producer/hello", String.class);
        return callServiceResult;
    }
}
