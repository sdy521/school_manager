package com.study.util;

public enum TestEnum {
    SECRECY(1, "测试1"), SINGLE(2, "测试2"), MARRIED(3, "测试3");
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
