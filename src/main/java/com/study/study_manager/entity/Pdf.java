package com.study.study_manager.entity;

import javax.persistence.Column;

/**
 * @author sdy
 * @date 2019/4/30 14:27
 */
public class Pdf extends BaseEntity {
    @Column
    private String name;
    @Column
    private String path;

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
}
