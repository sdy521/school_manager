package com.study.study_manager.entity;

import javax.persistence.Column;

public class Info extends BaseEntity{
    @Column
    private Integer userid;
    @Column
    private Integer sex;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private Integer age;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
