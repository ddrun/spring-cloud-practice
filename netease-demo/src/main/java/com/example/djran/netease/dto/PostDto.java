package com.example.djran.netease.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("文章vo")
@Data
public class PostDto implements Serializable{
    @ApiModelProperty("文档id")
    private String id;

    @ApiModelProperty("文档标题")
    private String title;
}
