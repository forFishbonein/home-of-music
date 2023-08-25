/*
 * @Time : 2022/8/9 15:47
 * @Author : hao
 * @File : RegisterController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.controller;


import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.params.LoginAndRegisterParam;
import com.hao.springbootmusic.service.LoginAndLogoutAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginAndLogoutAndRegisterService loginService;

    @PostMapping
    public Result register(@RequestBody LoginAndRegisterParam loginAndRegisterParam){
        //SSO：单点登录业务，后期可以把登录注册业务提出去，作为一个单独的服务！
        return loginService.register(loginAndRegisterParam);
    }
}
