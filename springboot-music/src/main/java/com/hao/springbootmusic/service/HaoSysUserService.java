package com.hao.springbootmusic.service;


import com.hao.springbootmusic.dao.pojo.HaoSysUser;
import com.hao.springbootmusic.dao.vo.Result;

public interface HaoSysUserService {

    HaoSysUser findUser(String account, String pwd);

    HaoSysUser findUserByAccount(String account);

    void save(HaoSysUser sysUser);

    Result getUserInfoByToken(String token);
}
