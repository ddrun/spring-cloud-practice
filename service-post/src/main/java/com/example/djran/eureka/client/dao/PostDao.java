package com.example.djran.eureka.client.dao;

import com.example.djran.eureka.client.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created on 2018/12/28
 * post dao
 * @author d.djran@gmail.com
 */
@Repository
public interface PostDao extends JpaRepository<Posts,String> {
    Posts getPostsById(String id);
}
