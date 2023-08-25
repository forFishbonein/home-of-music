/*
 * @Time : 2022/8/8 15:25
 * @Author : hao
 * @File : WebConfig.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.config;

import com.hao.springbootmusic.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //跨域配置：不可设置为*，不安全, 前后端分离项目，可能域名不一致（前端是8080，后端是8888）
        //本地测试 端口不一致 也算跨域
        //我们配置允许8080访问8888端口：这个是必须要配置的，因为前后端分离项目，前端后端是两个服务，不可能用一个端口！
        registry.addMapping("/**").allowedOrigins("http://124.220.15.95:8080");
    }

    //使拦截器生效：
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截test接口：后续实际遇到需要拦截的接口时，再配置为真正的拦截接口
        //拦截/comments/create/change评论请求，因为评论是必须要登录的！
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/");
//                .addPathPatterns("/download/**");
        //注意，这里指的是后端接口，和前端路由没有任何关系！
//                .addPathPatterns("/comments/create/change") //拦截评论
//                .addPathPatterns("/articles/publish"); //拦截发布文章，这样本地线程才可以在这个请求时拿到作者信息
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/register").excludePathPatterns("/download/**");
    }
}
