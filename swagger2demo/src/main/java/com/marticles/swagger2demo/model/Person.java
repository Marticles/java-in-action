package com.marticles.swagger2demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Person Model")
public class Person {
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    @ApiModelProperty(value = "客户姓名")
    private String name;
    @ApiModelProperty(value = "生日，返回的时候用yyyy-MM-dd格式")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}