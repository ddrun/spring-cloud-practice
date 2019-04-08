package com.example.djran.gateway.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "GW_ROUTE_DEFINE")
@ApiModel("文章详情")
public class RouteDefine {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(value = "主键")
    @Column(name = "ID")
    private String id;
    /**
     * 路由id
     */
    @ApiModelProperty(value = "路由id")
    @Column(name = "ROUTE_ID")
    private String routeId;

    @Column(name = "PREDICATES")
    private String predicates;

    @Column(name = "FILTERS")
    private String filters;

    @Column(name = "CREATE_TIME")
    private String createTime;

    @Column(name = "UPDATE_TIME")
    private String updateTime;

    @Column(name = "DEL_FLAG")
    private String delFlag;

    @Column(name = "URI")
    private String uri;
}
