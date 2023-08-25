/*
 * @Time : 2022/8/8 16:23
 * @Author : hao
 * @File : Result.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result { //自定义返回值数据集

    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    //成功的情况下：
    public static Result success(Object data) {

        return new Result(true,200,"success",data); //把数据封装成Result对象
    }
    //失败的情况下：
    public static Result fail(Integer code, String msg) {

        return new Result(false,code,msg,null);
    }
}
