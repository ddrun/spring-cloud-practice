package com.example.djran.gateway.dao;

import com.example.djran.gateway.model.RouteDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDefineDao extends JpaRepository<RouteDefine,String>{
}
