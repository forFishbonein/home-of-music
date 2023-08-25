/*
 * @Time : 2022/8/8 15:11
 * @Author : hao
 * @File : MybatisPlusConfig.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 让spring可以扫描到它
// @Configuration可理解为用spring的时候xml里面的<beans>标签。
// @Bean可理解为用spring的时候xml里面的<bean>标签。
@Configuration
//扫包，将此包下的接口生成代理实现类，并且注册到spring容器中
@MapperScan("com.hao.springbootmusic.dao.mapper")
public class MybatisPlusConfig {

    // MybatisPlus的分页插件：
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
