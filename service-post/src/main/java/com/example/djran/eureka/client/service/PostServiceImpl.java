package com.example.djran.eureka.client.service;

import com.example.djran.eureka.client.dao.PostDao;
import com.example.djran.eureka.client.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created on 2018/12/28
 * post impl
 * @author d.djran@gmail.com
 */
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostDao postDao;
    @Override
    public Posts getPost(String id) {
        return postDao.getPostsById(id);
    }
}
