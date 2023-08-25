/*
 * @Time : 2022/8/8 18:01
 * @Author : hao
 * @File : HaoSysUserServiceImpl.java
 * @Software : IntelliJ IDEA
 */
package com.hao.springbootmusic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.hao.springbootmusic.dao.mapper.SysUserMapper;
import com.hao.springbootmusic.dao.pojo.HaoSysUser;
import com.hao.springbootmusic.dao.vo.ErrorCode;
import com.hao.springbootmusic.dao.vo.LoginUserVo;
import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.UserVo;
import com.hao.springbootmusic.service.HaoSysUserService;
import com.hao.springbootmusic.service.LoginAndLogoutAndRegisterService;
import com.hao.springbootmusic.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HaoSysUserServiceImpl implements HaoSysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginAndLogoutAndRegisterService loginAndLogoutAndRegisterService;

    @Override
    public HaoSysUser findUser(String account, String pwd) {
        LambdaQueryWrapper<HaoSysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HaoSysUser::getAccount, account); //判断账号是否相等
        queryWrapper.eq(HaoSysUser::getPassword, pwd); //判断密码是否相等，注意pwd在数据库中是加密的，所以传进来的也必须是加密的！
        queryWrapper.select(HaoSysUser::getId, HaoSysUser::getAccount,HaoSysUser::getAvatar,HaoSysUser::getNickname); //只查我们需要的信息
        queryWrapper.last("limit 1"); //查到一个即可停止查询
        HaoSysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    /**
     * 根据账号查找是否有一样的账号
     * @param account
     * @return
     */
    @Override
    public HaoSysUser findUserByAccount(String account) {
        LambdaQueryWrapper<HaoSysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HaoSysUser::getAccount,account);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 保存用户信息
     * @param sysUser
     */
    @Override
    public void save(HaoSysUser sysUser) {
        //注意 mybatis-plus 默认生成的id是分布式id，采用了雪花算法（并不会进行自增）
        //分布式id的好处：用户多了的话需要进行分表，那么就需要用分布式id，因为要保证每台机器上面的id都不重复才可以！
        this.sysUserMapper.insert(sysUser);
    }

    @Override
    public Result getUserInfoByToken(String token) {
        /**
         * 获取用户信息的逻辑：
         * 1.token合法性校验：是否为空，解析是否成功，redis是否存在
         * 2.如果校验失败，就返回错误
         * 3.如果校验成功，返回对应的结果 LoginUserVo
         */
        HaoSysUser sysUser = loginAndLogoutAndRegisterService.checkToken(token);
        if (sysUser == null){
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setId(String.valueOf(sysUser.getId()));
        loginUserVo.setNickname(sysUser.getNickname());
        return Result.success(loginUserVo);
    }

}

