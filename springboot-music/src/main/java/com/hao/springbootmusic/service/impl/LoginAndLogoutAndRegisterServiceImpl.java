/*
 * @Time : 2022/8/9 9:59
 * @Author : hao
 * @File : LoginServiceImpl.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.hao.springbootmusic.dao.pojo.HaoSysUser;
import com.hao.springbootmusic.dao.vo.ErrorCode;
import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.params.LoginAndRegisterParam;
import com.hao.springbootmusic.service.HaoSysUserService;
import com.hao.springbootmusic.service.LoginAndLogoutAndRegisterService;
import com.hao.springbootmusic.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAndLogoutAndRegisterServiceImpl implements LoginAndLogoutAndRegisterService {
    /**
     * 登录业务的步骤：
     * 1.检查参数是否合法
     * 2.根据用户名和密码去user表中查询是否存在
     * 3.如果不存在，登录失败，用户名或者密码错误
     * 4.如果存在，使用JWT生成token，返回token给前端
     * 5.注：我们还要把token放入redis中，redis存储token:user的映射关系，同时设置过期时间
     * 登陆成功之后的步骤逻辑（将要去获取用户信息）：先认证token字符串是否合法，再去redis验证token是否存在（减少表的查询，加快速度，不用去访问数据库）
     */

    private static final String slat = "mszlu!@#"; //加密字符串：加密盐；好处：即使数据库的密码被破解了，这个盐只要足够复杂就不会泄露（加密盐字符串也可以写在数据库里面作为一个字段）

    @Autowired
    private HaoSysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate; //导入redisTemplate

    @Override
    public Result login(LoginAndRegisterParam loginAndRegisterParam) {
        String account = loginAndRegisterParam.getAccount();
        String password = loginAndRegisterParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){ //判断账号密码非空
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        String pwd = DigestUtils.md5Hex(password + slat); //构建 MD5+slat 的密码，传入userService
        HaoSysUser sysUser = sysUserService.findUser(account, pwd); //去数据库中查询用户是否存在
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //如果用户存在，即登录成功，使用JWT生成token，返回token
        String token = JWTUtils.createToken(sysUser.getId());
        //将token放到redis中，过期时间为1天
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS); //使用fastjson转换对象为json
        return Result.success(token);
    }

    //检验token是否正确
    @Override
    public HaoSysUser checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token); //自定义的解析token的方法，解析传进来的token
        if (map == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token); //得到redis里面的token
        if (StringUtils.isBlank(userJson)){ //如果redis里面没有匹配的token
            return null;
        }
        HaoSysUser sysUser = JSON.parseObject(userJson, HaoSysUser.class); //把userJson解析为user对象

        return sysUser;
    }

// 生成密码对应的md5字符串：将这个字符串存入数据库即可！
//    public static void main(String[] args) {
//        System.out.println(DigestUtils.md5Hex("admin"+slat));
//    }


    //退出登录时，我们后端只需要将redis中的token清除即可，前端的本地token交给前端来清除
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    /**
     * 注册的逻辑：
     * 1.判断传入的参数是否合法（长度，是否为空，是否符合格式）
     * 2.判断账户是否存在，如果存在，那么就返回异常，账户已经被注册了，如果不存在就将信息存入数据库
     * 3.生成token，返回给前端（这里默认注册成功之后就直接登录了）
     * 4.将token存入redis
     * 5.注意：要加上事务，一旦中间的任何过程出现问题，注册的用户要进行回滚
     */
    @Override
    public Result register(LoginAndRegisterParam loginAndRegisterParam) {
        String account = loginAndRegisterParam.getAccount();
        String password = loginAndRegisterParam.getPassword();
        String nickname = loginAndRegisterParam.getNickname();
        //判断是否为空
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)){

            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg()); //参数有问题
        }
        HaoSysUser sysUser = this.sysUserService.findUserByAccount(account);
        if (sysUser != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg()); //账户已经存在了
        }
        sysUser = new HaoSysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat)); //保存md5密码
        sysUser.setAvatar("/img/header.04c29617.jpg"); //默认头像
        this.sysUserService.save(sysUser); //保存用户信息到数据库

        //token
        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
