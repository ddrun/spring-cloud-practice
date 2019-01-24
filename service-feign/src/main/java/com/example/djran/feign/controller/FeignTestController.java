package com.example.djran.feign.controller;

import com.example.djran.core.dto.DataRet;
import com.example.djran.feign.service.FeignServicePost;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Feign调用测试")
public class FeignTestController {
    @Autowired
    private FeignServicePost feignServicePost;
    @GetMapping(value = "/post/{id}")
    public DataRet getPost(@PathVariable("id") String id){
      return feignServicePost.getPostFromServicePost(id);
    }
}
