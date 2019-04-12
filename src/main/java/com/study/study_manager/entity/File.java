package com.study.study_manager.entity;

import javax.persistence.Column;

/**
 * @author sdy
 * @date 2019/4/11 13:45
 */
public class File extends BaseEntity{

    @Column
    private String name;
    @Column
    private String path;
    @Column
    private String code;
    @Column
    private String pcode;
    @Column
    private String icon;
    @Column
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
