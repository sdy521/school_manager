package com.study.util;

public enum TestEnum {
    SECRECY(1, "保密"), SINGLE(2, "单身"), MARRIED(3, "已婚");
    int code;
    String value;

    TestEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
