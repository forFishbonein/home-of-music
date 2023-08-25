/*
 * @Time : 2022/8/11 23:36
 * @Author : hao
 * @File : TestController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.controller;

/*
 * @Time : 2022/8/11 23:33
 * @Author : hao
 * @File : TestController.java
 * @Software : IntelliJ IDEA
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController{

    @GetMapping("/haha")
    public String test() {
        return "ok";
    }

}
