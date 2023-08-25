/*
 * @Time : 2022/8/9 12:02
 * @Author : hao
 * @File : UserController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.controller;

import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.service.HaoSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private HaoSysUserService sysUserService; //获取用户信息是sysUser业务的功能

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return sysUserService.getUserInfoByToken(token);
    }
}
