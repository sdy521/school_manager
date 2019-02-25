package com.study.study_manager.entity;

import javax.persistence.Column;

public class Class extends BaseEntity{

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
