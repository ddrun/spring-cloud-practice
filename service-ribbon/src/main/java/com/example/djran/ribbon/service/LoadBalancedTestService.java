package com.example.djran.ribbon.service;

import com.example.djran.core.dto.DataRet;

public interface LoadBalancedTestService {
    DataRet getPost(String id);
}
