/*
 * @Time : 2022/8/9 9:57
 * @Author : hao
 * @File : LoginController.java
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
@RequestMapping("login")
public class LoginController {
    //这样不好：不应该把其他的业务引入进来，应该每个功能有单独的业务，登录也有单独的业务！在登陆的业务里面可以引入用户的业务（因为必须要用到用户业务
    //@Autowired
    //private SysUserService SysUserService;
    @Autowired
    private LoginAndLogoutAndRegisterService loginService;

    @PostMapping
    public Result login(@RequestBody LoginAndRegisterParam loginAndRegisterParam){

//    public Result login(@RequestParam LoginAndRegisterParam loginAndRegisterParam){
        return loginService.login(loginAndRegisterParam);
    }
}