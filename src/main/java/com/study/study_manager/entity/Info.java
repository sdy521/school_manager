package com.study.study_manager.entity;

import javax.persistence.Column;

public class Info extends BaseEntity{
    @Column
    private Integer userid;
    @Column
    private String sex;
    @Column
    private Integer type;
    @Column
    private String address;
    @Column
    private String phone;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
