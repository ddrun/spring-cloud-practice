package com.example.djran.ribbon.service;

import com.example.djran.core.dto.DataRet;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadBalancedTestServiceImpl implements LoadBalancedTestService{
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getPostError")
    @Override
    public DataRet getPost(String id) {
        return restTemplate.getForObject("http://SERVICE-POST/post/"+id,DataRet.class);
    }
    public DataRet getPostError(String id) {
        return new DataRet("SERVICE-POST-ERROR","服务调用失败");
    }
}
