package com.example.djran.eureka.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "微服务接口统一对象")
public class ApiRegRet {

    @ApiModelProperty(value = "服务大类编码")
    private String serviceTypeCodeParent;
    @ApiModelProperty(value = "服务大类名称")
    private String serviceTypeNameParent;

    @ApiModelProperty(value = "服务小类编码")
    private String serviceTypeCode;
    @ApiModelProperty(value = "服务小类名称")
    private String serviceTypeName;

    @ApiModelProperty(value = "服务url")
    private String apiUrl;
    @ApiModelProperty(value = "服务名称")
    private String apiName;
    @ApiModelProperty(value = "服务请求类型：post/get等")
    private String apiMethodType;

}
