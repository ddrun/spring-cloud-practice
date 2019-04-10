package com.example.djran.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigTestController {

    @Value("${gateway.config.test}")
    private String testConfig;
    @RequestMapping("/test")
    public String testConfig(){
        return this.testConfig;
    }
}
