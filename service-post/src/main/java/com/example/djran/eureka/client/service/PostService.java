package com.example.djran.eureka.client.service;

import com.example.djran.eureka.client.model.Posts;
/**
 * Created on 2018/12/28
 * post interface
 * @author d.djran@gmail.com
 */
public interface PostService {
    Posts getPost(String id);
}
