package com.study.study_manager.dto;

import com.study.study_manager.core.jqGrid.JqGridParam;

public class InfoParam extends JqGridParam {
    //1 老师 2 学生
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
