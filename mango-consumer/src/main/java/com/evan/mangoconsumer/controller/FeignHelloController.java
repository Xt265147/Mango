package com.evan.mangoconsumer.controller;

import com.evan.mangoconsumer.service.MangoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignHelloController {
    @Autowired
    private MangoProducerService mangoProducerService;

    @RequestMapping("/feign/call")
    public String call() {
        // 使用Feign调用mango-consumer服务
        return mangoProducerService.hello();
    }
}
