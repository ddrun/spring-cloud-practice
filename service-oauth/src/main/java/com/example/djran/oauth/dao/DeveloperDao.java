package com.example.djran.oauth.dao;


import com.example.djran.oauth.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dengjr
 * @function
 * @time 2018/8/15
 */
@Repository
public interface DeveloperDao extends JpaRepository<Developer,String> {

    Developer findDeveloperByAppId(String appId);
}
