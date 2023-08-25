package com.hao.springbootmusic.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.springbootmusic.dao.pojo.HaoMusic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao
 * @since 2022-08-11
 */
public interface HaoMusicMapper extends BaseMapper<HaoMusic> {
    IPage<HaoMusic> listMusic(Page<HaoMusic> page);
}

