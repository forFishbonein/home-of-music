package com.hao.springbootmusic.service.impl;

import com.hao.springbootmusic.dao.pojo.HaoSinger;
import com.hao.springbootmusic.dao.mapper.HaoSingerMapper;
import com.hao.springbootmusic.service.HaoSingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
@Service
public class HaoSingerServiceImpl implements HaoSingerService {

    @Autowired
    HaoSingerMapper haoSingerMapper;

    @Override
    public List<HaoSinger> findSingers() {

        List<HaoSinger> haoSingers = haoSingerMapper.selectList(null);

        return haoSingers;
    }

}

