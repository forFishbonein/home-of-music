/*
 * @Time : 2022/8/9 10:15
 * @Author : hao
 * @File : ErrorCode.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.vo;

//枚举错误码常数
public enum ErrorCode {
    //相当于是new好的对象
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    TOKEN_ERROR(10003,"token不合法"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    ACCOUNT_EXIST(10004,"账号已存在");

    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

