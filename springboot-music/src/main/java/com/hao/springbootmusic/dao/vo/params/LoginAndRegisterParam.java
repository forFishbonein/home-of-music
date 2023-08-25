/*
 * @Time : 2022/8/9 10:00
 * @Author : hao
 * @File : LoginAndRegisterParam.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.vo.params;

import lombok.Data;

@Data
public class LoginAndRegisterParam {

    private String account; //账号

    private String password; //密码

    private String nickname; //用户名：只有注册需要用到
}
