package com.example.djran.netease.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class BaseModel implements Serializable{
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
