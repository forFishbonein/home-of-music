/*
 * @Time : 2022/8/9 16:21
 * @Author : hao
 * @File : LoginInterceptor.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hao.springbootmusic.dao.pojo.HaoSysUser;
import com.hao.springbootmusic.dao.vo.ErrorCode;
import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.service.LoginAndLogoutAndRegisterService;
import com.hao.springbootmusic.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j //lombok的方法
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginAndLogoutAndRegisterService loginAndLogoutAndRegisterServices;

    //preHandle：在执行controller方法（这个在springMvc里面其实就叫做Handler处理器）之前进行执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 拦截器逻辑：
         * 1.需要判断请求的接口路径是否为HandlerMethod（即Controller的方法），这样才需要进行拦截
         * 2.判断token是否为空，如果为空，那么就是未登录
         * 3.如果token不为空，进行登录验证（使用loginAndLogoutAndRegisterServices里面的checkToken）
         * 4.如果认证成功，那么就放行即可
         */
        if (!(handler instanceof HandlerMethod)){ //判断请求的类型是否为handlerMethod
            return true; //如果不是，那么就直接放行，不拦截（因为请求的有可能是RequestResourceHandler，即resource下的static）
        }
        String token = request.getHeader("Authorization"); //从Header里面去拿token

        //打印日志：
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (StringUtils.isBlank(token)){ //如果token为空
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8"); //让浏览器识别我们返回的是一个json数据
            response.getWriter().print(JSON.toJSONString(result)); //返回json信息（网页端会收到信息）
//            response.setContentType("text/html;charset=utf-8"); //让浏览器识别我们返回的是html
//            response.getWriter().print("<div style='color:red;width:100%;height:400px;padding-top:200px;font-size:20px;text-align:center;text-transform:uppercase;'>未登录，请先登录！</div>");
            return false;
        }
        HaoSysUser sysUser = loginAndLogoutAndRegisterServices.checkToken(token); //认证token
        if (sysUser == null){ //如果认证失败
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8"); //让浏览器识别我们返回的是一个json数据
            response.getWriter().print(JSON.toJSONString(result)); //返回json信息
            return false;
        }
        //登录验证成功，可以放行！

        //如果我希望在controller中直接获取用户的信息，怎么获取? ——> 也就是说我们想知道，登录成功之后访问这个资源的人到底是谁！
        UserThreadLocal.put(sysUser);

        //否则就是登录状态，放行即可！
        return true;
    }

    //用完了ThreadLocal之后要进行删除，afterCompletion代表所有的方法都执行完了之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除ThreadLocal中用完的信息，会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}

