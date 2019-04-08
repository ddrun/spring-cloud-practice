package com.example.djran.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sentinel")
@Slf4j
public class SentinelTestController {

    @RequestMapping("hello")
    @SentinelResource("hello")
    public String hello(){
        return "hello sentinel";
    }
}
