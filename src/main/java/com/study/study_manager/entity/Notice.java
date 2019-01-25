package com.study.study_manager.entity;

import javax.persistence.Column;

public class Notice extends BaseEntity{
    @Column
    private String title;
    @Column
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
