/*
 * @Time : 2022/8/9 15:42
 * @Author : hao
 * @File : LogoutController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.controller;


import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.service.LoginAndLogoutAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginAndLogoutAndRegisterService loginService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }
}
