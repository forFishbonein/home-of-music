package com.hao.springbootmusic.service;

import com.hao.springbootmusic.dao.pojo.HaoSinger;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
public interface HaoSingerService{
    List<HaoSinger> findSingers();
}
