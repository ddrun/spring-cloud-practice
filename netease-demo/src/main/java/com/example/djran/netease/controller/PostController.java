package com.example.djran.netease.controller;

import com.example.djran.netease.dto.DataRet;
import com.example.djran.netease.model.Posts;
import com.example.djran.netease.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/12/28
 * post controller
 * @author d.djran@gmail.com
 */
@RestController
@Slf4j
@RequestMapping("post")
@Api("文章管理")
public class PostController {

    @Value("${server.port}")
    String port;

    @Autowired
    private PostService postService;
    @GetMapping(value = "/{id}")
    @ApiOperation("根据id获取文章详情")
    public DataRet getPostTestGet(@PathVariable("id") String id){
        Posts posts=postService.getPost(id);
        return new DataRet(port,posts.toString());
    }

    @RequestMapping(value = "applicationJson",method = {RequestMethod.GET,RequestMethod.POST})
    public DataRet jsonParams(@RequestBody(required = false) Posts param, HttpServletRequest request){
        log.info(param.getId());
        String age=request.getParameter("age");
        return new DataRet(param.getId()+" "+age);
    }
    @RequestMapping(value = "formdata",method = {RequestMethod.GET,RequestMethod.POST})
    public DataRet jsonParams(String routId,Posts param){
        log.info(param.getId());
        return new DataRet(param.getId());
    }
}
