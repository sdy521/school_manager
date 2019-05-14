package com.study.school_manager.dto;

import com.study.school_manager.core.jqGrid.JqGridParam;

public class InfoParam extends JqGridParam {
    //1 老师 2 学生
    private Integer type;

    private String name;
    private Integer sex;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
