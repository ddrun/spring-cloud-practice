package com.example.djran.gateway.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dengjr
 * @function
 * @time 2018/8/15
 */
@Data
@Entity
@Table(name = "T_DEVELOPER")
public class Developer implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private String id;
    /**
     * 开发者账号
     */
    @Column(name = "APP_ID")
    private String appId;
    /**
     * 开发者账号密码
     */
    @Column(name = "APP_SECRET")
    private String appSecret;
    /**
     * 创建日期
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;
    /**
     * Y可用，N不可用
     */
    @Column(name = "STS")
    private String sts;
    /**
     * 开发者名称
     */
    @Column(name = "APP_NAME")
    private String appName;
    /**
     * 接口启用时间
     */
    @Column(name = "START_DATE")
    private Date startDate;
    /**
     * 接口失效时间
     */
    @Column(name = "END_DATE")
    private Date endDate;
    /**
     * null
     */
    @Column(name = "PARENT_DEVELOPER_ID")
    private String parentDeveloperId;
    /**
     * app 其他业务系统等
     */
    @Column(name = "CHANNEL_TYPE")
    private String channelType;
    /**
     * 渠道名称
     */
    @Column(name = "CHANNEL_NAME")
    private String channelName;
    /**
     * IP地址
     */
    @Column(name = "IP")
    private String ip;
    /**
     * 联系人
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 联系人电话
     */
    @Column(name = "PHONE")
    private String phone;
    /**
     * 排序序号
     */
    @Column(name = "ORDER_NUM")
    private String orderNum;
}
