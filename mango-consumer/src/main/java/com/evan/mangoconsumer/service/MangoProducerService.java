package com.evan.mangoconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mango-producer")
public interface MangoProducerService {
    @RequestMapping("/hello")
    public String hello();
}
