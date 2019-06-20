package com.example.djran.eureka.client.controller;

import com.example.djran.eureka.client.dto.ApiRegRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MappingCDDontroller {
    @Autowired
    WebApplicationContext applicationContext;
    @PostMapping("api/register")
    public String apiRegister(@RequestBody ApiRegRet apiRegRet, HttpServletRequest request) {
        String serverName=request.getServerName();
        int serverPort=request.getServerPort();
        String addr=request.getRemoteAddr();
        String host=request.getRemoteHost();
        String localAddr=request.getLocalAddr();
        String contextPath=request.getContextPath();
        String servletPath=request.getServletPath();
        String protocol=request.getProtocol();
        String uri= request.getRequestURI();
        System.out.println(apiRegRet.getServiceTypeCodeParent()
                +" "+apiRegRet.getApiUrl()+" "+apiRegRet.getServiceTypeCode());
        return null;
    }

    @RequestMapping(value = "v1/getAllUrl", method = RequestMethod.POST)
    public Object getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, Object> map1 = new HashMap<>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }
            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            map1.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }
            list.add(map1);
        }
        return list;
    }
}
