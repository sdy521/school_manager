package com.study.study_manager.entity;

import javax.persistence.Column;

public class Menu extends BaseEntity{

    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String url;
    @Column
    private String pcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
}
