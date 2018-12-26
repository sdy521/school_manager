package com.study.study_manager.dto;

import com.study.study_manager.core.jqGrid.JqGridParam;

public class TeacherParam extends JqGridParam {
    private Integer id;
    private String name;
    private Integer deleted;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
