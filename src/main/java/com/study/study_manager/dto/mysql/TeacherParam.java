package com.study.study_manager.dto.mysql;

import com.study.study_manager.core.jqGrid.JqGridParam;

public class TeacherParam extends JqGridParam {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
