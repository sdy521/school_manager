package com.study.study_manager.entity.security;
//角色表

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class SysRole{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

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
        this.name = name;
    }
}
