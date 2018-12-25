package com.study.study_manager.entity.mysql;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "teacher")
public class Teacher extends BaseEntity{
    @Column
    private String name;
    @Column
    private String password;

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
}
