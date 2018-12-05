package com.study.study_manager.entity;
//数据创建成功之后，在客户端请求网页的时候我们需要有一个实体类用来向客户端传递消息
public class Msg {
    private String title;
    private String content;
    private String extraInfo;

    public Msg() {
    }

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

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
    public Msg(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }
}
