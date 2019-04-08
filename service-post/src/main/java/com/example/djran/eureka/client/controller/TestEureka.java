package com.example.djran.eureka.client.controller;

import com.example.djran.core.dto.DataRet;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestEureka {

    @Resource
    private EurekaClient eurekaClient;
    @RequestMapping("/home")
    public DataRet home(){
        List<Application> applications=eurekaClient.getApplications().getRegisteredApplications();
        return new DataRet(applications);
    }
    @RequestMapping(value = "apps")
    public DataRet apps(){
        List<Application> apps = eurekaClient.getApplications().getRegisteredApplications();
        Collections.sort(apps, new Comparator<Application>() {
            public int compare(Application l, Application r) {
                return l.getName().compareTo(r.getName());
            }
        });
        return new DataRet(apps);
    }
}
