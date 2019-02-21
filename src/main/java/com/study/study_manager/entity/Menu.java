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
    /***
     * 1:老师2:学生
     */
    @Column
    private Integer type;
    @Column
    private String icon;
    /***
     * 显示顺序
     */
    @Column
    private Integer sort;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
