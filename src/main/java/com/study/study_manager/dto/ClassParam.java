package com.study.study_manager.dto;

import com.study.study_manager.core.jqGrid.JqGridParam;

public class ClassParam extends JqGridParam {

    private String name;
    private Integer deleted;

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
