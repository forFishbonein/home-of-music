package com.hao.springbootmusic.service;

import com.hao.springbootmusic.dao.pojo.HaoSysUser;
import com.hao.springbootmusic.dao.vo.Result;
import com.hao.springbootmusic.dao.vo.params.LoginAndRegisterParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional //事务：保证一致性，要么都做要么都不做！ ——> 加在接口上最好！
public interface LoginAndLogoutAndRegisterService {
    /**
     * 登录功能
     * @param loginAndRegisterParam
     * @return
     */
    Result login(LoginAndRegisterParam loginAndRegisterParam);

    /**
     * 检验token
     * @param token
     * @return
     */
    HaoSysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册功能：其实和登录类似
     * @param loginAndRegisterParam
     * @return
     */
    Result register(LoginAndRegisterParam loginAndRegisterParam);
}

