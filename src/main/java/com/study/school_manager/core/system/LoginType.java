package com.study.school_manager.core.system;

/**
 * @author sdy
 * @date 2019/5/15 10:24
 */
public enum LoginType {
    SUCCESS(0,"登陆成功"),LOGOUT(1,"登出成功"),BAD_USER_PASS(2,"用户名密码错误"),EXPIRED(3,"登陆状态失效"),DISABLED(4,"用户被禁用不可登陆");

    LoginType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    int code;
    String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
