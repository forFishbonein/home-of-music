package com.hao.springbootmusic.service;

import com.hao.springbootmusic.dao.vo.SingerVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
public interface HaoSingerSingService{
    List<SingerVo> findSingerAndSing();
}
