package com.study.study_manager.entity;

import javax.persistence.Column;

public class Class extends BaseEntity{

    @Column
    private String name;
    @Column
    private Integer createUser;
    @Column
    private Integer updateUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
