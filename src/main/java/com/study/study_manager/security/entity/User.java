package com.study.study_manager.security.entity;

import com.study.study_manager.entity.mysql.BaseEntity;

import javax.persistence.Column;

public class User extends BaseEntity {

    @Column
    private String name;
    @Column
    private String password;
    /***
     * 0:超级管理员1:老师2:学生
     */
    @Column
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
