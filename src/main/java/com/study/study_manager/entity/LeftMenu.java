package com.study.study_manager.entity;

import java.util.List;

public class LeftMenu {
    private String name;
    private String url;
    private String code;
    private String pcode;
    private String icon;
    private Boolean active;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<LeftMenu> getChildren() {
        return children;
    }

    public void setChildren(List<LeftMenu> children) {
        this.children = children;
    }
}
