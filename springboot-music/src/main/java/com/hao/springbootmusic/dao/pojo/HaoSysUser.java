/*
 * @Time : 2022/8/11 21:50
 * @Author : hao
 * @File : HaoSysUser.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class HaoSysUser {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String account;

    private String avatar;

    private String nickname;

    private String password;
}