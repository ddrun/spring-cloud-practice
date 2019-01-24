package com.example.djran.feign.service;

import com.example.djran.core.dto.DataRet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-post")
public interface FeignServicePost {
    @GetMapping("post")
    DataRet getPostFromServicePost(@PathVariable("id") String id);
}
