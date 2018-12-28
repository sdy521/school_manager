package com.study.study_manager.entity;

import java.util.List;

public class LeftMenu {
    private String name;
    private String url;
    private String code;
    private String pcode;
    private List<LeftMenu> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public List<LeftMenu> getChildren() {
        return children;
    }

    public void setChildren(List<LeftMenu> children) {
        this.children = children;
    }
}
