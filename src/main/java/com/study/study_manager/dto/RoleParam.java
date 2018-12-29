package com.study.study_manager.dto;

import com.study.study_manager.core.jqGrid.JqGridParam;

public class RoleParam extends JqGridParam {
    private String name;
    private Integer userid;
    private Integer roleid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
