package com.example.djran.ribbon.controller;

import com.example.djran.core.dto.DataRet;
import com.example.djran.ribbon.service.LoadBalancedTestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ribbon")
@Api("ribbon调用及负载测试")
public class RibbonController {
    @Autowired
    private LoadBalancedTestService loadBalancedTestService;
    @GetMapping("/post/{id}")
    public DataRet ribbonTest(@PathVariable("id") String id){
        return loadBalancedTestService.getPost(id);
    }
}
