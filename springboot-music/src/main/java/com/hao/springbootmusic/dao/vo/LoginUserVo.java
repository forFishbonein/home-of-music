/*
 * @Time : 2022/8/9 12:03
 * @Author : hao
 * @File : LoginUserVo.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.vo;

import lombok.Data;

@Data
public class LoginUserVo {

//    private Long id;
    private String id;

    private String account;

    private String nickname;

    private String avatar;
}
